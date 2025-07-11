package com.moomoo.controller.admin;

import com.moomoo.constant.JwtClaimsConstant;
import com.moomoo.dto.EmployeeDTO;
import com.moomoo.dto.EmployeeLoginDTO;
import com.moomoo.entity.Employee;
import com.moomoo.properties.JwtProperties;
import com.moomoo.result.Result;
import com.moomoo.service.EmployeeService;
import com.moomoo.utils.JwtUtil;
import com.moomoo.vo.EmployeeLoginVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jdk.jpackage.internal.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Staff management for ice cream shop
 */
@RestController
@RequestMapping("/admin/employee")
@Slf4j
@Api(tags = "Employee Management related interface")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private JwtProperties jwtProperties;

    /**
     * Staff login
     *
     * @param employeeLoginDTO
     * @return
     */


    @PostMapping("/login")
    @ApiOperation(value = "Employee login")
    public Result<EmployeeLoginVO> login(@RequestBody EmployeeLoginDTO employeeLoginDTO) {
        log.info("Employee login: {}", employeeLoginDTO);

        Employee employee = employeeService.login(employeeLoginDTO);

        //After successful login, generate jwt token
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.EMP_ID, employee.getId());
        String token = JwtUtil.createJWT(
                jwtProperties.getAdminSecretKey(),
                jwtProperties.getAdminTtl(),
                claims);

        EmployeeLoginVO employeeLoginVO = EmployeeLoginVO.builder()
                .id(employee.getId())
                .userName(employee.getUsername())
                .name(employee.getName())
                .token(token)
                .build();

        return Result.success(employeeLoginVO);
    }

    /**
     * Staff logout
     * @return
     */
    @PostMapping("/logout")
    @ApiOperation("Employee logout")
    public Result<String> logout() {
        return Result.success();
    }


    /**
     * Add new employee
     * @param employeeDTO
     * @return
     */
    @PostMapping
    @ApiOperation("Add new employee")
    public Result save(@RequestBody EmployeeDTO employeeDTO) {
        log.info("Add new employee: {}",employeeDTO);
        employeeService.save(employeeDTO);
        return null;
    }
}
