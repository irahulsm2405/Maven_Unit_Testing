package com.tdd.service;

import com.tdd.modal.Employee;

public class EmployeeServiceImpl implements EmployeeService {

    @Override
    public Employee addNewEmployee(String firstName, String lastName, String email, String password, String repeatPassword, int employeeId) {

        //This was used to validate the unit test testCreateEmployee_whenFirstNameIsEmpty_throwsIllegalArgumentException()
        if (firstName == null || firstName.trim().length() == 0) {
            throw new IllegalArgumentException("Employees first name is empty");
        }

        if (password != repeatPassword) {
            throw new IllegalArgumentException("Passwords do not match");
        }

        if (lastName == null || lastName.trim().length() == 0) {
            throw new IllegalArgumentException("Last name is empty");
        }

        return new Employee(firstName, lastName, email, employeeId);
    }
}
