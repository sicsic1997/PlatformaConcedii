<%@ page language="java"
         contentType="text/html; charset=windows-1256"
         pageEncoding="windows-1256"
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
    <title>Platforma concedii</title>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css" integrity="sha384-9gVQ4dYFwwWSjIDZnLEWnxCjeSWFphJiwGPXr1jddIhOegiu1FwO5qRGvFXOdJZ4" crossorigin="anonymous">
    <link rel="stylesheet" href="platforma_css/login.css">
    <link href="https://fonts.googleapis.com/css?family=Molengo" rel="stylesheet">

</head>

<body>
<div class="container login-container">
    <div class="row mb-5">
        <div class="col-md text-center">
            <h1>Login</h1>
        </div>
    </div>
    <div class="row justify-content-center">
        <div class="col-md-6">
            <form id="login-form" class="text-center needs-validation" novalidate>
                <div class="form-group text-left">
                    <label for="loginUserName">Username</label>
                    <input type="text"
                           class="form-control"
                           id="loginUserName"
                           placeholder="Enter username"
                           required>
                    <div class="invalid-feedback">
                        Please provide a valid username.
                    </div>
                </div>
                <div class="form-group text-left">
                    <label for="loginPassword">Password</label>
                    <input type="password"
                           class="form-control"
                           id="loginPassword"
                           placeholder="Password"
                           required>
                    <div class="invalid-feedback">
                        Please provide a valid username.
                    </div>
                </div>
                <div class="wrong-credentials">
                    <p class="text-right">Username or password incorrect</p>
                </div>
                <button type="submit" id="login-btn" class="btn btn-success mt-4">Submit</button>
            </form>
        </div>
    </div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>
<script type="text/javascript" src="platforma_scripts/login_script.js"></script>

</body>
</html>