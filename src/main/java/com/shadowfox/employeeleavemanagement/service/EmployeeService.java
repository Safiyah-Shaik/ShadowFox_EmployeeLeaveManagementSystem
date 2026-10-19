package com.shadowfox.employeeleavemanagement.service;

import com.shadowfox.employeeleavemanagement.entity.Employee;
import com.shadowfox.employeeleavemanagement.repository.EmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;

    public EmployeeService(EmployeeRepository employeeRepository,
                       PasswordEncoder passwordEncoder) {
        this.employeeRepository = employeeRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public Employee registerEmployee(Employee employee) {
    if (employeeRepository.existsByEmail(employee.getEmail())) {
        throw new IllegalArgumentException("An employee with this email already exists");
    }

        employee.setPassword(passwordEncoder.encode(employee.getPassword()));

        return employeeRepository.save(employee);
    }
}
