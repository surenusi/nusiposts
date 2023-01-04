var main = {
  init: function () {
    var _this = this;
    $("#btn-create").on("click", function () {
      _this.create();
    });
    $("#btn-create-user").on("click", function () {
      _this.createUser();
    });
  },

  create: function () {
    var data = {
      title: $("#title").val(),
      author: $("#author").val(),
      content: $("#content").val(),
    };

    $.ajax({
      type: "POST",
      url: "/post",
      contentType: "application/json; charset=utf-8",
      data: JSON.stringify(data),
      success: function () {
        alert("글을 등록하였습니다.");
        location.reload();
      },
      error: function (error) {
        alert("글 등록에 실패하였습니다.");
        console.log(error);
      },
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
