package com.tucker.manage.bean;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class User {

    private Integer id;
    private String name;
    private String password;
    private String role;
}
