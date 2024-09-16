package com.example.amarisemployee.service;

import org.springframework.stereotype.Service;

@Service
public class SalaryCalculator {

    public double calculateAnnualSalary(double monthlySalary) {
        return monthlySalary * 12;
    }
}
