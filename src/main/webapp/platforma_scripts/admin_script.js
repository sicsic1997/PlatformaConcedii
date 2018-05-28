$(document).ready(function() {

    if($.cookie('userRole') !== 'ADMIN') {
        window.location.replace("http://localhost:8080/platforma/login");
    }

    var registerForm = document.getElementById('register-form');

    $('.wrong-credentials').hide();
    $('.toaster').hide();

    $("#register-btn").click(function(event) {
        if (registerForm.checkValidity() === true) {
            register();
        }
        event.preventDefault();
        registerForm.classList.add('was-validated');
    });

    function clearValues() {
        $("#registerUserName").val('');
        $("#registerPassword").val('');
        $("#registerFirstName").val('');
        $("#registerLastName").val('');
    }

    function register() {
        var data = {
            userName: $("#registerUserName").val(),
            password: $("#registerPassword").val(),
            firstName: $("#registerFirstName").val(),
            lastName: $("#registerLastName").val(),
            userRole: $("#registerUserRole").find(":selected").text()
        };

        $('.toaster').hide();

        $.post("/platforma/admin", data, function(response) {
            var classToAdd = response.substring(0, 1) == 0 ? 'error' : 'success';
            $('.toaster p').text(response.substring(1));
            $('.toaster').addClass(classToAdd);
            $('.toaster').fadeIn(200);
            $('.toaster').delay(2000).fadeOut(200);
            $('#register-btn').prop('disabled', true);
            setTimeout(function(){location.reload();}, 2500);
        });
    }

});