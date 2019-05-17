package com.tucker.manage.service;

import com.tucker.manage.bean.Employee;
import com.tucker.manage.dao.EmployeeDao;
import com.tucker.manage.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class EmpService {

    @Autowired
    EmployeeMapper employeeMappee;


    public Collection<Employee> getAll() {
        Collection<Employee> emps = employeeMappee.getAllEmp();
        return emps;
    }


    public Employee getEmpById(Integer id) {
        Employee emp=employeeMappee.getDEmpById(id);
        return emp;
    }


    public void insertEmp(Employee employee) {
        employeeMappee.insertEmp(employee);
    }


    public void deleteEmp(Integer id) {
        employeeMappee.deleteEmpById(id);
    }


    public void upDateEmp(Employee employee) {
        employeeMappee.upDateEmp(employee);
    }
}
