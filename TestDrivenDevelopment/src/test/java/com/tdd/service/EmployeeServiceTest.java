package com.tdd.service;

import com.tdd.modal.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class EmployeeServiceTest {

    EmployeeService employeeService;
    String firstName;
    String lastName;
    String email;
    int employeeId;
    String password;
    String repeatPassword;

    //Lifecycle method as we are reusing below values
    @BeforeEach
    void setup() {
        //Arrange
        employeeService = new EmployeeServiceImpl();
        employeeId = 001;
        firstName = "Rahul";
        lastName = "More";
        email = "rahul@gmail.com";
        password = "1234567890";
        repeatPassword = "1234567890";
    }

    //This method was written before writing the service class code
    @DisplayName("Employee object created")
    @Test
    void testCreateEmployee_whenEmployeeDetailsProvided_returnsEmployeeObject() {

        //Act
        Employee employee1 = employeeService.addNewEmployee(firstName, lastName, email, password, repeatPassword, employeeId);

        //Assert
        Assertions.assertNotNull(employee1, "The addNewEmployee() should not have returned null");
        Assertions.assertEquals(firstName, employee1.getFirstName(), "Employees first name isn't correct");
        Assertions.assertEquals(lastName, employee1.getLastName(), "Employees last name isn't correct");
        Assertions.assertEquals(email, employee1.getEmail(), "Employees email name isn't correct");
        Assertions.assertEquals(employeeId, employee1.getEmployeeId(), "Employees Id isn't correct");
    }

    @DisplayName("Empty first name throws exception")
    @Test
    void testCreateEmployee_whenFirstNameIsEmpty_throwsIllegalArgumentException() {
        //Arrange
        String firstName = "";
        String expectedExceptionMessage = "Employees first name is empty";    //This was done as a part of refactor

        //Assert and Act
        IllegalArgumentException thrown = Assertions.assertThrows(IllegalArgumentException.class,
                () -> employeeService.addNewEmployee(firstName, lastName, email, password, repeatPassword, employeeId)
                , "Empty first name should have caused illegal argument exception");

        //This was done as a part of refactor
        Assertions.assertEquals(expectedExceptionMessage, thrown.getMessage(), "Exception error message is not correct");
    }

    @DisplayName("Passwords dont match")
    @Test
    void testCreateEmployee_whenPasswordsDontMatch_throwsIllegalArgumentException(){
        //Arrange
        String repeatPassword = "123456789";
        String expectedExceptionForPassword = "Passwords do not match";

        //Act and Assert

        //This is to check if password does not match
        IllegalArgumentException actaulExceptionThrown = Assertions.assertThrows(IllegalArgumentException.class,
                () -> employeeService.addNewEmployee(firstName, lastName, email, password, repeatPassword, employeeId),
                "This test is used to check if passwords does not match and it seems like it does match");

        //This is to check if exception message does not match
        Assertions.assertEquals(expectedExceptionForPassword,actaulExceptionThrown.getMessage(),"This test will pass only if exception message matches");
    }

    @DisplayName("Check if last name is empty")
    @Test
    void testCreateEmployee_whenLasrNameIsEmpty_throwsIllegalArgumentException(){
        //Arrange
        lastName = "";
        String expectedExceptionMessage = "Last name is empty";
        //Act and Assert

        //This is to checkif last name is empty
        IllegalArgumentException actualExceptionMessage = Assertions.assertThrows(IllegalArgumentException.class,
                ()->employeeService.addNewEmployee(firstName, lastName, email, password, repeatPassword, employeeId),
                "This will pass when lastname is empty");

        //This is to check is exception message is correct
        Assertions.assertEquals(expectedExceptionMessage,actualExceptionMessage.getMessage(),"This is to check if the exception message matches and seems it does not");
    }
}
