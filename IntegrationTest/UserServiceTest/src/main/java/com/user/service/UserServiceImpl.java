package com.user.service;

import com.user.entity.UserEntity;
import com.user.exception.*;
import com.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository){
        this.userRepository=userRepository;
    }

    //This is the implementation of addNewUser() method from controller class
    @Override
    public UserEntity addNewUser(UserEntity userEntity) {
        UserEntity newUser = userRepository.save(userEntity);
        return newUser;
    }

    //This is the implementation of getUser() method from controller class
    @Override
    public UserEntity getUser(Integer id) {
        UserEntity user = userRepository.findById(id).orElse(null);
        return user;
    }

    //This is the implementation of getAllUsers() method from controller class
    @Override
    public List<UserEntity> getAllUsers() {
        List<UserEntity> allUsers = userRepository.findAll();
        return allUsers;
    }

    //This is the implementation of updateUser() methodfrom controller class
    @Override
    public UserEntity updateUser(Integer id, UserEntity userEntity) {

        userEntity.setId(id);

        //This has been handles in another way in addNewUser() method in controller class
        if(userRepository.findById(id).isEmpty()){
            throw new IdDoesNotExistsException("Id does not exists");
        }else if(userEntity.getUserId()==null || userEntity.getUserId().trim().length()==0){
            throw new UserIdShouldNotBeNullException("User Id cannot be null");
        } else if(userEntity.getFirstName()==null || userEntity.getLastName()==null || userEntity.getFirstName().trim().length()==0 || userEntity.getLastName().trim().length()==0){
            throw new NameShouldNotBeNullException("Firstname or last name should not be null");
        }else if(userEntity.getEmail()==null || userEntity.getEmail().trim().length()==0){
            throw new EmailShouldNotBeNullException("Email cannot be null");
        }else{
            UserEntity updatedUser = userRepository.save(userEntity);
            return updatedUser;
        }
    }

    //Implementation of addMultipleUsers() from controller class
    @Override
    public List<UserEntity> addAllEmployees(List<UserEntity> userEntity) {
        return userRepository.saveAll(userEntity);
    }

    //This is the implementation of patchUser() from controller class
    @Override
    public UserEntity updateUserPartially(int id, Map<String, Object> updates) {
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new IdDoesNotExistsException("User with ID " + id + " not found"));

        updates.forEach((key, value) -> {
            switch (key) {
                case "userId":
                    user.setUserId((String) value);
                    break;
                case "firstName":
                    user.setFirstName((String) value);
                    break;
                case "lastName":
                    user.setLastName((String) value);
                    break;
                case "email":
                    user.setEmail((String) value);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid field: " + key);
            }
        });

        return userRepository.save(user);
    }

    //This is the implementation of deleteUser() from controller class
    @Override
    public void deleteUser(Integer id, UserEntity userEntity) {
        if(userRepository.findById(id).isEmpty()){
            throw new IdDoesNotExistsException("Id does not exists");
        }else{
            userEntity.setId(id);
            userRepository.delete(userEntity);
        }
    }

    //This is the implementation of deleteAllUsers() from controller class
    @Override
    public void deleteAllUsers() {
        userRepository.deleteAll();
    }

    //This is the implementation of getUsersByFirstNameHQL() from controller class
    public List<UserEntity> getUsersByFirstName(String firstName) {
        return userRepository.findByFirstNameHQL(firstName);
    }

    ////This is the implementation of getUsersByEmailSQL() from controller class
    public Optional<UserEntity> getUserByEmail(String email) {
        return userRepository.findByEmailSQL(email);
    }
}
