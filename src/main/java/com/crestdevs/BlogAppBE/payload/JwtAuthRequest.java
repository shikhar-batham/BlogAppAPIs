package com.crestdevs.BlogAppBE.payload;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JwtAuthRequest {

    //we have considered email as username
    private String username;
    private String password;
}
