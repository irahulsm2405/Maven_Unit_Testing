package com.mockito.service;

import com.mockito.modal.Employee;

public interface EmployeeService {
    public Employee addNewEmployee(Integer employeeId, String firstName, String lastName, String email);
}
