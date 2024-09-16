package com.example.amarisemployee.service;

import com.example.amarisemployee.model.Employee;
import com.example.amarisemployee.model.EmployeeDTO;
import com.example.amarisemployee.model.EmployeeListResponse;
import com.example.amarisemployee.model.EmployeeResponse;
import com.example.amarisemployee.utilities.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final RestTemplate restTemplate;
    private final SalaryCalculator salaryCalculator;

    public EmployeeService(RestTemplate restTemplate, SalaryCalculator salaryCalculator) {
        this.restTemplate = restTemplate;
        this.salaryCalculator = salaryCalculator;
    }

    public List<Employee> getAllEmployees() {
        try {
            ResponseEntity<EmployeeListResponse> responseEntity =
                    restTemplate.getForEntity(Constants.API_EMPLOYEES, EmployeeListResponse.class);

            return Optional.ofNullable(responseEntity.getBody())
                    .map(EmployeeListResponse::getData)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT, "No employees found"));
        } catch (RestClientException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error fetching employee list", e);
        }
    }

    public Employee getEmployeeById(int id) {
        try {
            ResponseEntity<EmployeeResponse> responseEntity =
                    restTemplate.getForEntity(Constants.API_EMPLOYEE + id, EmployeeResponse.class);

            return Optional.ofNullable(responseEntity.getBody())
                    .map(EmployeeResponse::getData)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found"));
        } catch (RestClientException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error fetching employee", e);
        }
    }

    public EmployeeDTO toEmployeeDTO(Employee employee) {
        EmployeeDTO dto = new EmployeeDTO();
        dto.setId(employee.getId());
        dto.setName(employee.getName());
        dto.setSalary(employee.getSalary());
        dto.setAge(employee.getAge());
        dto.setAnnualSalary(salaryCalculator.calculateAnnualSalary(employee.getSalary()));
        return dto;
    }
}
