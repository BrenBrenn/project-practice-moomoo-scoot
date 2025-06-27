package com.moomoo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Staff login response data format for ice cream shop")
public class EmployeeLoginVO implements Serializable {

    @ApiModelProperty("Staff ID")
    private Long id;

    @ApiModelProperty("Username")
    private String userName;

    @ApiModelProperty("Staff Name")
    private String name;

    @ApiModelProperty("JWT Token")
    private String token;

}
