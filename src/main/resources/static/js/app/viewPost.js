var main = {
  init: function () {
    var _this = this;
    $("#delete-button").on("click", function () {
      _this.deletePost();
    });
    $("#update-button").on("click", function () {
      _this.updatePost();
    });
  },

  deletePost: function () {
    var deleteConfirm = confirm("게시글을 삭제하시겠습니까?");
    if (deleteConfirm) {
      //yes
      alert("삭제했습니다.");
    } else {
      //no
    }
  },

  updatePost: function () {
    var data = {
      title: $("#title").val(),
      content: $("#content").val(),
    };

    var requestUrl = "/updatePost/" + $("#post-id").val();

    $.ajax({
      type: "PUT",
      url: requestUrl,
      contentType: "application/json; charset=utf-8",
      data: JSON.stringify(data),
      success: function () {
        alert("글을 수정하였습니다.");
        location.reload();
      },
      error: function () {
        alert("글 수정에 실패하였습니다.");
        console.log(error);
      },
    });
  },
};

main.init();
