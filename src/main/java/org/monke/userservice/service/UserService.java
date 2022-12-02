package org.monke.userservice.service;

import org.monke.userservice.exception.EmailTakenException;
import org.monke.userservice.exception.InvalidCredentialsException;
import org.monke.userservice.exception.NoUserException;
import org.monke.userservice.exception.PasswordMismatchException;
import org.monke.userservice.entity.request.AddUserRequest;
import org.monke.userservice.entity.request.ChangePasswdRequest;
import org.monke.userservice.entity.request.Credentials;
import org.monke.userservice.entity.response.UserResponse;

import java.util.List;

public interface UserService {
    List<UserResponse> getUsers();
    UserResponse getUserByEmail(String email) throws NoUserException;
    void addUser(AddUserRequest userRequest) throws EmailTakenException;
    void deleteUser(String email) throws NoUserException;
    void modifyPassword(ChangePasswdRequest changePasswdRequest) throws NoUserException, PasswordMismatchException;
    List<UserResponse> getUsersByRole(String role);
    void validateCredentials(Credentials credentials) throws InvalidCredentialsException;
}
