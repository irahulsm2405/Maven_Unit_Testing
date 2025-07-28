package com.tdd.modal;

public class Employee {

    private String firstName;
    private String lastName;
    private String email;
    private int employeeId;

    public Employee(String firstName, String lastName, String email, int employeeId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.employeeId = employeeId;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public int getEmployeeId(){
        return employeeId;
    }
}
