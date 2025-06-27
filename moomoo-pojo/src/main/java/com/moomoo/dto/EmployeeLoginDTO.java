package com.moomoo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(description = "Staff login request data model for ice cream shop")
public class EmployeeLoginDTO implements Serializable {

    @ApiModelProperty("Username")
    private String username;

    @ApiModelProperty("Password")
    private String password;

}
