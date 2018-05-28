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
    <link rel="stylesheet" href="platforma_css/dashboard.css">
    <link rel="stylesheet" href="platforma_css/toaster.css">
    <link href="https://fonts.googleapis.com/css?family=Molengo" rel="stylesheet">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons"
          rel="stylesheet">

</head>

<body>
<nav class="navbar navbar-light bg-light justify-content-between flex-nowrap flex-row">
    <div class="container mx-3">
        <ul class="nav navbar-nav flex-row">
            <li class="nav-item pr-3">
                <a class="nav-link" href="holidayRequest">MAKE REQUEST</a>
            </li>
            <li class="nav-item">
                <a class="nav-link active" href="dashboard">DASHBOARD</a>
            </li>
        </ul>
    </div>
</nav>
<div class="container dashboard-container">
    <div class="row mt-3" id="search-filters">
        <div class="col-md-3 text-left">
            <div class="form-group">
                <label>Select employee</label>
                <select id="userSelect" class="custom-select" required>
                    <option value="All">All</option>
                </select>
            </div>
        </div>
        <div class="col-md-3 text-left">
            <div class="form-group">
                <label>Select status</label>
                <select id="statusSelect" class="custom-select" required>
                    <option value="0">All</option>
                    <option value="1">APPROVED</option>
                    <option value="2">REJECTED</option>
                    <option value="3">PENDING</option>
                </select>
            </div>
        </div>
        <div class="col-md-3">
            <div class="form-group">
                <label>Select period</label>
                <input type="text" class="form-control disable" id="daterange" placeholder="Select value">
            </div>
        </div>
        <div class="col-md-2 pt-3">
            <div class="custom-control custom-checkbox">
                <input type="checkbox" class="custom-control-input" id="filterCheck">
                <label class="custom-control-label" for="filterCheck">Filter Date</label>
            </div>
        </div>
        <div class="col-md-12 text-center">
            <button type="submit" id="search-btn" class="btn btn-success mt-4 px-4">SEARCH</button>
        </div>
    </div>
    <div class="results">
        <div class="table-wrapper mb-2">
            <table class="table">
                <thead>
                <tr>
                    <th class="border-top-0" scope="col">First Name</th>
                    <th class="border-top-0" scope="col">Last Name</th>
                    <th class="border-top-0" scope="col">Start Date</th>
                    <th class="border-top-0" scope="col">End Date</th>
                    <th class="border-top-0" scope="col">Status</th>
                    <th class="border-top-0" scope="col">Approve / Reject</th>
                </tr>
                </thead>
                <tbody>

                </tbody>
            </table>
        </div>

        <div class="row">
            <div class="col-md-12 text-left">
                <p class="go-back">
                    <i class="material-icons">arrow_back</i>
                    <span>GO BACK</span>
                </p>
            </div>
        </div>

        <div class="row">
            <div class="text-center col-md-12">
                <button type="submit" id="generate-report" class="btn btn-success mt-4 px-4">GENERATE REPORT</button>
            </div>
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
<script type="text/javascript" src="platforma_scripts/dashboard_script.js"></script>

</body>
</html>