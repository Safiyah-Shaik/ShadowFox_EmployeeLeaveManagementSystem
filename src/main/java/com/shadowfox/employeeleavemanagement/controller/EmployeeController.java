package com.shadowfox.employeeleavemanagement.controller;

import com.shadowfox.employeeleavemanagement.entity.Employee;
import com.shadowfox.employeeleavemanagement.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Employee registerEmployee(@Valid @RequestBody Employee employee) {
        return employeeService.registerEmployee(employee);
    }
}
