package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import web.model.User;
import web.service.UserService;


@Controller
public class UsersController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ModelAndView getAllUsers() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("users");
        modelAndView.addObject("allUsers", userService.getAllUsers());
        return modelAndView;
    }

    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public ModelAndView editPage(@PathVariable("id") int id) {
        User userForEdit = userService.getById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editPage");
        modelAndView.addObject("userForEdit", userForEdit);
        return modelAndView;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView editUser(@ModelAttribute("userForEdit") User user) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/users");
        userService.edit(user);
        return modelAndView;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveUser(@ModelAttribute("userForEdit") User user, Model model) {
        // Model model = new Model();
        //model.setViewName("redirect:/users");
        if (!userService.saveUser(user)) {
            model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
        }
        return "redirect:/users";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public ModelAndView deleteUser(@PathVariable("id") int id) {
        User userForDelete = userService.getById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/users");
        userService.removeUserById(userForDelete);
        return modelAndView;
    }

    @GetMapping(value = "/login")
    public String getLoginPage() {
        return "login";
    }


    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public ModelAndView getUserPage(@PathVariable("id") int id) {
        User user = userService.getById(id);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("user");
        modelAndView.addObject("user", user);
        return modelAndView;

    }
}