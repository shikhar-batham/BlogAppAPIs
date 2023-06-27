package com.crestdevs.BlogAppBE.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class UserDto {

    private Integer id;

    @NotNull
    private String  name;
    @Email
    private String email;
    @NotNull
    private String password;
    @NotNull
    private String about;
}
