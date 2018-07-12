package me.brandonmichael.forcast.controllers;

import me.brandonmichael.forcast.models.Location;
import me.brandonmichael.forcast.models.User;
import me.brandonmichael.forcast.models.data.LocationDao;
import me.brandonmichael.forcast.models.data.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "location")
public class LocationController {

    @Autowired
    private LocationDao locationDao;

    @Autowired
    private UserDao userDao;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(Model model, HttpServletRequest request, HttpServletResponse response) {
        HttpSession httpSession = request.getSession(false);

        User user = null;
        if(httpSession != null){
            user = (User) httpSession.getAttribute("user");
        }

        if(user!=null){
            user.getLocations();
            return "location/index";
        }

        return "redirect:/";

    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String location(Model model, @RequestParam String latSave, @RequestParam String lngSave, @RequestParam String city, @RequestParam String state, @RequestParam String country, HttpServletRequest request, HttpServletResponse response) {
        /* TODO: send reverse geocaching request */
        Location location = new Location(latSave, lngSave, city, state, country);

        /* TODO: save Location to active user (using session variable) */
        HttpSession httpSession = request.getSession(false);

        User user = null;
        if(httpSession != null) {
            user = (User) httpSession.getAttribute("user");
            locationDao.save(location);
            User currentUser = userDao.findOne(user.getId());
            currentUser.addLocation(location);
            userDao.save(currentUser);
            model.addAttribute("user", currentUser);
        }
        model.addAttribute("location", location);
        return "location/index";
    }

}
