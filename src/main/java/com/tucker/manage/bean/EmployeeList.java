package com.tucker.manage.bean;

import lombok.Data;
import org.hibernate.validator.constraints.EAN;

import java.util.List;

@Data
public class EmployeeList {
    private List<Employee> EmpList;
}
