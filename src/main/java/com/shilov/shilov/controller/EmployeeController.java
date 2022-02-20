package com.shilov.shilov.controller;

import com.shilov.shilov.entity.Employee;
import com.shilov.shilov.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping()
    public String getAll(Model model) {
        model.addAttribute("employees", employeeService.getAll());
        return "employee/employee-list";
    }

    @GetMapping("/add")
    public String addEndpoint(@ModelAttribute Employee employee, Model model) {
        model.addAttribute("employee", employee);
        return "employee/add-employee";
    }

    @PostMapping("/add-item")
    public String add(@ModelAttribute Employee employee, BindingResult bindingResult, Model model) {
        employeeService.create(employee);
        return "redirect:/employee";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") long id, Model model) {
        Employee employee = employeeService.getById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        model.addAttribute("employee", employee);

        return "employee/edit-employee";
    }

    @PostMapping("/update/{id}")
    public String update(@PathVariable("id") long id, Employee employee, BindingResult result, Model model) {
        employeeService.create(employee);
        return "redirect:/employee";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        employeeService.deleteById(id);
        return "redirect:/employee";
    }
}
