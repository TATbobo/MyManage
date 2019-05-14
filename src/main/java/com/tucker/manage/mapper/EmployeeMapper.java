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
    @Insert("insert into Employee(lastName,email,gender,birth) values(#{lastName},#{gender},#{email},#{birth})")
    public int insertEmp(Employee employee);

    @Update("update employee set departmentName=#{departmentName} where id=#{id}")
    public int upDateEmp(Employee employee);
}
