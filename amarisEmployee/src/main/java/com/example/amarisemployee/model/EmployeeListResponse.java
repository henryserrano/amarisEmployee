package com.example.amarisemployee.model;

import lombok.Data;

import java.util.List;

@Data
public class EmployeeListResponse {
    private String status;
    private List<Employee> data;
    private String message;
}
