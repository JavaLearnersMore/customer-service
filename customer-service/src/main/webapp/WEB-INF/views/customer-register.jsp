
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Customer Service Registration</title>

    <style>
        * { box-sizing: border-box; }
        body { margin: 0; font-family: Arial, sans-serif; background: #f4f7fb; }
        .header { background: #0d6efd; color: white; padding: 18px 50px; font-size: 24px; font-weight: bold; }
        .page-wrap {
            width: 1250px; margin: 40px auto;
        }
        .top-row {
            display: flex; gap: 25px; align-items: flex-start;
        }
        .container {
            width: 600px; background: white;
            padding: 30px 40px; border-radius: 10px;
            box-shadow: 0 4px 15px rgba(0,0,0,0.12);
        }
        .top-row .container {
            flex: 1 1 0; width: auto;
            height: 640px;
            overflow-y: auto;
        }
        .container h2 { text-align: center; margin-bottom: 25px; color: #333; }
        .step-badge {
            display: inline-block; font-size: 11px; font-weight: bold;
            color: #6c8ebf; background: #eaf1fb; padding: 3px 10px;
            border-radius: 12px; margin-bottom: 10px;
        }
        .hint { font-size: 13px; color: #777; margin: -12px 0 18px; text-align: center; }
        .form-group { margin-bottom: 18px; }
        label { display: block; margin-bottom: 6px; font-weight: bold; color: #444; }
        input {
            width: 100%; padding: 11px; border: 1px solid #ccc;
            border-radius: 5px; font-size: 15px;
        }
        input:focus { border-color: #0d6efd; outline: none; }
        .register-btn {
            width: 100%; padding: 12px; background: #0d6efd; border: none;
            color: white; font-size: 16px; font-weight: bold;
            border-radius: 5px; cursor: pointer; margin-top: 10px;
        }
        .register-btn:hover { background: #0b5ed7; }
        .login-link { text-align: center; margin-top: 20px; }
        .login-link a { color: #0d6efd; text-decoration: none; font-weight: bold; }
        .message { padding: 10px; margin-bottom: 15px; border-radius: 5px; text-align: center; }
        .message.big { font-size: 18px; font-weight: bold; padding: 14px; }
        .success { background: #d1e7dd; color: #0f5132; }
        .error { background: #f8d7da; color: #842029; }
        .row { display: flex; gap: 15px; }
        .row .form-group { width: 50%; }
        .response-label {
            font-size: 12px;
            font-weight: bold;
            letter-spacing: 0.5px;
            color: #6c8ebf;
            margin: 15px 0 8px;
        }
        .response-json {
            background: #0d1b2a;
            color: #d6e4f0;
            padding: 16px 20px;
            border-radius: 8px;
            border-left: 4px solid #2ecc71;
            font-family: 'Consolas', 'Monaco', monospace;
            font-size: 14px;
            line-height: 1.6;
            overflow-x: auto;
            white-space: pre;
        }
        .copy-btn {
            display: inline-block;
            margin-top: 8px;
            padding: 6px 14px;
            font-size: 13px;
            font-weight: bold;
            background: #2ecc71;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        .copy-btn:hover { background: #27ae60; }
        .copy-btn.copied { background: #1a8f4d; }
        .bottom-row {
            margin-top: 25px;
        }
        .bottom-row .container {
            width: 100%;
            max-width: 700px;
            margin: 0 auto;
        }
        .profile-accounts-row {
            display: grid;
            grid-template-columns: 1fr 1fr;
            gap: 25px;
            margin-top: 25px;
        }
        .profile-accounts-row .container {
            width: auto;
        }
        .action-btn {
            padding: 10px 20px; background: #6f42c1; border: none;
            color: white; font-size: 14px; font-weight: bold;
            border-radius: 6px; cursor: pointer;
        }
        .action-btn:hover { background: #5e35b1; }
        @media(max-width: 900px) {
            .profile-accounts-row { grid-template-columns: 1fr; }
        }
        @media(max-width: 650px) {
            .container { width: 90%; padding: 25px; }
            .row { display: block; }
            .row .form-group { width: 100%; }
        }
    </style>
</head>

<body>

<div class="header">Net Banking</div>

<div class="page-wrap">

<div class="top-row">

<div class="container">

    <h2>Register Customer</h2>

    <div id="messageBox"></div>

    <form id="registerForm">

        <div class="form-group">
            <label>Full Name</label>
            <input type="text" name="name" placeholder="Enter full name" required>
        </div>

        <div class="row">
            <div class="form-group">
                <label>Email</label>
                <input type="email" name="email" placeholder="Enter email" required>
            </div>
            <div class="form-group">
                <label>Mobile</label>
                <input type="text" name="mobile" placeholder="Enter mobile number" maxlength="10" required>
            </div>
        </div>

        <div class="row">
            <div class="form-group">
                <label>Date of Birth</label>
                <input type="date" name="dob" required>
            </div>
            <div class="form-group">
                <label>Account Number</label>
                <input type="text" name="accountNumber" placeholder="Enter account number" required>
            </div>
        </div>

        <div class="form-group">
            <label>IFSC Code</label>
            <input type="text" name="ifsc" placeholder="Example: NETB0000001" required>
        </div>

        <div class="form-group">
            <label>Username</label>
            <input type="text" name="username" placeholder="Create username" required>
        </div>

        <div class="form-group">
            <label>Password</label>
            <input type="password" name="password" placeholder="Create password" required>
        </div>

        <div class="form-group">
            <label>MPIN</label>
            <input type="password" name="mpin" placeholder="Enter 4 digit MPIN"
                   maxlength="4" pattern="[0-9]{4}" required>
        </div>

        <button type="submit" class="register-btn">Register Customer</button>

    </form>

    <div class="login-link">
        Already registered?
        <a href="${pageContext.request.contextPath}/login">Login here</a>
    </div>

</div>

<!-- ===================== LOGIN (SINGLE STEP, NEVER CHANGES) ===================== -->
<div class="container" id="loginPanel">

    <h2>Login</h2>
<div id="loginMessageBox"></div>
    <form id="loginStep1Form">

        <div class="form-group">
            <label>Username</label>
            <input type="text" name="username" placeholder="Enter username" required>
        </div>

        <div class="form-group">
            <label>Password</label>
            <input type="password" name="password" placeholder="Enter password" required>
        </div>

        <button type="submit" class="register-btn">Login</button>

    </form>

  <div id="loginResponseBox"></div>

    <!-- OTP box - appears after login succeeds, stays in sync with the Verify OTP form below -->
    <div class="form-group" id="loginOtpBox" style="display:none; margin-top: 15px;">
        <label>OTP</label>
        <input type="text" id="loginOtpInput" placeholder="Enter 6 digit OTP" maxlength="6" pattern="[0-9]{6}" autocomplete="one-time-code">
    </div>

</div>

</div>

<!-- ===================== VERIFY OTP (SEPARATE BOX, SIMPLE: OTP FIELD + RESPONSE BELOW BUTTON) ===================== -->
<div class="bottom-row">

<div class="container" id="verifyOtpPanel">

    <h2>Verify OTP</h2>
    <div class="hint">Dev/test mode: any syntactically valid 6-digit code is accepted.</div>
    <div id="verifyOtpMessageBox"></div>

    <form id="verifyOtpForm">

        <div class="form-group">
            <label>preAuthToken</label>
            <input type="text" name="preAuthToken" id="preAuthToken" readonly style="background: #f4f7fb; color: #555;">
        </div>

        <div class="form-group">
            <label>OTP</label>
            <input type="text" id="verifyOtpInput" name="otp" placeholder="Enter 6 digit OTP" maxlength="6" pattern="[0-9]{6}" required autocomplete="one-time-code">
        </div>

        <button type="submit" class="register-btn">Verify OTP</button>

    </form>

    <div id="verifyOtpResponseBox"></div>

</div>

</div>

<!-- ===================== PROFILE & ACCOUNTS & CHANGE MPIN ===================== -->
<div class="profile-accounts-row">

    <div class="container">
        <h2>My Profile</h2>
        <div class="form-group">
            <label>Customer ID</label>
            <input type="text" id="profileCustomerIdInput" placeholder="Enter customer ID">
        </div>
        <button type="button" class="action-btn" id="getProfileBtn">Get Profile</button>
        <div id="profileMessageBox"></div>
        <div id="profileResponseBox"></div>
    </div>

    <div class="container">
        <h2>My Accounts</h2>
        <div class="form-group">
            <label>Customer ID</label>
            <input type="text" id="accountsCustomerIdInput" placeholder="Enter customer ID">
        </div>
        <button type="button" class="action-btn" id="getAccountsBtn">Get Linked Accounts</button>
        <div id="accountsMessageBox"></div>
        <div id="accountsResponseBox"></div>
    </div>

    <div class="container">
        <h2>Change MPIN</h2>

        <form id="changeMpinForm">
            <div class="form-group">
                <label>Current MPIN</label>
                <input type="password" name="currentMpin" placeholder="Current 4 digit MPIN" maxlength="4" pattern="[0-9]{4}" required>
            </div>
            <div class="form-group">
                <label>New MPIN</label>
                <input type="password" name="newMpin" placeholder="New 4 digit MPIN" maxlength="4" pattern="[0-9]{4}" required>
            </div>
            <button type="submit" class="action-btn">Change MPIN</button>
        </form>

        <div id="changeMpinMessageBox"></div>
        <div id="changeMpinResponseBox"></div>
    </div>

</div>

</div>

<script>
document.getElementById('registerForm').addEventListener('submit', async function (e) {
    e.preventDefault();


    const form = e.target;
    const data = Object.fromEntries(new FormData(form).entries());
    const messageBox = document.getElementById('messageBox');

    try {
    	const response = await fetch('${pageContext.request.contextPath}/api/v1/customers/register', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(data)
        });

        const result = await response.json();

        if (response.ok) {
            messageBox.innerHTML =
                '<div class="message success">Registered successfully!</div>' +
                '<div class="response-label">RESPONSE &mdash; ' + response.status + ' CREATED</div>' +
                '<pre class="response-json">' + JSON.stringify(result, null, 2) + '</pre>';
            form.reset();
        } else {
            messageBox.innerHTML = '<div class="message error">' + (result.error || 'Registration failed') + '</div>';
        }
    } catch (err) {
        messageBox.innerHTML = '<div class="message error">Something went wrong: ' + err.message + '</div>';
    }
});

/* ===================== LOGIN - STEP 1 (USERNAME + PASSWORD) / STEP 3 (AFTER OTP VERIFIED) ===================== */
let verifiedLoginResult = null;

document.getElementById('loginStep1Form').addEventListener('submit', async function (e) {
    e.preventDefault();

    const form = e.target;
    const messageBox = document.getElementById('loginMessageBox');

    // If OTP is already verified, this click just reveals the final response - no API call needed
    if (verifiedLoginResult) {
        messageBox.innerHTML =
            '<div class="message success">Login successful!</div>' +
            '<div class="response-label">RESPONSE &mdash; 200 OK</div>' +
            '<pre class="response-json">' + JSON.stringify(verifiedLoginResult, null, 2) + '</pre>';

        // Reset everything for the next login
        verifiedLoginResult = null;
        document.getElementById('loginOtpBox').style.display = 'none';
        document.getElementById('loginOtpInput').value = '';
        form.reset();
        return;
    }

    const data = Object.fromEntries(new FormData(form).entries());
    const responseBox = document.getElementById('loginResponseBox');

    try {
        const response = await fetch('${pageContext.request.contextPath}/api/v1/auth/login', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(data)
        });

        const result = await response.json();

        if (response.ok) {
            // Login box never changes - big message + an OTP box that stays in sync with the box below
            messageBox.innerHTML = '<div class="message success big">Enter OTP</div>';
            document.getElementById('loginOtpBox').style.display = 'block';

            document.getElementById('preAuthToken').value = result.preAuthToken;

            responseBox.innerHTML =
                '<div class="response-label">RESPONSE &mdash; ' + response.status + ' OK</div>' +
                '<pre class="response-json">' + JSON.stringify(result, null, 2) + '</pre>';
        } else {
            messageBox.innerHTML = '<div class="message error">' + (result.error || 'Login failed') + '</div>';
            responseBox.innerHTML =
                '<div class="response-label">RESPONSE &mdash; ' + response.status + '</div>' +
                '<pre class="response-json">' + JSON.stringify(result, null, 2) + '</pre>';
        }
    } catch (err) {
        messageBox.innerHTML = '<div class="message error">Something went wrong: ' + err.message + '</div>';
    }
});

// Keep the Login box's OTP field and the Verify OTP box's OTP field in sync (either can be typed into)
document.getElementById('loginOtpInput').addEventListener('input', function () {
    document.getElementById('verifyOtpInput').value = this.value;
});
document.getElementById('verifyOtpInput').addEventListener('input', function () {
    document.getElementById('loginOtpInput').value = this.value;
});

/* ===================== VERIFY OTP - RESPONSE SHOWS BELOW THE BUTTON ===================== */
document.getElementById('verifyOtpForm').addEventListener('submit', async function (e) {
    e.preventDefault();

    const form = e.target;
    const data = Object.fromEntries(new FormData(form).entries());
    const messageBox = document.getElementById('verifyOtpMessageBox');
    const responseBox = document.getElementById('verifyOtpResponseBox');

    try {
        const response = await fetch('${pageContext.request.contextPath}/api/v1/auth/verify-otp', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(data)
        });

        const result = await response.json();

        if (response.ok) {
            messageBox.innerHTML = '<div class="message success">OTP verified successfully</div>';
            responseBox.innerHTML =
                '<div class="response-label">RESPONSE &mdash; ' + response.status + ' OK</div>' +
                '<pre class="response-json">' + JSON.stringify(result, null, 2) + '</pre>';

            // Hold the result - Login button (clicked again) will reveal it there too
            verifiedLoginResult = result;

            if (result.accessToken) {
                sessionStorage.setItem('accessToken', result.accessToken);
                sessionStorage.setItem('customerId', result.customerId);
                document.getElementById('profileCustomerIdInput').value = result.customerId;
                document.getElementById('accountsCustomerIdInput').value = result.customerId;
            }

            form.reset();
        } else {
            messageBox.innerHTML = '<div class="message error">' + (result.error || 'OTP verification failed') + '</div>';
            responseBox.innerHTML =
                '<div class="response-label">RESPONSE &mdash; ' + response.status + '</div>' +
                '<pre class="response-json">' + JSON.stringify(result, null, 2) + '</pre>';
        }
    } catch (err) {
        messageBox.innerHTML = '<div class="message error">Something went wrong: ' + err.message + '</div>';
    }
});

/* ===================== GET PROFILE ===================== */
document.getElementById('getProfileBtn').addEventListener('click', async function () {
    const messageBox = document.getElementById('profileMessageBox');
    const responseBox = document.getElementById('profileResponseBox');
    const customerIdInput = document.getElementById('profileCustomerIdInput');
    const customerId = customerIdInput.value.trim() || sessionStorage.getItem('customerId');

    if (!customerId) {
        messageBox.innerHTML = '<div class="message error">Please enter a Customer ID (or verify OTP first).</div>';
        return;
    }

    messageBox.innerHTML = '<div class="message">Loading...</div>';
    responseBox.innerHTML = '';

    try {
        const response = await fetch('${pageContext.request.contextPath}/api/v1/profile?customerId=' + encodeURIComponent(customerId), {
            method: 'GET'
        });

        const result = await response.json();

        if (response.ok) {
            messageBox.innerHTML = '<div class="message success">Profile loaded</div>';
        } else {
            messageBox.innerHTML = '<div class="message error">' + (result.error || 'Failed to load profile') + '</div>';
        }
        responseBox.innerHTML =
            '<div class="response-label">RESPONSE &mdash; ' + response.status + '</div>' +
            '<pre class="response-json">' + JSON.stringify(result, null, 2) + '</pre>';
    } catch (err) {
        messageBox.innerHTML = '<div class="message error">Something went wrong: ' + err.message + '</div>';
    }
});

/* ===================== GET LINKED ACCOUNTS ===================== */
document.getElementById('getAccountsBtn').addEventListener('click', async function () {
    const messageBox = document.getElementById('accountsMessageBox');
    const responseBox = document.getElementById('accountsResponseBox');
    const customerIdInput = document.getElementById('accountsCustomerIdInput');
    const customerId = customerIdInput.value.trim() || sessionStorage.getItem('customerId');

    if (!customerId) {
        messageBox.innerHTML = '<div class="message error">Please enter a Customer ID (or verify OTP first).</div>';
        return;
    }

    messageBox.innerHTML = '<div class="message">Loading...</div>';
    responseBox.innerHTML = '';

    try {
        const response = await fetch('${pageContext.request.contextPath}/api/v1/accounts?customerId=' + encodeURIComponent(customerId), {
            method: 'GET'
        });

        const result = await response.json();

        if (response.ok) {
            messageBox.innerHTML = '<div class="message success">Accounts loaded</div>';
        } else {
            messageBox.innerHTML = '<div class="message error">' + (result.error || 'Failed to load accounts') + '</div>';
        }
        responseBox.innerHTML =
            '<div class="response-label">RESPONSE &mdash; ' + response.status + '</div>' +
            '<pre class="response-json">' + JSON.stringify(result, null, 2) + '</pre>';
    } catch (err) {
        messageBox.innerHTML = '<div class="message error">Something went wrong: ' + err.message + '</div>';
    }
});

/* ===================== CHANGE MPIN ===================== */
document.getElementById('changeMpinForm').addEventListener('submit', async function (e) {
    e.preventDefault();

    const form = e.target;
    const data = Object.fromEntries(new FormData(form).entries());
    const messageBox = document.getElementById('changeMpinMessageBox');
    const responseBox = document.getElementById('changeMpinResponseBox');
    const customerId = sessionStorage.getItem('customerId');

    if (!customerId) {
        messageBox.innerHTML = '<div class="message error">Please verify OTP first to get a customerId.</div>';
        return;
    }

    messageBox.innerHTML = '<div class="message">Submitting...</div>';
    responseBox.innerHTML = '';

    try {
        const response = await fetch('${pageContext.request.contextPath}/api/v1/profile/mpin?customerId=' + encodeURIComponent(customerId), {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(data)
        });

        const result = await response.json();

        if (response.ok) {
            messageBox.innerHTML = '<div class="message success">MPIN changed successfully</div>';
            form.reset();
        } else {
            messageBox.innerHTML = '<div class="message error">' + (result.error || 'Failed to change MPIN') + '</div>';
        }
        responseBox.innerHTML =
            '<div class="response-label">RESPONSE &mdash; ' + response.status + '</div>' +
            '<pre class="response-json">' + JSON.stringify(result, null, 2) + '</pre>';
    } catch (err) {
        messageBox.innerHTML = '<div class="message error">Something went wrong: ' + err.message + '</div>';
    }
});
</script>

</body>
</html>