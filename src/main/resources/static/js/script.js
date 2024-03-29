$(document).ready(function () {
  //Save email in login form after fail
  if (window.location.href.includes("login")) {
    $("#loginConfirm").click(function () {
      let lastMailAttempt = $("#username").val()
      localStorage.setItem("lastMailAttempt", lastMailAttempt)
    })
    if (window.location.href.includes("login?error")) {
      $("#username").val(localStorage.getItem("lastMailAttempt"))
    }
  }

  //Check email availability
  if (window.location.href.includes("registration")) {
    $('#inputEmail').blur(function () {
      checkIfEmailUserAjax()
    })
  }

  //PatientHistoryDataTable
  if (window.location.href.includes("test/history")) {
    initPatientHistoryDataTable()
    initDoctorHistoryDataTable()

    let table = $('#patientHistoryTable').DataTable()

    // Event listener to the two range filtering inputs to redraw on input
    $('#min, #max').change(function () {
      table.draw()
    })
  }

  //AdminDataTable
  if (window.location.href.includes("admin/users")) {
    initAdminDataTable()
  }

  //PatientsDataTable
  if (window.location.href.includes("patient/profile")) {
    initPatientHistoryDataTable()

    let table = $('#patientHistoryTable').DataTable()

    // Event listener to the two range filtering inputs to redraw on input
    $('#min, #max').change(function () {
      table.draw()
    })
  }

  if ($('#myBar').length) {

// When the user scrolls the page, execute myFunction
    window.onscroll = function () {
      progressBar()
    }
  }
  if (window.location.href.includes("patients")) {
    initPatientDataTable()

    let table = $('#patientsTable').DataTable()

    // Event listener to the two range filtering inputs to redraw on input
    $('#min, #max').change(function () {
      table.draw()
    })
  }

  //TestValidation
  if ($('#testForm').length !== 0) {
    checkBoxValidation()
    $("input:checkbox").change()

    $('#operateTestButton').click(function (e) {
      // e.preventDefault()
    })

    $('#saveTestButton').click(function (e) {
      deleteRequiredAttFromTestForm()
      // e.preventDefault();
    })
  }
})

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
        $('#emailError').text('Email занят')
      } else {
        $('#emailError').text('')
      }
    },
    error: function (e) {
      console.log(e.toString())
    }
  })
}

function initPatientHistoryDataTable() {
  // Setup - add a text input to each footer cell
  /*$('#patientHistoryTable tfoot th:lt(3)').each(function () {
    let title = $(this).text()
    $(this).html('<input type="text" placeholder=" Поиск' + '" />')

  })*/
// Apply the search
  /*table.columns().every(function () {
    let that = this

    $('input', this.footer()).on('keyup change clear', function () {
      if (that.search() !== this.value) {
        that
          .search(this.value)
          .draw()
      }
    })
  })*/

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
    initComplete: function () {
      this.api().columns([1, 2, 3]).every(function () {
        var column = this
        var select = $('<select><option value=""></option></select>')
          .appendTo($(column.footer()).empty())
          .on('change', function () {
            var val = $.fn.dataTable.util.escapeRegex(
              $(this).val()
            )

            column
              .search(val ? '^' + val + '$' : '', true, false)
              .draw()
          })

        column.data().unique().sort().each(function (d, j) {
          select.append('<option value="' + d + '">' + d + '</option>')
        })
      })
    },
    /*ajax: {url:"http://localhost:8080/load_test_history", dataSrc:""},
    "columns": [
      {"data": "date"},
      {"data": "type.name"},
      {"data": "processed"},
      {"data": "diagnosis.text"},
      {"data": "id"},
    ],columnDefs: [ {
      targets: [4],
      data: "id",
      "render": function (data, type, row, meta) {
        return '<a href="/test/view?questionnaireId=' + row.id + '">' + 'view' + '</a>';
      }
    }],*/
    "lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "Все"]],
    "autoWidth": false
  })

  $.fn.dataTable.ext.search.push(
    function (settings, data, dataIndex) {

      var min = Date.parse($('#min').val())
      var max = Date.parse($('#max').val())
      var date = moment(data[0], 'DD.MM.YY HH:mm', false).valueOf() // CHANGE to date column for each table

      if ((isNaN(min) && isNaN(max)) ||
        (isNaN(min) && date <= max) ||
        (min <= date && isNaN(max)) ||
        (min <= date && date <= max)) {
        return true
      }
      return false
    }
  )
}


