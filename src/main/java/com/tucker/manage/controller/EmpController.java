package com.tucker.manage.controller;

import com.tucker.manage.bean.Employee;
import com.tucker.manage.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
public class EmpController {

    @Autowired
    EmpService empService;

    @GetMapping("/emps")
    public String getAll(Model model){
        Collection<Employee> employees = empService.getAll();
        //放在请求域中
        model.addAttribute("emps",employees);
        return "/tables-datatable";
    }

    @GetMapping("/emp/{id}")
    public String getEmpById(@PathVariable("id") Integer id,Model model){
        Employee employee = empService.getEmpById(id);
        model.addAttribute("emp",employee);
        return "/tables-datatable";
    }

    @GetMapping("/edit")
    public String toEditPage(Model model){
        Collection<Employee> employees = empService.getAll();
        model.addAttribute("emps",employees);
        return "/tables-editable";
    }

    @GetMapping("/insert")
    public String toInsertPage(){
        return "tables-inserttable";
    }

    @PostMapping("/emp")
    public String insertEmp(Employee employee){
        empService.insertEmp(employee);
        return "redirect:/emps";
    }

    @DeleteMapping("/emp/{id}")
    public String deleteEmp(@PathVariable("id") Integer id){
        empService.deleteEmp(id);
        return "redirect:/emps";
    }

    @PutMapping("/emp")
    public String updateEmp(Employee employee){
        empService.upDateEmp(employee);
        return "/tables-datatable";
    }

}
