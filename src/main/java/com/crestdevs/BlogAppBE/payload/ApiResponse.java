package com.crestdevs.BlogAppBE.payload;

import lombok.*;

import javax.annotation.processing.Generated;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponse {

    String message;
    Boolean success;
}
