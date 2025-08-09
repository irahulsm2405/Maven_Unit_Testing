package com.user.service;


import com.user.entity.UserEntity;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface UserService {
    public UserEntity addNewUser(UserEntity userEntity);
    public UserEntity getUser(Integer id);
    List<UserEntity> getAllUsers();
    UserEntity updateUser(Integer id,UserEntity userEntity);
    List<UserEntity> addAllEmployees(List<UserEntity> userEntity);
    UserEntity updateUserPartially(int id, Map<String, Object> updates);
    void deleteUser(Integer id, UserEntity userEntity);
    void deleteAllUsers();
    List<UserEntity> getUsersByFirstName(String firstName);
    Optional<UserEntity> getUserByEmail(String email);
}
