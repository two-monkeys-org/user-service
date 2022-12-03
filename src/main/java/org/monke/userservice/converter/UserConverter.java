package org.monke.userservice.converter;

import org.monke.userservice.entity.User;
import org.monke.userservice.entity.request.AddUserRequest;
import org.monke.userservice.entity.response.UserResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserConverter {

    public User userOf(AddUserRequest addUserRequest) {
        return new User()
                .setName(addUserRequest.getName())
                .setSurname(addUserRequest.getSurname())
                .setEmail(addUserRequest.getEmail())
                .setPassword(addUserRequest.getPassword())
                .setFirstLogin(true)
                .setRoles("USER");
    }

    public UserResponse toResponse(User user) {
        return new UserResponse()
                .setEmail(user.getEmail())
                .setSurname(user.getSurname())
                .setName(user.getName())
                .setRoles(user.getRoles())
                .setFirstLogin(user.isFirstLogin());
    }

    public List<UserResponse> toResponses(List<User> user) {
        return user.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
}
