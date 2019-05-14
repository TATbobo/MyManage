package com.tucker.manage.service;

import com.tucker.manage.bean.Employee;
import com.tucker.manage.dao.EmployeeDao;
import com.tucker.manage.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class EmpService implements EmployeeDao {

    @Autowired
    EmployeeMapper employeeMappee;

    @Override
    public Collection<Employee> getAll() {
        Collection<Employee> emps = employeeMappee.getAllEmp();
        return emps;
    }

    @Override
    public Employee getEmpById(Integer id) {
        Employee emp=employeeMappee.getDEmpById(id);
        return emp;
    }

    @Override
    public void insertEmp(Employee employee) {
        employeeMappee.insertEmp(employee);
    }

    @Override
    public void deleteEmp(Integer id) {
        employeeMappee.deleteEmpById(id);
    }

    @Override
    public void upDateEmp(Employee employee) {
        employeeMappee.upDateEmp(employee);
    }
}
