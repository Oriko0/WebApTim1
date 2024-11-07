package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;


@Controller
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String showAllUsers(Model model) {
        model.addAttribute("allUsers", userService.getAllUsers());
        return "allusers";
    }
    @GetMapping("/addNewUser")
    public String addNewUser(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "user-info";
    }
    @GetMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/";

    }
    @GetMapping("/addNewUpdateUser")
    public String addNewUpdateUser(@RequestParam("usrId") int id, Model model) {
        User user = userService.getUser(id);
        model.addAttribute("user", user);
        return "user-update";
    }
    @GetMapping("/updateInfo")
    public String updateInfo(@ModelAttribute("user") User user) {
        userService.updateInfo(user);
        return "redirect:/";
    }
    @GetMapping("/deleteUser")
    public String deleteUser(@RequestParam("usrId") int id) {
        userService.deleteUser(id);
        return "redirect:/";
    }
}
