package com.crestdevs.BlogAppBE.payload;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PostDto {

    private String postId;
    private String title;
    private String content;
    private String image;
    private Date addedDate;
    private UserDto user ;
}
