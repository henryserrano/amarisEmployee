package com.example.amarisemployee.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SalaryCalculatorTest {
   private SalaryCalculator salaryCalculator;

   @BeforeEach
   void setUp() {
       salaryCalculator = new SalaryCalculator();
   }

   @Test
   void testCalculateAnnualSalary() {
       // Arrange
       double monthlySalary = 5000;
       double expectedAnnualSalary = 5000 * 12;

       // Act
       double actualAnnualSalary = salaryCalculator.calculateAnnualSalary(monthlySalary);

       // Assert
       assertEquals(expectedAnnualSalary, actualAnnualSalary, "The annual salary calculation is incorrect.");
   }

   @Test
   void testCalculateAnnualSalary_WithZeroSalary() {
       // Arrange
       double monthlySalary = 0;
       double expectedAnnualSalary = 0;

       // Act
       double actualAnnualSalary = salaryCalculator.calculateAnnualSalary(monthlySalary);

       // Assert
       assertEquals(expectedAnnualSalary, actualAnnualSalary, "The annual salary calculation is incorrect for zero salary.");
   }


}
