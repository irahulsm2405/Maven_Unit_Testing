package com.user.util;

import com.user.entity.UserEntity;
import com.user.exception.EmailShouldNotBeNullException;
import com.user.exception.NameShouldNotBeNullException;
import com.user.exception.UserIdShouldNotBeNullException;
import org.springframework.stereotype.Component;

@Component
public class ValidationUtils {

    public void validateUserEntity(UserEntity userEntity) {
        if (isBlank(userEntity.getUserId())) {
            throw new UserIdShouldNotBeNullException("User Id cannot be null or empty");
        }
        if (isBlank(userEntity.getFirstName()) || isBlank(userEntity.getLastName())) {
            throw new NameShouldNotBeNullException("Firstname or lastname cannot be null or empty");
        }
        if (isBlank(userEntity.getEmail())) {
            throw new EmailShouldNotBeNullException("Email cannot be null or empty");
        }
    }

    private boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}
