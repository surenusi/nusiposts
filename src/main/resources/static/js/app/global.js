var timer;

var isLoginValid = false;

var main = {
  init: function () {
    var _this = this;
    $("#btn-create-user").on("click", function () {
      _this.createUser();
    });
    $("#login").on("keyup paste", function () {
      _this.loginValid($(this));
      _this.loginCheck($(this));
    });
    $("#nickname").on("keyup paste", function () {
      _this.nicknameValid($(this));
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
        alert("회원이 되신 것을 축하드립니다!");
        location.reload();
      },
      error: function (error) {
        alert("서버 요청에 실패하였습니다.");
      },
    });
  },

  //아이디 유효성 검사
  loginValid: function (input) {
    var loginRegType = /^[A-Z|a-z|0-9]{6,12}$/;

    if (loginRegType.test(input.val())) {
      //사용가능한 아이디
      input.removeClass("is-invalid").addClass("is-valid");
      $("#login-feedback").html("");
      $("#login-feedback")
        .removeClass("invalid-feedback")
        .addClass("valid-feedback");
      isLoginValid = true;
    } else {
      //사용할 수 없는 아이디
      input.removeClass("is-valid").addClass("is-invalid");
      $("#login-feedback").html("6~12자 이내의 영문, 숫자만 사용 가능합니다.");
      $("#login-feedback")
        .removeClass("valid-feedback")
        .addClass("invalid-feedback");
      isLoginValid = false;
    }
  },

  //아이디 중복 검사
  loginCheck: function (input) {
    //유효하지 않은 아이디는 중복 검사하지 않음
    if (!isLoginValid || input.val() == "") {
      return;
    }
    if (timer) {
      clearTimeout(timer);
    }
    timer = setTimeout(function () {
      $.ajax({
        type: "GET",
        url: "/user/check/" + input.val(),
        contentType: "application/json; charset=utf-8",
        success: function (result) {
          //중복된 아이디 존재
          if (result == 1) {
            // 사용할 수 없는 아이디
            input.removeClass("is-valid").addClass("is-invalid");
            $("#login-feedback").html("이미 사용중인 아이디입니다.");
            $("#login-feedback")
              .removeClass("valid-feedback")
              .addClass("invalid-feedback");
          }
          //중복된 아이디 존재하지않음
          else {
            // 사용가능한 아이디
            input.removeClass("is-invalid").addClass("is-valid");
            $("#login-feedback").html("사용 가능한 아이디입니다.");
            $("#login-feedback")
              .removeClass("invalid-feedback")
              .addClass("valid-feedback");
          }
        },
        error: function (error) {
          alert("서버 요청에 실패했습니다.");
        },
      });
    }, 200);
  },

  //닉네임 유효성 검사
  nicknameValid: function (input) {
    var nicknameRegType = /^[A-Z|a-z|0-9|ㄱ-ㅎ|가-힣]{2,20}$/;

    if (nicknameRegType.test(input.val())) {
      //유효한 닉네임
      input.removeClass("is-invalid").addClass("is-valid");
      $("#nickname-feedback").html("");
      $("#nickname-feedback").removeClass("invalid-feedback");
    } else {
      //유효하지 않은 닉네임
      input.removeClass("is-valid").addClass("is-invalid");
      $("#nickname-feedback").html(
        "2~20자 이내의 한글, 영문, 숫자만 사용 가능합니다."
      );
      $("#nickname-feedback").addClass("invalid-feedback");
    }
  },
};

main.init();
