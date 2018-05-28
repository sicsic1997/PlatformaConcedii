$(document).ready(function() {
    var users = [];
    var userSelect = $('#user-select');

    if($.cookie('userRole') !== 'EMPLOYEE') {
        window.location.replace("http://localhost:8080/platforma/login");
    }

    $('.wrong-credentials').hide();

    getAllUsers();

    $('#daterange').daterangepicker({
        autoApply:true
    });

    function getAllUsers() {
        $.ajax({
            url: '/platforma/employee',
            type: 'PUT',
            success: function(response) {
                users = JSON.parse(response);
                populateDropdown();

            }
        });
    }

    function populateDropdown() {
        for(var i = 0; i < users.length; i++) {
            userSelect.append($("<option />").val(users[i].userName).text(users[i].firstName + ' ' + users[i].lastName));
        }
    }

});