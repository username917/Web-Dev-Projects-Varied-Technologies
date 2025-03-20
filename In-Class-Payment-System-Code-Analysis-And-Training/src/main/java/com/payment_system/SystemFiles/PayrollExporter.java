package com.payment_system.SystemFiles;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class PayrollExporter {
    private static final String FILE_PATH = "payroll_data.txt";
    private final EmployeeRepository employeeRepository;

    public PayrollExporter(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public void exportPayrollData() throws IOException {
        List<Employee> employees = employeeRepository.findAll();
        BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true));

        for (Employee emp : employees) {
            writer.write(emp.getId() + "," + emp.getName() + "," + emp.getSalary());
            writer.newLine();
        }

        writer.close();
    }
}
