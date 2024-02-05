package com.crestdevs.BlogAppBE.payload;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PostResponse {

    private List<PostDto> content;
    private Integer pageNumber;
    private Integer pageSize;
    private Integer totalElement;
    private Integer totalPages;
    private Boolean lastPage;
}
