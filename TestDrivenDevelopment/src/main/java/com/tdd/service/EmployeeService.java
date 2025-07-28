package com.tdd.service;

import com.tdd.modal.Employee;

public interface EmployeeService {
    Employee addNewEmployee(String firstName, String lastName, String email, String password, String repeatPassword, int employeeId);
}
