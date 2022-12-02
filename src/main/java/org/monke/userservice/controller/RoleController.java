package org.monke.userservice.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.monke.userservice.entity.request.AddRolesRequest;
import org.monke.userservice.entity.request.ChangeRoleRequest;
import org.monke.userservice.exception.NoRoleException;
import org.monke.userservice.exception.NoUserException;
import org.monke.userservice.service.RoleService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
public class RoleController {
    private final RoleService roleService;


    @GetMapping("/{email}")
    @ResponseStatus(HttpStatus.OK)
    public List<String> getRolesByEmail(@PathVariable String email) {
        try {
            return roleService.getRolesByEmail(email);
        } catch(NoUserException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,  e.getMessage());
        }
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public void addRole(@RequestBody ChangeRoleRequest changeRoleRequest) {
        try {
            roleService.addRole(changeRoleRequest);
        } catch (NoUserException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,  e.getMessage());
        }
    }

    @PostMapping( "/addAll")
    @ResponseStatus(HttpStatus.CREATED)
    public void addAllRoles(@RequestBody AddRolesRequest addRolesRequest) {
        try {
            roleService.addAllRoles(addRolesRequest);
        } catch (NoUserException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,  e.getMessage());
        }
    }

    @PostMapping("/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRole(@RequestBody ChangeRoleRequest changeRoleRequest) {
        try {
            roleService.deleteRole(changeRoleRequest);
        } catch (NoUserException | NoRoleException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,  e.getMessage());
        }
    }
}
