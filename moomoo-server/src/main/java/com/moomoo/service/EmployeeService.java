package com.moomoo.service;

import com.moomoo.dto.EmployeeLoginDTO;
import com.moomoo.entity.Employee;

public interface EmployeeService {

    /**
     * Staff login for ice cream shop
     * @param employeeLoginDTO
     * @return
     */
    Employee login(EmployeeLoginDTO employeeLoginDTO);

}
