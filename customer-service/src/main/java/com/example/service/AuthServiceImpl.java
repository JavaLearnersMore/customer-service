package com.example.service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.dao.CustomerCredentialDAO;
import com.example.model.CustomerCredential;
import com.example.model.LoginRequest;
import com.example.model.LoginResponse;
import com.example.model.PreAuthResponse;
import com.example.model.VerifyOtpRequest;

@Service
public class AuthServiceImpl implements AuthService {

    private static final Logger log = LoggerFactory.getLogger(AuthServiceImpl.class);

    private static final Pattern SIX_DIGIT_OTP = Pattern.compile("^[0-9]{6}$");
    private static final long PRE_AUTH_TOKEN_TTL_MS = TimeUnit.MINUTES.toMillis(5);
    private static final long ACCESS_TOKEN_TTL_SECONDS = 3600;
    private static final int MAX_OTP_ATTEMPTS = 3;

    private final SecureRandom random = new SecureRandom();
    private final CustomerCredentialDAO credentialDAO;
    private final PasswordEncoder passwordEncoder;

    private final ConcurrentHashMap<String, PreAuthEntry> preAuthStore = new ConcurrentHashMap<>();

    @Autowired
    public AuthServiceImpl(CustomerCredentialDAO credentialDAO, PasswordEncoder passwordEncoder) {
        this.credentialDAO = credentialDAO;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public PreAuthResponse login(LoginRequest loginRequest) {
        if (loginRequest.getUsername() == null || loginRequest.getUsername().isBlank()
                || loginRequest.getPassword() == null || loginRequest.getPassword().isBlank()) {
            throw new IllegalArgumentException("Username and password are required");
        }

        CustomerCredential credential = credentialDAO.findByUsername(loginRequest.getUsername());
        if (credential == null) {
            throw new IllegalArgumentException("Invalid username or password");
        }
        if (!passwordEncoder.matches(loginRequest.getPassword(), credential.getPasswordHash())) {
            throw new IllegalArgumentException("Invalid username or password");
        }

        Long customerId = credential.getCustomerId();

        String preAuthToken = generateToken();
        String otp = generateOtp();

        preAuthStore.put(preAuthToken, new PreAuthEntry(
                customerId,
                otp,
                System.currentTimeMillis() + PRE_AUTH_TOKEN_TTL_MS,
                0
        ));

   
        log.info("Generated OTP {} for preAuthToken {} (customerId={})", otp, preAuthToken, customerId);

        return new PreAuthResponse(preAuthToken, "Password verified. Enter the OTP sent to your registered mobile to complete login.");
    }

    @Override
    public LoginResponse verifyOtp(VerifyOtpRequest verifyOtpRequest) {
        String preAuthToken = verifyOtpRequest.getPreAuthToken();
        String otp = verifyOtpRequest.getOtp();

        if (preAuthToken == null || preAuthToken.isBlank()) {
            throw new IllegalArgumentException("preAuthToken is required");
        }
        if (otp == null || !SIX_DIGIT_OTP.matcher(otp).matches()) {
            throw new IllegalArgumentException("A valid 6-digit OTP is required");
        }

        PreAuthEntry entry = preAuthStore.get(preAuthToken);
        if (entry == null) {
            throw new IllegalArgumentException("Invalid or expired preAuthToken");
        }
        if (System.currentTimeMillis() > entry.expiresAtMs) {
            preAuthStore.remove(preAuthToken);
            throw new IllegalArgumentException("preAuthToken has expired, please login again");
        }

        if (entry.attempts >= MAX_OTP_ATTEMPTS) {
            preAuthStore.remove(preAuthToken);
            throw new IllegalArgumentException("Too many incorrect attempts, please login again");
        }

        if (!entry.otp.equals(otp)) {
            preAuthStore.put(preAuthToken, entry.withIncrementedAttempts());
            throw new IllegalArgumentException("Incorrect OTP");
        }

        // OTP matched - one-time use, remove immediately.
        preAuthStore.remove(preAuthToken);

        String accessToken = generateToken();
        return new LoginResponse(accessToken, "Bearer", ACCESS_TOKEN_TTL_SECONDS, entry.customerId);
    }

    private String generateToken() {
        byte[] randomBytes = UUID.randomUUID().toString().getBytes();
        return Base64.getUrlEncoder().withoutPadding().encodeToString(randomBytes);
    }

    /** Generates a random 6-digit numeric OTP, e.g. "042817". Always exactly 6 digits, zero-padded. */
    private String generateOtp() {
        int otp = random.nextInt(1_000_000); // 0 .. 999999
        return String.format("%06d", otp);
    }

    private static class PreAuthEntry {
        final Long customerId;
        final String otp;
        final long expiresAtMs;
        final int attempts;

        PreAuthEntry(Long customerId, String otp, long expiresAtMs, int attempts) {
            this.customerId = customerId;
            this.otp = otp;
            this.expiresAtMs = expiresAtMs;
            this.attempts = attempts;
        }

        PreAuthEntry withIncrementedAttempts() {
            return new PreAuthEntry(customerId, otp, expiresAtMs, attempts + 1);
        }
    }
}