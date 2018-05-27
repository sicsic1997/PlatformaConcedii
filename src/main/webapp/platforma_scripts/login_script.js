$(document).ready(function() {

    var loginForm = document.getElementById('login-form');

    $('.wrong-credentials').hide();

    $("#login-btn").click(function(event) {
        if (loginForm.checkValidity() === true) {
            logIn();
        }
        event.preventDefault();
        loginForm.classList.add('was-validated');
    });

    function logIn() {
        var data = {
            userName: $("#loginUserName").val(),
            password: $("#loginPassword").val()
        };
        $.post("/platforma/login", {userName: data.userName, password: data.password}, function(response) {
           if(response == 0) {
               $('.wrong-credentials').show();
           } else {
               window.location.replace("http://localhost:8080/platforma/" + response);
           }

        });
    }

});