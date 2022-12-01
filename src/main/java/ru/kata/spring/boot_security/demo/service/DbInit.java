package ru.kata.spring.boot_security.demo.service;

import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Service
public class DbInit {
    private final UserService userService;
    private final RoleService roleService;

    public DbInit(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }
    @PostConstruct
    public void generateUsers() {
        Role adminRole = new Role();
        adminRole.setId(1L);
        adminRole.setName("ROLE_ADMIN");
        roleService.saveRole(adminRole);
        Role userRole = new Role();
        userRole.setId(2L);
        userRole.setName("ROLE_USER");
        roleService.saveRole(userRole);

        Set<Role> adminRoles = new HashSet<>();
        adminRoles.add(adminRole);
        adminRoles.add(userRole);
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(userRole);

        User user1 = new User();
        user1.setName("name1");
        user1.setLastName("lastname1");
        user1.setAge(20);
        user1.setEmail("email1@mail.com");
        user1.setPassword("password1");
        user1.setRoles(userRoles);
        userService.saveUser(user1);
        User user2 = new User();
        user2.setName("name2");
        user2.setLastName("lastname2");
        user2.setAge(30);
        user2.setEmail("email2@mail.com");
        user2.setPassword("password2");
        user2.setRoles(userRoles);
        userService.saveUser(user2);
        User admin = new User();
        admin.setName("nameAdmin");
        admin.setLastName("lastnameAdmin");
        admin.setAge(40);
        admin.setEmail("emailAdmin@mail.com");
        admin.setPassword("passwordAdmin");
        admin.setRoles(adminRoles);
        userService.saveUser(admin);
    }
}
