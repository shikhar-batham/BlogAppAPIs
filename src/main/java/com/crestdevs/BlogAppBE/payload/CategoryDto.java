package com.crestdevs.BlogAppBE.payload;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CategoryDto {

    private Integer categoryId;
    private String categoryTitle;
    private String categoryDescription;
}