function initDoctorHistoryDataTable() {
  // Setup - add a text input to each footer cell
  $('#doctorHistoryTable tfoot th:lt(7)').each(function () {
    let title = $(this).text()
    $(this).html('<input class="col-8" type="text" placeholder=" Поиск' + '" />')
  })

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
    fixedColumns: true,
    "lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "Все"]],
    "autoWidth": false,
  })

  // Apply the search
  table.columns().every(function () {
    let that = this

    $('input', this.footer()).on('keyup change clear', function () {
      if (that.search() !== this.value) {
        that
          .search(this.value)
          .draw()
      }
    })
  })
}

function initAdminDataTable() {
  // Setup - add a text input to each footer cell
  $('#adminTable tfoot th:lt(5):gt(0)').each(function () {
    let title = $(this).text()
    $(this).html('<input class="d-none d-md-inline" type="text" placeholder=" Поиск' + '" />')
  })

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
    "columnDefs": [
      {"width": "20%", "targets": 4}
    ],
    "lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "Все"]],
    "autoWidth": false,
    responsive: true
  })

  // Apply the search
  table.columns().every(function () {
    let that = this

    $('input', this.footer()).on('keyup change clear', function () {
      if (that.search() !== this.value) {
        that
          .search(this.value)
          .draw()
      }
    })
  })
}

function initPatientDataTable() {
  // Setup - add a text input to each footer cell
  $('#patientsTable tfoot th').filter(function (index) {
    let neededIndexes = [1, 2, 5]
    return neededIndexes.includes(index)
  }).each(function () {
    let title = $(this).text()
    $(this).html('<input type="text" placeholder=" Поиск' + '" />')
  })

  // DataTable
  let table = $('#patientsTable').DataTable({
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
    initComplete: function () {
      this.api().columns([3, 4]).every(function () {
        var column = this
        var select = $('<select><option value=""></option></select>')
          .appendTo($(column.footer()).empty())
          .on('change', function () {
            var val = $.fn.dataTable.util.escapeRegex(
              $(this).val()
            )

            column
              .search(val ? '^' + val + '$' : '', true, false)
              .draw()
          })

        column.data().unique().sort().each(function (d, j) {
          select.append('<option value="' + d + '">' + d + '</option>')
        })
      })
    },
    "lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "Все"]],
    "autoWidth": true,
  })

  // Apply the search
  table.columns().every(function () {
    let that = this

    $('input', this.footer()).on('keyup change clear', function () {
      if (that.search() !== this.value) {
        that
          .search(this.value)
          .draw()
      }
    })
  })

  //Date range apply
  $.fn.dataTable.ext.search.push(
    function (settings, data, dataIndex) {

      var min = Date.parse($('#min').val())
      var max = Date.parse($('#max').val())
      var date = moment(data[1], 'DD.MM.YY HH:mm', false).valueOf() // CHANGE to date column for each table

      if ((isNaN(min) && isNaN(max)) ||
        (isNaN(min) && date <= max) ||
        (min <= date && isNaN(max)) ||
        (min <= date && date <= max)) {
        return true
      }
      return false
    }
  )
}


function checkBoxValidation() {
  let allCheckboxes = $("input:checkbox")

  allCheckboxes.on('change', function (e) {
    let changedCheckboxName = this.name
    let changedCheckboxGroup = []

    allCheckboxes.each(function (i) {
      if ($(allCheckboxes[i]).attr('name').split('.')[0] === changedCheckboxName.split('.')[0]) {
        changedCheckboxGroup.push(allCheckboxes[i])
      }
    })

    if ($(changedCheckboxGroup).is(':checked')) {
      $(changedCheckboxGroup).removeAttr('required')
    } else {
      $(changedCheckboxGroup).attr('required', 'required')
    }
  })
}

function deleteRequiredAttFromTestForm() {
  let allInputs = $("input")
  allInputs.removeAttr('required')
}


function loadQuestionnaire(e) {
  e.preventDefault()
  $.ajax({
    type: "GET",
    url: "/load_test_history",
    data: {
      id: 1,
      page: 2,
    },
    dataType: 'json',
    success: function (data) {
      console.log(data)
    },
    error: function (e) {
      console.log(e.toString())
    }
  })
}


function progressBar() {
  let winScroll = document.body.scrollTop || document.documentElement.scrollTop
  let height = document.documentElement.scrollHeight - document.documentElement.clientHeight
  let scrolled = (winScroll / height) * 100
  document.getElementById("myBar").style.width = scrolled + "%"
}