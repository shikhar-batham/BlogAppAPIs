package com.crestdevs.BlogAppBE.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class UserDto {
    private Integer id;

    private String  name;
    private String email;
    private String password;
    private String about;
}
