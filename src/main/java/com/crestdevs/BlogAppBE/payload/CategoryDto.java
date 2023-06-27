package com.crestdevs.BlogAppBE.payload;

import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDto {

    Integer categoryId;
    String categoryTitle;
    String categoryDescription;
}
