package com.mockito.repository;

import com.mockito.modal.Employee;

import java.util.HashMap;
import java.util.Map;

public class EmployeeRepositoryImpl implements EmployeeRepository{

    Map<Integer,Employee> employees = new HashMap<>();

    @Override
    public boolean save(Employee employee) {
        boolean returnValue = false;

        if(!employees.containsKey(employee.getEmployeeId())){
            employees.put(employee.getEmployeeId(),employee);
            returnValue = true;
        }
        return returnValue;
    }
}
