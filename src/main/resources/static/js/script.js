$(document).ready(function () {
    //Save email in login form after fail
    if (window.location.href.includes("login")) {
        $("#loginConfirm").click(function () {
            let lastMailAttempt = $("#username").val();
            localStorage.setItem("lastMailAttempt", lastMailAttempt);
        });
        if (window.location.href.includes("login?error")) {
            $("#username").val(localStorage.getItem("lastMailAttempt"));
        }
    }

    //Check email availability
    if (window.location.href.includes("registration")) {
        $('#inputEmail').blur(function () {
            // if (!$('#inputEmail')[0].checkValidity()) return;
            checkIfEmailUserAjax();
        });
    }

    //PatientHistoryDataTable
    if (window.location.href.includes("test/history")) {
        initPatientHistoryDataTable();
        initDoctorHistoryDataTable();
    }

    if (window.location.href.includes("admin")) {
        initAdminDataTable();
    }

});

function checkIfEmailUserAjax() {
    $.ajax({
        type: "GET",
        url: "/search_used_mail",
        data: {
            enteredEmail: $('#inputEmail').val()
        },
        dataType: 'json',
        success: function (isUsed) {
            if (isUsed) {
                $('#emailError').text('Email занят');
            } else {
                $('#emailError').text('');
            }
        },
        error: function (e) {
            console.log(e.toString());
        }
    });
}

function initPatientHistoryDataTable() {
    // Setup - add a text input to each footer cell
    $('#patientHistoryTable tfoot th:lt(3)').each(function () {
        let title = $(this).text();
        $(this).html('<input type="text" placeholder=" Поиск' + '" />');
    });

    // DataTable
    let table = $('#patientHistoryTable').DataTable({
        language: {
            "search": "Поиск:",
            "lengthMenu": "Показать _MENU_ записей",
            "info": "Страница _PAGE_ из _PAGES_",
            "infoFiltered": "(отфильтровано из _MAX_ записей)",
            "infoEmpty": "Нет записей",
            "zeroRecords": "Записей, соотвествующих поиску, не найдено",
            "paginate": {
                "first": "Первая",
                "last": "Последняя",
                "next": "Следующая",
                "previous": "Предыдущая"
            },
        },
        "lengthMenu": [ [10, 25, 50, -1], [10, 25, 50, "Все"] ],
        "autoWidth": false,
    });

    // Apply the search
    table.columns().every(function () {
        let that = this;

        $('input', this.footer()).on('keyup change clear', function () {
            if (that.search() !== this.value) {
                that
                    .search(this.value)
                    .draw();
            }
        });
    });
}

function initDoctorHistoryDataTable() {
    // Setup - add a text input to each footer cell
    $('#doctorHistoryTable tfoot th:lt(7)').each(function () {
        let title = $(this).text();
        $(this).html('<input type="text" placeholder=" Поиск' + '" />');
    });

    // DataTable
    let table = $('#doctorHistoryTable').DataTable({
        language: {
            "search": "Поиск:",
            "lengthMenu": "Показать _MENU_ записей",
            "info": "Страница _PAGE_ из _PAGES_",
            "infoFiltered": "(отфильтровано из _MAX_ записей)",
            "infoEmpty": "Нет записей",
            "zeroRecords": "Записей, соотвествующих поиску, не найдено",
            "paginate": {
                "first": "Первая",
                "last": "Последняя",
                "next": "Следующая",
                "previous": "Предыдущая"
            },
        },
        "lengthMenu": [ [10, 25, 50, -1], [10, 25, 50, "Все"] ],
        "autoWidth": true,
    });

    // Apply the search
    table.columns().every(function () {
        let that = this;

        $('input', this.footer()).on('keyup change clear', function () {
            if (that.search() !== this.value) {
                that
                    .search(this.value)
                    .draw();
            }
        });
    });
}

function initAdminDataTable() {
    // Setup - add a text input to each footer cell
    $('#adminTable tfoot th:lt(5):gt(0)').each(function () {
        let title = $(this).text();
        $(this).html('<input type="text" placeholder=" Поиск' + '" />');
    });

    // DataTable
    let table = $('#adminTable').DataTable({
        language: {
            "search": "Поиск:",
            "lengthMenu": "Показать _MENU_ записей",
            "info": "Страница _PAGE_ из _PAGES_",
            "infoFiltered": "(отфильтровано из _MAX_ записей)",
            "infoEmpty": "Нет записей",
            "zeroRecords": "Записей, соотвествующих поиску, не найдено",
            "paginate": {
                "first": "Первая",
                "last": "Последняя",
                "next": "Следующая",
                "previous": "Предыдущая"
            },
        },
        "lengthMenu": [ [10, 25, 50, -1], [10, 25, 50, "Все"] ],
        "autoWidth": true,
    });

    // Apply the search
    table.columns().every(function () {
        let that = this;

        $('input', this.footer()).on('keyup change clear', function () {
            if (that.search() !== this.value) {
                that
                    .search(this.value)
                    .draw();
            }
        });
    });
}