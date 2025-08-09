package com.user.controller;

import com.user.entity.UserEntity;
import com.user.exception.UserAlreadyExistsException;
import com.user.repository.UserRepository;
import com.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.user.util.ValidationUtils;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    UserService userService;
    UserRepository userRepository;
    ValidationUtils validationUtils;


    @Autowired
    public UserController(UserService userService, UserRepository userRepository,ValidationUtils validationUtils) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.validationUtils = validationUtils;
    }

    //Used to get a list of all users
    @GetMapping
    public List<UserEntity> getAllUsers(){
       return userService.getAllUsers();
    }

    //Used to get a user with specific id
    //Exception has been handled in service class
    @GetMapping("/{id}")
    public UserEntity getUser(@PathVariable Integer id){
        return userService.getUser(id);
    }

    //Used to add new users in bulk
    @PostMapping("/bulk")
    public List<UserEntity> addMultipleUsers(@RequestBody List<UserEntity> userEntity){
        return userService.addAllEmployees(userEntity);
    }

    //Used to add a new user
    @PostMapping
    public UserEntity addNewUser(@RequestBody UserEntity userEntity){

       if(userRepository.findByUserId(userEntity.getUserId()).isPresent()){
            throw new UserAlreadyExistsException("UserId already exists");
       }
       validationUtils.validateUserEntity(userEntity);
       return this.userService.addNewUser(userEntity);
    }

    //Used to update an existing user
    //Exception has been handled in service class
    @PutMapping("/{id}")
    public UserEntity updateUser(@PathVariable Integer id,@RequestBody UserEntity userEntity){
       return this.userService.updateUser(id,userEntity);
    }

    //Used to partially update a user
    @PatchMapping("/{id}")
    public ResponseEntity<UserEntity> patchUser(
            @PathVariable int id,
            @RequestBody Map<String, Object> updates) {

        UserEntity updatedUser = userService.updateUserPartially(id, updates);
        return ResponseEntity.ok(updatedUser);
    }

    //Used to delete a specific user
    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable Integer id, UserEntity userEntity){
        userService.deleteUser(id,userEntity);
    }

    //Used to delete all users
    @DeleteMapping("/all")
    public void deleteAll(){
        userService.deleteAllUsers();
    }

    //Used to get users by first name using HQL
    @GetMapping("/firstname/{firstName}")
    public List<UserEntity> getUsersByFirstNameHQL(@PathVariable String firstName) {
        return userService.getUsersByFirstName(firstName);
    }

    //Used to get users by email using SQL
    @GetMapping("/email/{email}")
    public Optional<UserEntity> getUsersByEmailSQL(@PathVariable String email) {
        return userService.getUserByEmail(email);
    }
}
