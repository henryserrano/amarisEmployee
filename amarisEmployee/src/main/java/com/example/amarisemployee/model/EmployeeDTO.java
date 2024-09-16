package com.example.amarisemployee.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class EmployeeDTO {
    private int id;
    @JsonProperty("employee_name")
    private String name;
    @JsonProperty("employee_salary")
    private double salary;
    private double annualSalary;
    @JsonProperty("employee_age")
    private String age;
}
