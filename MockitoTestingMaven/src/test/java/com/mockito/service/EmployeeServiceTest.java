package com.mockito.service;

import com.mockito.exception.EmailNotificationServiceException;
import com.mockito.exception.EmployeeServiceException;
import com.mockito.modal.Employee;
import com.mockito.repository.EmployeeRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

//by using this import we don't need to call methods using Mockito class name. eg. Mockito.any() can be replaced directly by any()
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {

    //Used to create a mock object
    @Mock
    EmployeeRepository employeeRepository;

    @Mock
    EmailNotificationService emailNotificationService;

    //Above mock object will be injected in this
    @InjectMocks
    EmployeeServiceImpl employeeServiceImpl;

    Integer employeeId;
    String firstName;
    String lastName;
    String email;

    @BeforeEach
    void init() {
        employeeId = 1;
        firstName = "Rahul";
        lastName = "More";
        email = "rahul@test.com";
    }

    @DisplayName("New employee create test")
    @Test
    void testAddNewEmployee_whenDetailsProvided_createNewEmployee() {

        //Arrange
        //We tell mockito that when save method is called on employee repository objecct then return true
        //We can stub the save method with any values we want.
        // Mockito.any(Employee.class) is a built in arguments matcher in which we can pass an employee object.
        when(employeeRepository.save(any(Employee.class))).thenReturn(true); //when its false, it will throw employee already present exception

        //Act
        Employee employee = employeeServiceImpl.addNewEmployee(employeeId, firstName, lastName, email);

        //Assert
        Assertions.assertNotNull(employee, "The addNewEmployee() should not have returned null");
        Assertions.assertEquals(firstName, employee.getFirstName(), "Employees first name isn't correct");
        Assertions.assertEquals(lastName, employee.getLastName(), "Employees last name isn't correct");
        Assertions.assertEquals(email, employee.getEmail(), "Employees email name isn't correct");
        Assertions.assertEquals(employeeId, employee.getEmployeeId(), "Employees Id isn't correct");

        //Verify is used to check how many times method was invoked. We are asking if it ran only one time, if it did not then this test method will fail
        //There are other methods like Mockito.atLeast(1); Mockito.atMost(1); Mockito.atLeastOnce(); Mockito.atMostOnce(); and Mockito.never(); to verify method invocation

        verify(employeeRepository, times(1)).save(any(Employee.class));  //change to 0 if wanna see it fail
        // OR
        verify(employeeRepository).save(any(Employee.class)); //if value is 1 then we can remove it too
    }

    @DisplayName("Save method exception test")
    @Test
    void testAddNewEmployee_whenSaveMethodThrowsException_thenThrowEmployeeServiceException() {
        //Arrange
        //This only works with methods which has a return value. It does not work with void methods
        when(employeeRepository.save(any(Employee.class))).thenThrow(RuntimeException.class);

        //Act and Assert
        assertThrows(EmployeeServiceException.class,
                () -> employeeServiceImpl.addNewEmployee(employeeId, firstName, lastName, email),
                "This method should have thrown Employee Service Exception");
    }

    @DisplayName("Email Notification exception is handled. Stubbing void methods")
    @Test
    void testAddNewEmployee_whenEmailNotificationExceptionThrown_shouldThrowEmployeeServiceException() {
        //Arrange

        //This does compile or thow error as return type is void
        //when(employeeRepository.save(any(Employee.class))).thenThrow(EmployeeServiceException.class);
        //when(emailNotificationService.sendEmail(any(Employee.class))).thenThrow(EmployeeServiceException.class);

        //When return type is void use below
        doThrow(EmailNotificationServiceException.class).when(emailNotificationService).sendEmail(any(Employee.class));

        //This is uded to do nothing when we invoke the sendEmail() method.
        //This has been commented as it overrides above method and fails below code
        //doNothing().when(emailNotificationService).sendEmail(any(Employee.class));

        //Assert and act
        assertThrows(EmployeeServiceException.class,
                () -> employeeServiceImpl.addNewEmployee(employeeId, firstName, lastName, email),
                "Email Notification Exception should be thrown");

        //Verify
        verify(emailNotificationService, times(1)).sendEmail(any(Employee.class));
    }

    @Test
    void testAddNewEmployee_whenNewEmployeeAdded_shouldSendEmail(){
        //Arrange
        EmailNotificationServiceImpl emailNotificationSpy = spy(new EmailNotificationServiceImpl());
        EmployeeServiceImpl employeeService = new EmployeeServiceImpl(employeeRepository, emailNotificationSpy);

        //This calls mock
        when(employeeRepository.save(any(Employee.class))).thenReturn(true);
        //This will call the real method and not the mock one
        doCallRealMethod().when(emailNotificationSpy).sendEmail(any(Employee.class));
        //Act
        employeeService.addNewEmployee(employeeId, firstName, lastName, email);

        //Assert
        verify(emailNotificationSpy,times(1)).sendEmail(any(Employee.class));
    }
}
