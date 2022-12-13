package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.User;
import ru.kata.spring.boot_security.demo.services.RoleService;
import ru.kata.spring.boot_security.demo.services.UserService;
import ru.kata.spring.boot_security.demo.util.UserValidator;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final UserValidator userValidator;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, UserValidator userValidator, RoleService roleService) {
        this.userService = userService;
        this.userValidator = userValidator;
        this.roleService = roleService;
    }

    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("roles", roleService.findAll());
        return "admin/registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("user") @Valid User user, BindingResult  bindingResult) {
        userValidator.validate(user, bindingResult);

        if (bindingResult.hasErrors())
            return "admin/registration";

        userService.save(user);
        return "redirect:/admin";
    }

    @GetMapping("")
    public String redirect() {
        return "redirect:/admin/users";
    }

    @GetMapping("/users")
    public String adminPage(Model model) {
        model.addAttribute("users", userService.findAll());
        return "admin/admin";
    }

    @GetMapping("/users/edit/{id}")
    public String editPage(Model model, @PathVariable("id") long id) {
        model.addAttribute("user", userService.findById(id));
        return "admin/edit";
    }

    @PatchMapping("/users/edit")
    public String edit(@ModelAttribute("user")@Valid User user, BindingResult bindingResult) {
        userValidator.validate(user, bindingResult);
        if(bindingResult.hasErrors()) {
            return "admin/edit";
        }
        userService.save(user);
        return "redirect:/admin/users";
    }

    @DeleteMapping("/users/{id}")
    public String delete(@PathVariable("id") long id) {
        userService.delete(id);
        return "redirect:/admin/users";
    }
}
