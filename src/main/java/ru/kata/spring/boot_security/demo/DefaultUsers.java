package ru.kata.spring.boot_security.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;

import java.util.Set;

@Component
public class DefaultUsers implements CommandLineRunner {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public DefaultUsers(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @Override
    public void run(String... args) throws Exception {
        if(roleService.findByRole("ROLE_USER").isEmpty()) {
            roleService.save(new Role("ROLE_USER"));
        }
        if(roleService.findByRole("ROLE_ADMIN").isEmpty()) {
            roleService.save(new Role("ROLE_ADMIN"));
        }

        Role roleUser = roleService.findByRole("ROLE_USER").get();
        Role roleAdmin = roleService.findByRole("ROLE_ADMIN").get();

        if(userService.findByUsername("user").isEmpty()) {
            userService.save(new User("user", "user@gmail.com", "user", Set.of(roleUser)));
        }
        if(userService.findByUsername("admin").isEmpty()) {
            userService.save(new User("admin", "admin@gmail.com", "admin",
                    Set.of(roleAdmin, roleUser)));
        }
    }

}
