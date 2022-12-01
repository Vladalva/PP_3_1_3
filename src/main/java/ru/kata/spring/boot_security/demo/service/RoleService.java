package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;

public interface RoleService {
    List<Role> findAllRoles();

    Role findRoleById(Long roleId);

    boolean saveRole(Role role);

    boolean deleteRole(Long roleId);

    Role findRoleByName(String roleName);
}
