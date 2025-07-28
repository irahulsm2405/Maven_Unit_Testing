package com.mockito.repository;

import com.mockito.modal.Employee;

public interface EmployeeRepository {
    public boolean save(Employee employee);
}
