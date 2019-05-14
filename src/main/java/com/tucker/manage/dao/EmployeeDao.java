package com.tucker.manage.dao;

import com.tucker.manage.bean.Employee;

import java.util.Collection;

public interface EmployeeDao {
    public Collection<Employee>  getAll();
    public Employee getEmpById(Integer id);
    public void insertEmp(Employee employee);
    public void deleteEmp(Integer id);
    public void upDateEmp(Employee employee);
}
