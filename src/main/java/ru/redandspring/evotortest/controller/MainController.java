package ru.redandspring.evotortest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.redandspring.evotortest.model.User;
import ru.redandspring.evotortest.service.UserService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexander Tretyakov.
 */
@Controller
public class MainController {

    private static final String TEMPLATE = "main";

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(ModelMap model){

        model.addAttribute("titlePage", "Evotor");
        model.addAttribute("currentPage", "index");
        return TEMPLATE;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(ModelMap model){
        model.addAttribute("titlePage", "Registration");
        model.addAttribute("includeView", "registration");
        return TEMPLATE;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registrationPost(@RequestParam String username, @RequestParam String password, ModelMap model, final RedirectAttributes redirectAttributes){
        List<String> messages = new ArrayList<>();
        if (username == null || username.isEmpty()) {
            messages.add("Не заполнено поле логин");
        }

        if (password == null || password.isEmpty()) {
            messages.add("Не заполнено поле пароль");
        }

        if (userService.getByLogin(username) != null){
            messages.add("Такой пользователь уже существует");
        }

        if ( ! messages.isEmpty()){
            model.addAttribute("messages", messages);
            return registration(model);
        }

        long result = userService.save(new User(username, password));
        if (result == 0){
            messages.add("Произошла техническая ошибка");
        }

        if ( ! messages.isEmpty()){
            model.addAttribute("messages", messages);
            return registration(model);
        }

        redirectAttributes.addFlashAttribute("messages", new String[]{"Регистрация прошла успешно"});
        return "redirect:/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(ModelMap model) {

        model.addAttribute("titlePage", "Login");
        model.addAttribute("includeView", "login");
        return TEMPLATE;
    }

    @RequestMapping(value = "/balance", method = RequestMethod.GET)
    public String balance(ModelMap model) {

        String login = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userService.getByLogin(login);

        model.addAttribute("titlePage", "Show balance");
        model.addAttribute("includeView", "balance");
        model.addAttribute("currentPage", "balance");
        model.addAttribute("balance", user.getBalance());
        return TEMPLATE;
    }
}
