package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleServiceImpl;
import ru.kata.spring.boot_security.demo.service.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping("/hello/admin")
public class AdminController {
    private UserService userService;
    private RoleServiceImpl roleServiceImpl;

    @Autowired
    public AdminController(UserService userService, RoleServiceImpl roleServiceImpl) {
        this.userService = userService;
        this.roleServiceImpl = roleServiceImpl;
    }

    @GetMapping()
    public String showAllUsers(Model model) {
        model.addAttribute("admin", userService.getAllUsers());
        model.addAttribute("roles", roleServiceImpl.findAll());
        return "admin";
    }

    @GetMapping(value = "/{id}/editUser")
    public String editUser(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.getUser(id));
        model.addAttribute("roles", roleServiceImpl.findAll());
        return "editUser";
    }

    @PatchMapping(value = "/{id}")
    public String update(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/editUser";
        }
        userService.editUser(user);
        return "redirect:/hello/admin";
    }

    @DeleteMapping(value = "/{id}/delete")
    public String delete(@PathVariable("id") long id) {
        userService.removeUserById(id);
        return "redirect:/hello/admin";
    }

    @GetMapping(value = "/{id}/showUser")
    public String showUser(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.getUser(id));
        return "showUser";
    }
}
