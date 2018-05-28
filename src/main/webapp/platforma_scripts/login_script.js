$(document).ready(function() {

    deleteCookies();

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
            var location;
            switch (response) {
                case "ADMIN":
                    location = 'admin';
                    break;
                case "MANAGER":
                    location = 'managerRequest';
                    break;
                case "EMPLOYEE":
                    location = 'employee';
                    break;
                default:
                    $('.wrong-credentials').show();
                    return;
            }
            $.cookie("userRole", response, { expires : 10 });
            $.cookie("userName", data.userName, { expires : 10 });
            window.location.replace("http://localhost:8080/platforma/" + location);
        });
    }

    function deleteCookies() {
        $.cookie("userRole", null);
        $.cookie("userName", null);

        $.removeCookie("userRole");
        $.removeCookie("userName");
    }

});