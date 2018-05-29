$(document).ready(function() {

    var userRole = $.cookie('userRole'),
        userSelect = $('#userSelect'),
        statusSelect = $('#statusSelect'),
        users = [],
        filteredUsers = [],
        today = new Date(),
        minDate = new Date(today.getFullYear() - 1, today.getMonth(), today.getDate()),
        maxDate = new Date(today.getFullYear() + 1, today.getMonth(), today.getDate());

    $('.toaster').hide();

    if(userRole !== 'MANAGER' && userRole !== 'EMPLOYEE') {
        window.location.replace("http://localhost:8080/platforma/login");
    }

    $('#filterCheck').change(function() {
        $('#daterange').toggleClass('disable');
    });

    $('#daterange').daterangepicker({
        autoApply:true,
        showDropdowns: true,
        minDate: minDate,
        maxDate: maxDate
    });

    $('.go-back').click(function() {
        $('#search-filters').show();
        $('.results').hide();
    });

    $("#search-btn").click(function() {
        search();
    });

    $(document).on("click", ".approve", function(){
        var row = $(this).parent().parent();
        updateStatus(row.index(), "APPROVED");
    });

    $(document).on("click", ".reject", function(){
        var row = $(this).parent().parent();
        updateStatus(row.index(), "REJECTED");
    });

    $('#generate-report').click(function() {
       generateReport();
    });

    function populateDropdown() {
        for(var i = 0; i < users.length; i++) {
            userSelect.append($("<option />").val(users[i].userName).text(users[i].firstName + ' ' + users[i].lastName));
        }
    }

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

    function init() {
        if(userRole === 'MANAGER') {
            getAllUsers();
        }
    }

    function search() {
        var data = {
            userName: userSelect.find(":selected").val(),
            status: statusSelect.find(":selected").text(),
            startDate: $('#daterange').data('daterangepicker').startDate.format('DD-MM-YYYY'),
            endDate: $('#daterange').data('daterangepicker').endDate.format('DD-MM-YYYY'),
            useDateRange: $('#filterCheck').is(':checked')
        };
        $('#search-btn').prop('disabled', true);
        $.ajax({
            url: '/platforma/dashboard',
            type: 'POST',
            data: data,
            success: function(response) {
                filteredUsers = JSON.parse(response);
                $(".results tbody").empty();
                populateTable();
                $('#search-btn').prop('disabled', false);
                $('.results').show();
                $('#search-filters').hide();
            }
        });

    }

    function getParsedDate(date) {
        return (date.dayOfMonth + '/' + date.monthValue + '/' +  date.year);
    }

    function populateTable() {
        for(var i = 0; i < filteredUsers.length; i++) {
            var row = $('<tr></tr>'),
                startDate = getParsedDate(filteredUsers[i].startDate),
                endDate = getParsedDate(filteredUsers[i].endDate);
            row.append('<td>' + filteredUsers[i].firstName + '</td>');
            row.append('<td>' + filteredUsers[i].lastName + '</td>');
            row.append('<td>' + startDate + '</td>');
            row.append('<td>' + endDate + '</td>');
            row.append('<td>' + filteredUsers[i].status + '</td>');
            if(filteredUsers[i].status === 'PENDING') {
                row.append("<td><i class='material-icons mr-1 approve'>" + "done" + "</i><i class='ml-1 material-icons reject'>" + "close" + "</i></td>");
            } else {
                row.append("<td></td>");
            }
            $(".results tbody:last-child").append(row);
        }

    }

    function updateStatus(index, newStatus) {
        var user = filteredUsers[index],
            data = {
                holidayId: user.id,
                newState: newStatus
            };
        $.ajax({
            url: '/platforma/report',
            type: 'POST',
            data: data,
            success: function(response) {
                $('.toaster p').text("Successfully " + newStatus.toLowerCase() + " holiday with id: " + user.id);
                $('.toaster').addClass("success");
                $('.toaster').fadeIn(200);
                search();
                setTimeout(function() {
                    $('.toaster').fadeOut(200);
                    $(".toaster").removeClass("success");
                }, 2000);

            }
        });
    }

    function generateReport() {
        var data = {
            userName: userSelect.find(":selected").val(),
            status: statusSelect.find(":selected").text(),
            startDate: $('#daterange').data('daterangepicker').startDate.format('DD-MM-YYYY'),
            endDate: $('#daterange').data('daterangepicker').endDate.format('DD-MM-YYYY'),
            useDateRange: $('#filterCheck').is(':checked')
        };
        $.ajax({
            url: '/platforma/report',
            type: 'GET',
            data: data,
            success: function(response) {
                window.open('reports/' + response);
            }
        });
    }

    init();

});