package com.tucker.manage.dao;

import com.tucker.manage.bean.Employee;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
public interface EmployeeDao {
    public int updateEmps(List<Employee> empList);
}
