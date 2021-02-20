package MyCRUDApp.controllers;


import MyCRUDApp.model.Role;
import MyCRUDApp.model.User;
import MyCRUDApp.service.RoleService;
import MyCRUDApp.service.RoleServiceImpl;
import MyCRUDApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminCotroller {

    private UserService userService;
    private RoleService roleService;

    @Autowired
    public AdminCotroller(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping("")
    public String showAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users";
    }

    @GetMapping("/{id}")
    public String showUserById(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "showUser";

    }

    @GetMapping("/new")
    public String newUserForm(Model model) {
        model.addAttribute("user", new User());
        return "newUser";
    }

    @PostMapping("/add")
    public String addUser(@ModelAttribute User user, @RequestParam(value = "selectRoles[]") String[] arr ) {
        Set<Role> setOfRoles = new HashSet<>();

        for (String s : arr) {
            setOfRoles.add(roleService.getRoleById(Integer.valueOf(s)));        }

        user.setRoles(setOfRoles);
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @GetMapping("/{id}/edit")
    public String editForm(Model model, @PathVariable("id") long id) {

        model.addAttribute("user", userService.getUserById(id));


        return "editUser";
    }

    @PostMapping("/{id}/update")
    public String updateUser(@ModelAttribute("user") User user, @PathVariable("id") long id) {
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @PostMapping("/{id}/delete")
    public String deleteUser(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }


}
