package com.user.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

// @WebMvcTest creates context only for controller layer
// @SpringBootTest annotation will create context for all classes

// @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)  //Will mock the web layer

// @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)  //here port will be taken from properties file

/* properties will override properties file */
// @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,properties = {"server.port=8888","hostname=192.168.0.2"})

// @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)  //to start app on a random port

/* Below annotation will tell app to load properties from this newly added file.
 * Both files(application.properties and application-test.properties) will be used by app
 * but for the values defined in application-test.properties will have precedence.
 * If any value is defined in annotation then that will have the highest precedence */
// @TestPropertySource(locations = "/application-test.properties", properties = {"server.port=8881"})

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UsersControllerIntegrationTest_Info {

    //To read port from properties file
    @Value("${server.port}")
    private int serverPort;

    //To read the actual server port on which app is running and assign to variable
    @LocalServerPort
    private int localServerPort;

    @Test
    void contextLoads(){
        System.out.println("Server port used is: "+ serverPort);
        System.out.println("Local server port used is: " + localServerPort);
    }
}
