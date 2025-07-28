package com.mockito.service;

import com.mockito.exception.EmployeeServiceException;
import com.mockito.modal.Employee;
import com.mockito.repository.EmployeeRepository;
import com.mockito.repository.EmployeeRepositoryImpl;

import java.util.Properties;

public class EmployeeServiceImpl implements EmployeeService {

    EmployeeRepository employeeRepository;
    EmailNotificationService emailNotificationService;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmailNotificationService emailNotificationService) {
        this.employeeRepository = employeeRepository;
        this.emailNotificationService = emailNotificationService;
    }

    @Override
    public Employee addNewEmployee(Integer employeeId, String firstName, String lastName, String email) {

        Employee employee = new Employee(employeeId, firstName,lastName,email);

        //This would be incorrect as when we are doing a unit test, our test wil actually call this method and save.
        //This will be integration test and unit test
        //for this to work in a unit test properly we need to isolate it from the method
        //EmployeeRepository employeeRepository = new EmployeeRepositoryImpl();
        boolean isEmployeeAlreadyPresent;

        try {
            isEmployeeAlreadyPresent = employeeRepository.save(employee);
        }catch(RuntimeException e){
            throw new EmployeeServiceException("An exception has been thrown by service class");
        }

        try{
            emailNotificationService.sendEmail(employee);
        }catch (RuntimeException e){
            throw new EmployeeServiceException(e.getMessage());
        }

        //this was added to fail the verfiy method in test class with argument 1. Uncomment to see
        //employeeRepository.save(employee);

        if(!isEmployeeAlreadyPresent) throw new EmployeeServiceException("Employee already exists");
        return employee;
    }
}
