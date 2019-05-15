package com.tucker.manage.mapper;

import com.tucker.manage.bean.Department;
import com.tucker.manage.bean.Employee;
import org.apache.ibatis.annotations.*;

import java.util.Collection;

public interface EmployeeMapper {

    @Select("select * from employee ")
    public Collection<Employee> getAllEmp();

    @Select("select * from employee where id=#{id}")
    public Employee getDEmpById(Integer id);

    @Delete("delete from employee where id=#{id}")
    public int deleteEmpById(Integer id);

    @Options(useGeneratedKeys = true,keyProperty = "id")
    @Insert("insert into employee(lastName,position,office,age,start_date,salary) values(#{lastName},#{position},#{office},#{age},#{start_date},#{salary})")
    public int insertEmp(Employee employee);

    @Update("update employee set lastName=#{lastName},email=#{email},gender=#{gender},d_id=#{d_id} where id=#{id}")
    public int upDateEmp(Employee employee);
}
