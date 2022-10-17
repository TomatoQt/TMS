function checkPassword() {
    var page_password = document.getElementById("password");
    var real_password = document.getElementById("real_password")
    if (page_password.value !== real_password.value) {
        alert("密码错误!!")
        return false;
    }
}