package com.moomoo.mapper;

import com.moomoo.entity.Employee;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface EmployeeMapper {

    /**
     * Search for employees by username
     * @param username
     * @return
     */
    @Select("select * from employee where username = #{username}")
    Employee getByUsername(String username);

    /**
     * insert employee data
     * @param employee
     */


    @Insert(value = "insert into employee(name, username, password, phone, sex, id_number, create_time, update_time, create_user, update_user, status)" +
    "values " +
    "(#{name},#{username}, #{password}, #{phone}, #{sex}, #{idNumber}, #{createTime}, #{updateTime}, #{createUser}, #{updateUser}, #{status})")
    void Insert(Employee employee);
}
