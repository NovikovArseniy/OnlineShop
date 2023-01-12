package ru.novikov.shop.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.novikov.shop.model.User;
import ru.novikov.shop.service.UserService;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    @Autowired
    UserService userService;

    @GetMapping
    public String registration(Model model){
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping
    public String addUser(@Valid User user,
                          Errors errors,
                          Model model){
        if (errors.hasErrors()){
            return "registration";
        }
        if (!user.getPassword().equals(user.getPasswordConfirm())){
            model.addAttribute("passwordError", "passwords do not match");
            return "registration";
        }
        if (!userService.saveUser(user)){
            model.addAttribute("usernameError", "a user with the same name already exists");
            return "registration";
        }
        return "redirect:/";
    }
}
