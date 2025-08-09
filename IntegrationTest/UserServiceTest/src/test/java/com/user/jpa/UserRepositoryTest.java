package com.user.jpa;

import com.user.entity.UserEntity;
import com.user.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private UserRepository userRepository;

    private UserEntity userEntity;

    @BeforeEach
    void init(){
        userEntity = new UserEntity();
        userEntity.setUserId("rtest");
        userEntity.setFirstName("Rahul");
        userEntity.setLastName("Test");
        userEntity.setEmail("rahul@test.com");
        testEntityManager.persistAndFlush(userEntity);
    }

    @Test
    void testFindByUserId_whenProvidedUserId_shouldReturnUserEntity(){
        //Arrange
        // In lifecycle method

        //Act
        UserEntity storedUser = userRepository.findByUserId("rtest").get();

        //Assert
        Assertions.assertEquals(userEntity.getFirstName(),storedUser.getFirstName(),"Returned name does not match");
    }

    @Test
    void testFindByFirstNameOrLastName_whenValidFirstNameOrLastNameProvided_shouldReturnUser(){
        //Arrange
        // In lifecycle method

        //Act --> will throw error if not present
        UserEntity storedUser = userRepository.findByFirstNameOrLastName("Rahul", "More").get();

        //Assert & Act
        Assertions.assertTrue(userRepository.findByFirstNameOrLastName("Rahul", "More").isPresent());
    }

    @Test
    void testfindByEmailSQL_whenValidEmailProvided_shouldReturnUser(){
        //Arrange
        // In lifecycle method

        //Act
        Optional<UserEntity> storedUser = userRepository.findByEmailSQL("rahul@test.com");

        //Assert
        Assertions.assertEquals(userEntity.getUserId(),storedUser.get().getUserId(),"User id did not match");
    }
}
