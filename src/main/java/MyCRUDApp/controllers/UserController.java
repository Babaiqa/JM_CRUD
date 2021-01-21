package MyCRUDApp.controllers;


import MyCRUDApp.model.User;
import MyCRUDApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "hello", method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {
        List<String> messages = new ArrayList<>();
        messages.add("Hello!");
        messages.add("I'm Spring MVC-SECURITY application");
        messages.add("5.2.0 version by sep'19 ");
        model.addAttribute("messages", messages);
        return "hello";
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }
///////////////////
    @GetMapping()
    public String showAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users/users";
    }

    @GetMapping("/{id}")
    public String showUserById(@PathVariable("id") long id, Model model) {
        model.addAttribute("user", userService.getUser(id));
        return "users/userById";
    }

    @GetMapping("/new")
    public String newUserForm(Model model) {
        model.addAttribute("user", new User());
        return "users/newUser";
    }

    @PostMapping("/add")
    public String addUser(@ModelAttribute User user) {
        userService.saveUser(user);
        return "redirect:/users";
    }

    @GetMapping("/{id}/edit")
    public String editForm(Model model, @PathVariable("id") long id) {
        model.addAttribute("user", userService.getUser(id));
        return "users/editUser";
    }

    @PostMapping("/{id}/save")
    public String updateUser(@ModelAttribute("user") User user, @PathVariable("id") long id) {
        userService.updateUser(user);
        return "redirect:/users";
    }

    @DeleteMapping("/{id}/delete")
    public String deleteUser(@PathVariable("id") long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }

}
