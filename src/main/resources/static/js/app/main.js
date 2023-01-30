var main = {
  init: function () {
    var _this = this;
    $("#btn-create").on("click", function () {
      _this.create();
    });
  },

  create: function () {
    var data = {
      title: $("#title").val(),
      author: $("#author").val(),
      content: $("#content").val(),
    };

    $("#title-feedback").html("");
    $("#author-feedback").html("");
    $("#content-feedback").html("");

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
        var errorMessage = error.responseJSON;
        $("#title-feedback").html(errorMessage.title);
        $("#author-feedback").html(errorMessage.author);
        $("#content-feedback").html(errorMessage.content);
      },
    });
  },
};

main.init();
