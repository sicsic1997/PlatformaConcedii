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
    <link rel="stylesheet" href="platforma_css/daterangepicker.css">
    <link rel="stylesheet" href="platforma_css/toaster.css">
    <link rel="stylesheet" href="platforma_css/employee.css">
    <link href="https://fonts.googleapis.com/css?family=Molengo" rel="stylesheet">

</head>

<body>
<nav class="navbar navbar-light bg-light justify-content-between flex-nowrap flex-row">
    <div class="container mx-3">
        <ul class="nav navbar-nav flex-row">
            <li class="nav-item pr-3">
                <a class="nav-link active" href="holidayRequest">MAKE REQUEST</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="dashboard">DASHBOARD</a>
            </li>
        </ul>
    </div>
</nav>
<div class="container employee-container">
    <div class="row">
        <div class="col-md-12 text-center">
            <h1>Take your well-deserved holiday</h1>
        </div>
    </div>
    <div class="row mt-5">
        <div class="col-md-8">
            <div class="form-group">
                <label>Select period</label>
                <input type="text" class="form-control" id="daterange" placeholder="Select value">
            </div>
        </div>
    </div>
    <div class="row mt-3 emp-select">
        <div class="form-group col-md-8 text-left">
            <label>Select employee</label>
            <select id="user-select" class="custom-select">
            </select>
        </div>
    </div>
    <div class="row">
        <div class="col-md-8 wrong-credentials">
            <p class="text-right"></p>
        </div>
    </div>
    <div class="row">
        <div class="text-center col-md-8">
            <button type="submit" id="request-holiday" class="btn btn-success mt-4 px-4">SUBMIT</button>
        </div>
    </div>
</div>

<div class="toaster">
    <p></p>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>
<script src="platforma_scripts/moment.min.js"></script>
<script src="platforma_scripts/daterangepicker.js"></script>
<script type="text/javascript" src="platforma_scripts/employeeRequest_script.js"></script>

</body>
</html>