package com.moomoo.service.impl;

import com.moomoo.constant.MessageConstant;
import com.moomoo.constant.StatusConstant;
import com.moomoo.dto.EmployeeLoginDTO;
import com.moomoo.entity.Employee;
import com.moomoo.exception.AccountLockedException;
import com.moomoo.exception.AccountNotFoundException;
import com.moomoo.exception.PasswordErrorException;
import com.moomoo.mapper.EmployeeMapper;
import com.moomoo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    /**
     * Employee Login
     *
     * @param employeeLoginDTO
     * @return
     */
    public Employee login(EmployeeLoginDTO employeeLoginDTO) {
        String username = employeeLoginDTO.getUsername();
        String password = employeeLoginDTO.getPassword();

        //1. Query data in the database based on username
        Employee employee = employeeMapper.getByUsername(username);

        //2. Handle various abnormal situations
        // (username does not exist, password is incorrect, account is locked)
        if (employee == null) {
            //Account does not exist
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }

        //Password comparison
        //Encrypted The password sent from the front end with md5
        password = DigestUtils.md5DigestAsHex(password.getBytes());

        if (!password.equals(employee.getPassword())) {
            //Wrong password
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }

        if (employee.getStatus() == StatusConstant.DISABLE) {
            //Account locked
            throw new AccountLockedException(MessageConstant.ACCOUNT_LOCKED);
        }

        //3. Return entity object
        return employee;
    }

}
