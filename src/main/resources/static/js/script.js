$(document).ready(function () {
    if (window.location.href.includes("login")) {
        $("#loginConfirm").click(function () {
            let lastMailAttempt = $("#username").val();
            localStorage.setItem("lastMailAttempt", lastMailAttempt);
        });
        if (window.location.href.includes("login?error")) {
            $("#username").val(localStorage.getItem("lastMailAttempt"));
        }
    }
});
