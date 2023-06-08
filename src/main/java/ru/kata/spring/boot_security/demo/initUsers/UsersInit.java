package ru.kata.spring.boot_security.demo.initUsers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleServiceImpl;
import ru.kata.spring.boot_security.demo.service.UserServiceImpl;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class UsersInit {
    private RoleServiceImpl roleService;
    private UserServiceImpl userService;

    @Autowired
    public UsersInit(RoleServiceImpl roleService, UserServiceImpl userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @PostConstruct
    public void init() {
        roleService.save(new Role("ROLE_ADMIN"));
        roleService.save(new Role("ROLE_USER"));
        Role admin = roleService.findRoleByRole("ROLE_ADMIN");
        Role user = roleService.findRoleByRole("ROLE_USER");

        userService.saveUser(new User("Severus", "Snape", 38, "123",
                "lilly_my_angel@gmail.com", List.of(admin)));
        userService.saveUser(new User("Remus", "Lupin", 40, "321",
                "moon_is_scarry@gmail.com", List.of(user)));
        userService.saveUser(new User("Minerva", "McGonagall", 55, "111",
                "head_of_gryffindor@gmail.com", List.of(admin, user)));
    }
}
