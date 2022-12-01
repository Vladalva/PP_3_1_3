package ru.kata.spring.boot_security.demo.service;

import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.repositories.RoleRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {
    RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> findAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role findRoleById(Long roleId) {
        Optional<Role> roleFromDb = roleRepository.findById(roleId);
        return roleFromDb.orElse(new Role());
    }

    @Override
    @Transactional
    public boolean saveRole(Role role) {
        Role roleFromDB = roleRepository.findByName(role.getName());

        if (roleFromDB != null) {
            return false;
        }

        roleRepository.save(role);
        return true;
    }

    @Override
    @Transactional
    public boolean deleteRole(Long roleId) {
        if (roleRepository.findById(roleId).isPresent()) {
            roleRepository.deleteById(roleId);
            return true;
        }
        return false;
    }

    @Override
    public Role findRoleByName(String roleName) {
        return roleRepository.findByName(roleName);
    }
}
