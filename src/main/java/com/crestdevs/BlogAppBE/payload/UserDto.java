package com.crestdevs.BlogAppBE.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class UserDto {

    private Integer id;

    private String name;

    private String lastName;

    private String email;

    private String password;

    private String userUniqueName;

    private Set<RoleDto> roles = new HashSet<>();
}
