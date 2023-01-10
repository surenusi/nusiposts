var main = {
  init: function () {
    var _this = this;
    $("#btn-create-user").on("click", function () {
      _this.createUser();
    });
  },
  createUser: function () {
    var data = {
      login: $("#login").val(),
      password: $("#password").val(),
      nickname: $("#nickname").val(),
      email: $("#email").val(),
    };

    $.ajax({
      type: "POST",
      url: "/user",
      contentType: "application/json; charset=utf-8",
      data: JSON.stringify(data),
      success: function () {
        alert("회원이 되신 것을 축하드립니다.");
        location.reload();
      },
      error: function (error) {
        alert("회원가입에 실패하였습니다.");
        console.log(error);
      },
    });
  },
};

main.init();
