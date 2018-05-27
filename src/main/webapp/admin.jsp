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
    <link rel="stylesheet" href="platforma_css/admin.css">
    <link href="https://fonts.googleapis.com/css?family=Molengo" rel="stylesheet">

</head>

<body>
<div class="container admin-container">
    <div class="row mb-5">
        <div class="col-md text-center">
            <h1>Adauga Personal</h1>
        </div>
    </div>
    <div class="row justify-content-center">
        <div class="col-md-8">
            <form id="login-form" class="text-center needs-validation" novalidate>
                <div class="row">
                    <div class="form-group text-left col-md-6">
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
                    <div class="form-group text-right col-md-6">
                        <label for="loginPassword">Password</label>
                        <input type="text"
                               class="form-control text-right"
                               id="loginPassword"
                               placeholder="Enter password"
                               required>
                        <div class="invalid-feedback">
                            Please provide a valid password.
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group text-left col-md-6">
                        <label for="loginFirstName">First name</label>
                        <input type="text"
                               class="form-control"
                               id="loginFirstName"
                               placeholder="Enter first name"
                               required>
                        <div class="invalid-feedback">
                            Please provide a valid first name.
                        </div>
                    </div>
                    <div class="form-group text-right col-md-6">
                        <label for="loginLastName">Last name</label>
                        <input type="text"
                               class="form-control text-right"
                               id="loginLastName"
                               placeholder="Enter last name"
                               required>
                        <div class="invalid-feedback">
                            Please provide a valid last name.
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="form-group col-md-12 text-left">
                        <label>User role</label>
                        <select class="custom-select" required>
                            <option value="1">EMPLOYEE</option>
                            <option value="2">MANAGER</option>
                            <option value="3">ADMIN</option>
                        </select>
                        <div class="invalid-feedback">Please select an user role</div>
                    </div>
                </div>

                <button type="submit" id="login-btn" class="btn btn-success mt-4">Submit</button>
            </form>
        </div>
    </div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript" src="platforma_scripts/login_script.js"></script>

</body>
</html>