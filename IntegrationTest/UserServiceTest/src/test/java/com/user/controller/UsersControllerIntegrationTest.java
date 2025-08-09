package com.user.controller;

import com.user.entity.UserEntity;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UsersControllerIntegrationTest {

    //Loads full application context while MockMvc doesnt
    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    @DisplayName("User can be created")
    void testAddNewUser_whenValidDetailsProvided_returnsUserDetails() throws JSONException {
        //Arrange

        // We can pass input as a string or a json object. Anything is fine
        /* String userDetailsRequestJson = """
        {
            "userId": "rtest",
            "firstName": "Rahul",
            "lastName": "Test",
            "email": "rahul@test.com"
        }
        """; */

        JSONObject userDetailsRequestJson = new JSONObject();
        userDetailsRequestJson.put("userId", "rtest");
        userDetailsRequestJson.put("firstName","Rahul");
        userDetailsRequestJson.put("lastName","Test");
        userDetailsRequestJson.put("email","rahul@test.com");

        // Will pass the object as plain string. This will work but could cause issues as it uses plain test/
        // testRestTemplate.postForEntity("/users",userDetailsRequestJson.toString(),String.class);

        /* This will fully simulate client like postman.
         * We can also use this pass additional headers like authentication or description, etc */
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));

        HttpEntity<String> request = new HttpEntity<>(userDetailsRequestJson.toString(),httpHeaders);

        //Act
        ResponseEntity<UserEntity> createdUserDetailsEntity = testRestTemplate.postForEntity("/users", request, UserEntity.class);
        UserEntity createdUserDetails = createdUserDetailsEntity.getBody();

        //Assert
        Assertions.assertEquals(HttpStatus.OK, createdUserDetailsEntity.getStatusCode()); //Asserting status code
        Assertions.assertEquals(userDetailsRequestJson.getString("userId"), createdUserDetails.getUserId(),"Returned userId seems incorrect");
        Assertions.assertEquals(userDetailsRequestJson.getString("firstName"),createdUserDetails.getFirstName(),"Returned firstName seems incorrect");
        Assertions.assertEquals(userDetailsRequestJson.getString("lastName"),createdUserDetails.getLastName(),"Returned lastname seems incorrect");
        Assertions.assertEquals(userDetailsRequestJson.getString("email"),createdUserDetails.getEmail(),"Returned email seems incorrect");
    }

    @Test
    @DisplayName("Test get method")
    void testGetUser_whenIdProvided_shouldReturnUserDetails(){
        //Arrange
        Integer id = 2;

        //Act
        ResponseEntity<UserEntity> userDetails = testRestTemplate.getForEntity("/users/"+id, UserEntity.class);

        //Assert
        System.out.println("Got user: " + userDetails.getBody().getUserId());
        Assertions.assertEquals(HttpStatus.OK, userDetails.getStatusCode());
        Assertions.assertEquals("rtest", userDetails.getBody().getUserId());
        Assertions.assertEquals("Rahul", userDetails.getBody().getFirstName());
        Assertions.assertEquals("Test", userDetails.getBody().getLastName());
        Assertions.assertEquals("rahul@test.com",userDetails.getBody().getEmail());

    }
}
