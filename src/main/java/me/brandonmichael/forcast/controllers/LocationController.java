package me.brandonmichael.forcast.controllers;

import me.brandonmichael.forcast.models.Location;
import me.brandonmichael.forcast.models.User;
import me.brandonmichael.forcast.models.data.LocationDao;
import me.brandonmichael.forcast.models.data.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "location")
public class LocationController {

    @Autowired
    private LocationDao locationDao;

    @Autowired
    private UserDao userDao;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(Model model, HttpServletRequest request) {
        HttpSession httpSession = request.getSession(false);

        /*TODO: add lat/longitude to cookies so map can start at user location
        * Send as location object with just the two attributes*/

        /*TODO: save timezone in cookies as well from javascript*/
        User user = null;
        if(httpSession != null){
            user = (User) httpSession.getAttribute("user");

        }

        if(user!=null){
            User currentUser = userDao.findOne(user.getId());
            model.addAttribute("user", currentUser);

            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                for (int i = 0; i < cookies.length; i++) {
                    if (cookies[i].getName().equals("latitude")) {
                        double latitude = Double.parseDouble(cookies[i].getValue());
                        model.addAttribute("latitude", latitude);
                    } else if (cookies[i].getName().equals("longitude")) {
                        double longitude = Double.parseDouble(cookies[i].getValue());
                        model.addAttribute("longitude", longitude);
                    }
                }
            }

            return "location/index";
        }

        return "redirect:/";
    }

    /*TODO: path variable map for location ID to be removed*/

    @RequestMapping(value = "/{Id}")
    public String removeLocation(@PathVariable int Id, Model model, HttpServletRequest request) {
        HttpSession httpSession = request.getSession(false);

        /*TODO: add lat/longitude to cookies so map can start at user location
         * Send as location object with just the two attributes*/

        /*TODO: save timezone in cookies as well from javascript*/
        User user = null;
        if(httpSession != null){
            user = (User) httpSession.getAttribute("user");

        }

        if(user!=null){
            locationDao.delete(Id);
            User currentUser = userDao.findOne(user.getId());
            model.addAttribute("user", currentUser);

            return "location/index";
        }

        return "redirect:/";
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String location(Model model, @RequestParam String latSave, @RequestParam String lngSave, @RequestParam String city, @RequestParam String state, @RequestParam String country, HttpServletRequest request) {

        Location location = new Location(latSave, lngSave, city, state, country);

        HttpSession httpSession = request.getSession(false);

        User user = null;
        if(httpSession != null) {
            user = (User) httpSession.getAttribute("user");

        }

        if(user!=null) {
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
