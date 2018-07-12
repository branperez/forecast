package me.brandonmichael.forcast.controllers;

import me.brandonmichael.forcast.models.Security.Password;
import me.brandonmichael.forcast.models.User;
import me.brandonmichael.forcast.models.data.PasswordDao;
import me.brandonmichael.forcast.models.data.UserDao;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;

@Controller
@RequestMapping(value = "/")
public class UserController {

    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordDao passwordDao;

    @RequestMapping(value = "")
    public String index(Model model) {
        return "user/index";
    }

    @RequestMapping(value = "logged-in")
    public String loggedIn(Model model, @RequestParam String username, @RequestParam String password, HttpServletRequest request, HttpServletResponse response) {

        if (!userDao.findByUsername(username).isEmpty()) {
            User user = userDao.findByUsername(username).get(0);
            Password validate = new Password(password, user.getPassword().getSalt());

            byte[] passBytes = user.getPassword().getHash();
            byte[] validationBytes = validate.getHash();

            if (Arrays.equals(passBytes, validationBytes)) {
                HttpSession httpSession = request.getSession();
                httpSession.setAttribute("user", user);
                model.addAttribute("User", user);
                return "user/logged-in";
                /* TODO: forward to user home page (locations management page?)  */
            } else {
                model.addAttribute("error", "Password is incorrect");
                return "user/index";
            }
        } else {
                model.addAttribute("error", "User not found");
                return "user/index";
            }

    }


    @RequestMapping(value = "register", method = RequestMethod.GET)
    public String registerUser(Model model) {
        return "user/register";
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public String registerUser(Model model, @RequestParam String username, @RequestParam String email, @RequestParam String password, @RequestParam String validate) {
        /* TODO: @validate user object/check for errors && passwords against validation */

        if (username.length() < 4 || username.length() > 18) {
            model.addAttribute("error", "Username must be between 4 and 18 characters");
            return "user/register";
        } else if (!password.equals(validate)) {
          model.addAttribute("error", "Passwords must match");
        } else if(!userDao.findByUsername(username).isEmpty()) {
            model.addAttribute("error", "Username is taken");
            return "user/register";
        } else if (!userDao.findByEmail(email).isEmpty()) {
            model.addAttribute("error", "An account with this email already exists");
        }

        User newUser = new User(username, email);
        Password newPassword = new Password(password);
        passwordDao.save(newPassword);
        newUser.setPassword(passwordDao.findOne(newPassword.getId()));
        userDao.save(newUser);



        model.addAttribute("User", newUser);
        return "user/logged-in";

        /* TODO: forward to user home page (need to figure out what this will be) */
    }

}

