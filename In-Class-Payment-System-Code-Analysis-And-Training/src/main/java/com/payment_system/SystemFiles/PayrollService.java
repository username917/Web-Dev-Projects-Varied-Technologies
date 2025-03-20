package com.payment_system.SystemFiles;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class PayrollService {
    private final EmployeeRepository employeeRepository;
    private static final double TAX_RATE = 0.2;
    private static final double BONUS_THRESHOLD = 80000;
    private static final double BONUS_PERCENTAGE = 0.05;

    public PayrollService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public double calculateNetSalary(double grossSalary) {
        double tax = grossSalary * TAX_RATE;
        double bonus = (grossSalary > BONUS_THRESHOLD) ? grossSalary * BONUS_PERCENTAGE : 0;
        return grossSalary - tax + bonus;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public void updateEmployeeSalary(int empId, double newSalary) {
        employeeRepository.findById(empId).ifPresent(emp -> {
            emp.setSalary(newSalary);
            employeeRepository.save(emp);
        });
    }
}
