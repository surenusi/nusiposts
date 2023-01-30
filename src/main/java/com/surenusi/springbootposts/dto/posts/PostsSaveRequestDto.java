package com.surenusi.springbootposts.dto.posts;

import com.surenusi.springbootposts.domain.Posts;
import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostsSaveRequestDto {

    @NotBlank(message = "제목을 입력해주세요.")
    @Size(min = 2, max = 30, message = "제목을 2~30자 이내로 입력해주세요.")
    private String title;

    @NotBlank(message = "내용을 입력해주세요.")
    private String content;

    @NotBlank(message = "작성자를 입력해주세요.")
    @Size(min = 2, max = 20, message = "작성자를 2~20자 이내로 입력해주세요.")
    private String author;

    @Builder
    public PostsSaveRequestDto(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Posts toEntity() {
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
}
