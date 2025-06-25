package com.moomoo.service;

import com.moomoo.dto.EmployeeLoginDTO;
import com.moomoo.entity.Employee;

public interface EmployeeService {

    /**
     * 员工登录
     * @param employeeLoginDTO
     * @return
     */
    Employee login(EmployeeLoginDTO employeeLoginDTO);

}
