var timer;

var isLoginValid = false;
var isEmailValid = false;

var main = {
  init: function () {
    var _this = this;
    $("#btn-create-user").on("click", function () {
      _this.checkForm();
    });
    $("#btn-signIn-user").on("click", function () {
      _this.signInUser();
    });
    $("#login").on("keyup paste", function () {
      _this.loginValid($(this));
      _this.loginCheck($(this));
    });
    $("#nickname").on("keyup paste", function () {
      _this.nicknameValid($(this));
    });
    $("#password").on("keyup paste", function () {
      _this.passwordValid($(this));
    });
    $("#reinput-password").on("keyup paste", function () {
      _this.passwordCompare($(this));
    });
    $("#email").on("keyup paste", function () {
      _this.emailValid($(this));
      _this.emailCheck($(this));
    });
  },

  //폼 유효성 검사
  checkForm: function () {
    if (!$("#login").hasClass("is-valid")) {
      alert("아이디가 유효하지 않습니다.");
      $("#login").focus();
      return;
    }

    if (!$("#nickname").hasClass("is-valid")) {
      alert("닉네임이 유효하지 않습니다.");
      $("#nickname").focus();
      return;
    }

    if (!$("#password").hasClass("is-valid")) {
      alert("비밀번호가 유효하지 않습니다.");
      $("#password").focus();
      return;
    }

    if (!$("#reinput-password").hasClass("is-valid")) {
      alert("비밀번호가 일치하지 않습니다.");
      $("#reinput-password").focus();
      return;
    }

    if (!$("#email").hasClass("is-valid")) {
      alert("이메일이 유효하지 않습니다.");
      $("#email").focus();
      return;
    }

    main.createUser();
  },

  //유저 생성 요청
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
        location.reload();
        alert("회원이 되신 것을 축하드립니다!");
      },
      error: function (error) {
        alert("서버 요청에 실패하였습니다.");
      },
    });
  },

  //유저 로그인
  signInUser: function () {
    var data = {
      login: $("#sign-in-login").val(),
      password: $("#sign-in-password").val(),
    };

    $.ajax({
      type: "POST",
      url: "/sign-in",
      contentType: "application/json; charset=utf-8",
      data: JSON.stringify(data),
      datatype: "json",
      success: function (result) {
        //토큰 local storage 저장
        window.localStorage.setItem("accessToken", result.accessToken);
        location.reload();
        alert("로그인에 성공하셨습니다.");
      },
      error: function (error) {
        alert("서버 요청에 실패하였습니다.");
      },
    });
  },

  //아이디 유효성 검사
  loginValid: function (input) {
    var loginRegType = /^[A-Za-z0-9]{6,12}$/;

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
    if (timer) {
      clearTimeout(timer);
    }
    //유효하지 않은 아이디는 중복 검사하지 않음
    if (!isLoginValid || input.val() == "") {
      return;
    }
    timer = setTimeout(function () {
      $.ajax({
        type: "GET",
        url: "/user/loginCheck/" + input.val(),
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
    }, 500);
  },

  //닉네임 유효성 검사
  nicknameValid: function (input) {
    var nicknameRegType = /^[A-Za-z0-9ㄱ-ㅎ가-힣]{2,20}$/;

    if (nicknameRegType.test(input.val())) {
      //유효한 닉네임
      input.removeClass("is-invalid").addClass("is-valid");
      $("#nickname-feedback").html("사용 가능한 닉네임입니다.");
      $("#nickname-feedback")
        .removeClass("invalid-feedback")
        .addClass("valid-feedback");
    } else {
      //유효하지 않은 닉네임
      input.removeClass("is-valid").addClass("is-invalid");
      $("#nickname-feedback").html(
        "2~20자 이내의 한글, 영문, 숫자만 사용 가능합니다."
      );
      $("#nickname-feedback").addClass("invalid-feedback");
    }
  },

  //비밀번호 유효성 검사
  passwordValid: function (input) {
    var passwordRegType = /^[a-zA-Z0-9`~!@#$%^&*()-_=+]{6,24}$/;

    //password 새로운 입력에 따른 비밀번호 재입력칸 초기화 작업
    $("#reinput-password").removeClass("is-invalid is-valid");
    $("#reinput-password").html("");
    $("#reinput-password-feedback").removeClass(
      "invalid-feedback valid-feedback"
    );
    $("#reinput-password-feedback").html("");

    if (passwordRegType.test(input.val())) {
      //유효한 비밀번호
      input.removeClass("is-invalid").addClass("is-valid");
      $("#password-feedback")
        .removeClass("invalid-feedback")
        .addClass("valid-feedback");
      $("#password-feedback").html("사용 가능한 비밀번호입니다.");
    } else {
      //유효하지 않은 비밀번호
      input.removeClass("is-valid").addClass("is-invalid");
      $("#password-feedback")
        .removeClass("valid-feedback")
        .addClass("invalid-feedback");
      $("#password-feedback").html(
        "6~24자 이내의 영문, 숫자, 특수문자만 사용 가능합니다."
      );
    }
  },

  //비밀번호, 비밀번호 재입력 일치 여부 확인
  passwordCompare: function (input) {
    var password = $("#password").val();

    if (password == input.val()) {
      //비밀번호 일치
      input.removeClass("is-invalid").addClass("is-valid");
      $("#reinput-password-feedback")
        .removeClass("invalid-feedback")
        .addClass("valid-feedback");
      $("#reinput-password-feedback").html("비밀번호가 일치합니다.");
    } else {
      //비밀번호 불일치
      input.removeClass("is-valid").addClass("is-invalid");
      $("#reinput-password-feedback")
        .removeClass("valid-feedback")
        .addClass("invalid-feedback");
      $("#reinput-password-feedback").html("비밀번호가 일치하지 않습니다.");
    }
  },

  //이메일 유효성 검사
  emailValid: function (input) {
    var emailRegType = /^[a-zA-Z0-9+-\_.]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/;

    if (emailRegType.test(input.val())) {
      //유효한 이메일
      input.removeClass("is-invalid").addClass("is-valid");
      $("#email-feedback")
        .removeClass("invalid-feedback")
        .addClass("valid-feedback");
      $("#email-feedback").html("");
      isEmailValid = true;
    } else {
      //유효하지 않은 이메일
      input.removeClass("is-valid").addClass("is-invalid");
      $("#email-feedback")
        .removeClass("valid-feedback")
        .addClass("invalid-feedback");
      $("#email-feedback").html("이메일이 유효하지 않습니다.");
      isEmailValid = false;
    }
  },

  //중복 이메일 체크
  emailCheck: function (input) {
    if (timer) {
      clearTimeout(timer);
    }
    //유효하지 않은 이메일에 대해서는 중복체크 하지 않음
    if (!isEmailValid) {
      return;
    }

    timer = setTimeout(function () {
      $.ajax({
        type: "GET",
        url: "/user/emailCheck/" + input.val(),
        contentType: "application/json; charset=utf-8",
        success: function (result) {
          //중복된 이메일 존재
          if (result == 1) {
            //사용할 수 없는 이메일
            input.removeClass("is-valid").addClass("is-invalid");
            $("#email-feedback")
              .removeClass("valid-feedback")
              .addClass("invalid-feedback");
            $("#email-feedback").html("이미 사용 중인 이메일입니다.");
          } else {
            //사용 가능한 이메일
            input.removeClass("is-invalid").addClass("is-valid");
            $("#email-feedback")
              .removeClass("invalid-feedback")
              .addClass("valid-feedback");
            $("#email-feedback").html("사용 가능한 이메일입니다.");
          }
        },
        error: function (error) {
          alert("서버 요청에 실패했습니다.");
        },
      });
    }, 500);
  },
};

main.init();
