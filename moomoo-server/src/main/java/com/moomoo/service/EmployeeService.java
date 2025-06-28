package com.moomoo.service;

import com.moomoo.dto.EmployeeDTO;
import com.moomoo.dto.EmployeeLoginDTO;
import com.moomoo.entity.Employee;
import org.springframework.beans.BeanUtils;

public interface EmployeeService {

    /**
     * Staff login for ice cream shop
     * @param employeeLoginDTO
     * @return
     */
    Employee login(EmployeeLoginDTO employeeLoginDTO);


    /**
     * Add new employee
     * @param employeeDTO
     */
    void save(EmployeeDTO employeeDTO);
}

