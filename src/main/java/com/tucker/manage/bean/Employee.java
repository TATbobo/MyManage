package com.tucker.manage.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
public class Employee {

	private Integer id;
    private String lastName;
    private String position;
    private String office;
    private Integer age;
    private String start_date;
    //1 male, 0 female
    private String salary;


}
