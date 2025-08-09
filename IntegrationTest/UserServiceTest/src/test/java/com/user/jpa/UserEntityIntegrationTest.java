package com.user.jpa;

import com.user.entity.UserEntity;
import jakarta.persistence.PersistenceException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

// Loads only JPA related beans. Uses in memory database. All transactions are rolled back once test is completed.
@DataJpaTest
public class UserEntityIntegrationTest {

    private UserEntity userEntity;

    @Autowired
    private TestEntityManager testEntityManager;

    @BeforeEach
    void init() {
        userEntity = new UserEntity();
        userEntity.setUserId("rtest");
        userEntity.setFirstName("Rahul");
        userEntity.setLastName("Test");
        userEntity.setEmail("rahul@test.com");
    }

    @Test
    void testUserEntity_whenValidDetailsProvided_shouldReturnStoredUserDetails() {
        //Arrange
        //in lifecycle method

        //Act
        UserEntity storedUserEntity = testEntityManager.persistAndFlush(userEntity);

        //Assert
        Assertions.assertTrue(storedUserEntity.getId() > 0);
        Assertions.assertEquals(userEntity.getUserId(), storedUserEntity.getUserId());
        Assertions.assertEquals(userEntity.getUserId(), storedUserEntity.getUserId());
        Assertions.assertEquals(userEntity.getFirstName(), storedUserEntity.getFirstName());
        Assertions.assertEquals(userEntity.getLastName(), storedUserEntity.getLastName());
        Assertions.assertEquals(userEntity.getEmail(), storedUserEntity.getEmail());
    }

    @Test
    void testUserEntity_whenFirstNameISTooLong_shouldThrowException() {
        //Arrange
        userEntity.setFirstName("RahulSanjayMore");

        //Assert and Act
        Assertions.assertThrows(PersistenceException.class,
                () -> testEntityManager.persistAndFlush(userEntity),
                "Should have thrown persistence exception as name is greater than 10 chars");
    }


    // This case should gail when the unique constraint us removed from entity class
    @Test
    void testUserEntity_whenUserIdNotUnique_shouldThrowException() {
        //Arrange
        UserEntity secondUserEntity = new UserEntity();
        secondUserEntity.setUserId("rtest");
        secondUserEntity.setFirstName("Gaurav");
        secondUserEntity.setLastName("Test");
        secondUserEntity.setEmail("gaurav@test.com");
        testEntityManager.persistAndFlush(secondUserEntity);

        userEntity.setUserId("rtest");

        //Assert and Act
        Assertions.assertThrows(PersistenceException.class,
                () -> testEntityManager.persistAndFlush(userEntity),
                "Should have thrown persistence exception as user Id already exists");
    }

}
