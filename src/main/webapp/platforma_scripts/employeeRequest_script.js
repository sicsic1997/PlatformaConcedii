$(document).ready(function() {
    var users = [],
        userSelect = $('#user-select'),
        minDate = new Date(),
        maxDate = new Date(minDate.getFullYear() + 1, minDate.getMonth(), minDate.getDate());

    if($.cookie('userRole') !== 'EMPLOYEE' && $.cookie('userRole') !== 'MANAGER') {
        window.location.replace("http://localhost:8080/platforma/login");
    }

    $('.wrong-credentials').hide();
    $('.toaster').hide();
    $('.emp-select').hide();

    if($.cookie('userRole') === 'MANAGER') {
        getAllUsers();
        $('.emp-select').val($('.emp-select option:first').val());
        $('.emp-select').show();
        $('.emp-select').css("display", "flex");
    }

    $('#daterange').daterangepicker({
        autoApply:true,
        showDropdowns: true,
        minDate: minDate,
        maxDate: maxDate
    });


    $('#request-holiday').click(function() {
        takeHoliday();
    });

    function getAllUsers() {
        $.ajax({
            url: '/platforma/holidayRequest',
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

    function takeHoliday() {
        var data = {
            userName: $.cookie('userRole') === 'MANAGER' ? userSelect.find(":selected").val() : $.cookie('userName'),
            startDate: $('#daterange').data('daterangepicker').startDate.format('DD-MM-YYYY'),
            endDate: $('#daterange').data('daterangepicker').endDate.format('DD-MM-YYYY'),
            status: $.cookie('userRole') === 'MANAGER' ? 'APPROVED' : 'PENDING'
        };

        $('.toaster').hide();

        $.post("/platforma/holidayRequest", data, function(response) {
            var classToAdd = response.substring(0, 1) == 0 ? 'error' : 'success';
            $('.toaster p').text(response.substring(1));
            $('.toaster').addClass(classToAdd);
            $('.toaster').fadeIn(200);
            $('#request-holiday').prop('disabled', true);
            setTimeout(function() {
                $('#request-holiday').prop('disabled', false);
                $('.toaster').fadeOut(200);
                $(".toaster").removeClass("error success");
            }, 2000);
            if(classToAdd === 'success') {
                setTimeout(function(){location.reload();}, 2500);
            }
        });
    }

});