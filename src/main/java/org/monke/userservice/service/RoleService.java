package org.monke.userservice.service;

import org.monke.userservice.exception.NoRoleException;
import org.monke.userservice.exception.NoUserException;
import org.monke.userservice.entity.request.AddRolesRequest;
import org.monke.userservice.entity.request.ChangeRoleRequest;

import java.util.List;

public interface RoleService {
    void addRole(ChangeRoleRequest changeRoleRequest) throws NoUserException;
    void deleteRole(ChangeRoleRequest changeRoleRequest) throws NoUserException, NoRoleException;
    List<String> getRolesByEmail(String email) throws NoUserException;
    void addAllRoles(AddRolesRequest addRolesRequest) throws NoUserException;
}
