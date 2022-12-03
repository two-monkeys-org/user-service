package org.monke.userservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.monke.userservice.converter.UserConverter;
import org.monke.userservice.entity.User;
import org.monke.userservice.entity.request.AddUserRequest;
import org.monke.userservice.entity.request.ChangePasswdRequest;
import org.monke.userservice.entity.request.Credentials;
import org.monke.userservice.entity.response.UserResponse;
import org.monke.userservice.exception.EmailTakenException;
import org.monke.userservice.exception.InvalidCredentialsException;
import org.monke.userservice.exception.NoUserException;
import org.monke.userservice.exception.PasswordMismatchException;
import org.monke.userservice.repository.UserRepository;
import org.monke.userservice.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserConverter userConverter;
//    private final EmailService emailService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void addUser(AddUserRequest userRequest) throws EmailTakenException {
        final String email = userRequest.getEmail();

        if(userRepository.existsByEmail(email)) {
            throw new EmailTakenException(String.format("User with email %s already exists", email));
        }

        User user = userConverter.userOf(userRequest);
//        emailService.send(new Mail()
//                .setText("Welcome to comspk. Here is your tmp password: " + user.getPassword())
//                .setSubject("Welcome to HABSAT")
//                .setEmailFrom("noreply@cosmopk")
//                .setEmailTo(user.getEmail()));

        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public List<UserResponse> getUsers() {
        return userConverter.toResponses(userRepository.findAll());
    }

    @Override
    public void modifyPassword(ChangePasswdRequest changePasswdRequest) throws NoUserException, PasswordMismatchException {
        final String email = changePasswdRequest.getEmail();

        if(!userRepository.existsByEmail(email)) {
            throw new NoUserException(String.format("There is no user with %s email", email));
        }

        User user = userRepository.findByEmail(email).get();

        if(!bCryptPasswordEncoder.matches(changePasswdRequest.getOldPassword(), user.getPassword())) {
            throw new PasswordMismatchException("Given passwords do not match!");
        }

        user.setPassword(bCryptPasswordEncoder.encode(changePasswdRequest.getNewPassword()))
            .setFirstLogin(false);

        userRepository.save(user);
    }

    @Override
    public UserResponse getUserByEmail(String email) throws NoUserException {
        return userRepository.findByEmail(email)
                .map(userConverter::toResponse)
                .orElseThrow(()-> new NoUserException("There is no such user with " + email + " email"));
    }

    @Override
    public void deleteUser(String email) throws NoUserException {
        userRepository.findByEmail(email)
                .map(e -> {
                    userRepository.delete(e);
                    return e;
                })
                .orElseThrow(()-> new NoUserException("There is no such user with " + email + " email"));
    }

    @Override
    public List<UserResponse> getUsersByRole(String role) {
        return userRepository.findAll()
                .stream()
                .filter(user -> user.getRoles().contains(role))
                .map(userConverter::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void validateCredentials(Credentials credentials) throws InvalidCredentialsException {
        final User user = userRepository.findByEmail(credentials.getEmail())
                .orElseThrow(() -> new InvalidCredentialsException("Invalid credentials!"));

        if(!bCryptPasswordEncoder.matches(credentials.getPassword(), user.getPassword()))
            throw new InvalidCredentialsException("Invalid credentials!");
    }


}
