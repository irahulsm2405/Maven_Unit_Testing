package com.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.user.entity.UserEntity;
import com.user.repository.UserRepository;
import com.user.service.UserService;
import com.user.util.ValidationUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/*
 * This class tests the controller layer
 * If we don't mention the controller class name, by default it will load all
 * controllers in my application context. Soif we want to test only one class then add
 * the class name in annotation.
 * We can also exclude any class from it if needed using excludeAutoConfiguration property
 */

@WebMvcTest(controllers = UserController.class)
@Import(ValidationUtils.class)
public class UserControllerLayerTest {

    @Autowired
    private MockMvc mockMvc;

    //older spring boot versions used @MockBean which is deprecated.
    @MockitoBean
    private UserService userService;

    @MockitoBean
    private UserRepository userRepository;

    @Test
    @DisplayName("Single user creation")
    void testAddNewUser_whenValidDetailsProvided_returnsNewUserDetails() throws Exception {

        // This is the body of the request that we send in below config. Like json body we send in postman
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId("irahulsm");
        userEntity.setFirstName("Rahul");
        userEntity.setLastName("More");
        userEntity.setEmail("rahul@test.com");

        when(userService.addNewUser(any(UserEntity.class))).thenReturn(userEntity);

        //Arrange
        // This is to prepare a request that we want to send to the method. Like the postman request
        // This is a post request to /users end point with data body as userEntity
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(userEntity));

        //Act
        // This will execute the request that we prepared above and return as mvcResult.
        // We can read the content or response body from this
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        // This is used to covert the response back to user entity object/return type of controller method
        // to read for assertion
        String contentAsString = mvcResult.getResponse().getContentAsString();
        UserEntity createdUser = new ObjectMapper().readValue(contentAsString, UserEntity.class);

        //Assert
        Assertions.assertEquals(userEntity.getFirstName(),createdUser.getFirstName(),"The returned user first name didn't match");
    }

    @Test
    @DisplayName("First name is not empty")
    void testAddNewUser_whenFirstNameIsNotProvided_returnsNewUserDetails() throws Exception {
        //Arrange
        UserEntity userEntity = new UserEntity();
        userEntity.setUserId("irahulsm");
        userEntity.setFirstName("");
        userEntity.setLastName("More");
        userEntity.setEmail("rahul@test.com");

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(userEntity));

        //Act
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        //Assert
        Assertions.assertEquals(HttpStatus.BAD_REQUEST.value(),mvcResult.getResponse().getStatus());
    }
}
