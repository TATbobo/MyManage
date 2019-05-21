package com.tucker.manage.bean;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.*;

@Data
public class User {
    @ApiModelProperty(hidden = true)
    private Integer id;
    @ApiModelProperty(value = "用户名")
    private String name;
    @ApiModelProperty(value = "密码")
    private String password;
    @ApiModelProperty(hidden = true)
    private String role;
}
