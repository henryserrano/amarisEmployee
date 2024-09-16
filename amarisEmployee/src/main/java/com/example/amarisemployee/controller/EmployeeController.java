package com.example.amarisemployee.controller;

import com.example.amarisemployee.model.Employee;
import com.example.amarisemployee.model.EmployeeDTO;

import com.example.amarisemployee.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/employees")
@CrossOrigin
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<EmployeeDTO> getAllEmployees() {
        return employeeService.getAllEmployees().stream()
                .map(employeeService::toEmployeeDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public EmployeeDTO getEmployeeById(@PathVariable int id) {
        Employee employee = employeeService.getEmployeeById(id);
        return employeeService.toEmployeeDTO(employee);
    }
}
