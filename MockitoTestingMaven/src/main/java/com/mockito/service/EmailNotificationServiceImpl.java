package com.mockito.service;

import com.mockito.modal.Employee;

public class EmailNotificationServiceImpl implements EmailNotificationService{

    @Override
    public void sendEmail(Employee employee) {
        System.out.println("Email has been sent");
    }
}
