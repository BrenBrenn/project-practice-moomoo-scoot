package com.moomoo.service.impl;

import com.moomoo.constant.MessageConstant;
import com.moomoo.constant.PasswordConstant;
import com.moomoo.constant.StatusConstant;
import com.moomoo.dto.EmployeeDTO;
import com.moomoo.dto.EmployeeLoginDTO;
import com.moomoo.entity.Employee;
import com.moomoo.exception.AccountLockedException;
import com.moomoo.exception.AccountNotFoundException;
import com.moomoo.exception.PasswordErrorException;
import com.moomoo.mapper.EmployeeMapper;
import com.moomoo.service.EmployeeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    /**
     * Staff login for ice cream shop
     *
     * @param employeeLoginDTO
     * @return
     */
    public Employee login(EmployeeLoginDTO employeeLoginDTO) {
        String username = employeeLoginDTO.getUsername();
        String password = employeeLoginDTO.getPassword();

        // 1. Query database by username
        Employee employee = employeeMapper.getByUsername(username);

        // 2. Handle various exception cases (username not exists, wrong password, account locked)
        if (employee == null) {
            // Account not found
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }

        //Password comparison
        //Encrypted The password sent from the front end with md5
        password = DigestUtils.md5DigestAsHex(password.getBytes());

        if (!password.equals(employee.getPassword())) {
            // Wrong password
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }

        if (employee.getStatus() == StatusConstant.DISABLE) {
            // Account locked
            throw new AccountLockedException(MessageConstant.ACCOUNT_LOCKED);
        }

        // 3. Return entity object
        return employee;
    }

    /**
     * Add new employee
     * @param employeeDTO
     */
    public void save(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();

        //copy properties
        BeanUtils.copyProperties(employeeDTO, employee);

        //set status of the account; 1-normal, 0-banned; default normal
        employee.setStatus(StatusConstant.ENABLE);

        //set password; default: 123456
        employee.setPassword(DigestUtils.md5DigestAsHex(PasswordConstant.DEFAULT_PASSWORD.getBytes()));

        //set creation time and modification time
        employee.setCreateTime(LocalDateTime.now());
        employee.setUpdateTime(LocalDateTime.now());

        //current login user id 设置当前记录创建人id与修改人的id
        //TODO NEED TO CHANGE AHHHH!!!!! 动态
        employee.setCreateUser(10L);
        employee.setUpdateUser(10L);

        employeeMapper.Insert(employee);
    }

}
