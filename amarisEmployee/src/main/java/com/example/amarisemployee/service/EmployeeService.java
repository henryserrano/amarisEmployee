package com.example.amarisemployee.service;

import com.example.amarisemployee.exception.EmployeeNotFoundException;
import com.example.amarisemployee.exception.ExternalServiceException;
import com.example.amarisemployee.model.Employee;
import com.example.amarisemployee.model.EmployeeDTO;
import com.example.amarisemployee.model.EmployeeListResponse;
import com.example.amarisemployee.model.EmployeeResponse;
import com.example.amarisemployee.utilities.Constants;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

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
                    .orElseThrow(() -> new EmployeeNotFoundException("Not found employees"));
        } catch (RestClientException e) {
            throw new ExternalServiceException("Error to get the employee list", e);
        }
    }

    public Employee getEmployeeById(int id) {
        try {
            ResponseEntity<EmployeeResponse> responseEntity =
                    restTemplate.getForEntity(Constants.API_EMPLOYEE + id, EmployeeResponse.class);

            return Optional.ofNullable(responseEntity.getBody())
                    .map(EmployeeResponse::getData)
                    .orElseThrow(() -> new EmployeeNotFoundException("Not found employee"));
        } catch (RestClientException e) {
            throw new ExternalServiceException("Error retrieving employee data", e);
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