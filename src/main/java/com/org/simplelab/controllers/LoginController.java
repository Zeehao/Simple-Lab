package com.org.simplelab.controllers;

import com.org.simplelab.database.UserDB;
import com.org.simplelab.database.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Controllers for login and signup pages
 */

@Controller
@RequestMapping(path="/")
public class LoginController{

    @Autowired
    UserDB userDB;

    @GetMapping("")
    public String loginGet(){
        return "login";
    }

    /**
     * Processes user data submission in /login and adds
     * user data to current session.
     *
     * @param username -username from form submission in login.html
     * @param password -password from form submission in login.html
     * @param session -current user session
     * @return JSON response with format:
     *          { success: "true" } if authentication is successful
     *          { success: "false" } otherwise
     */
    @ResponseBody
    @PostMapping("/login")
    public Map<String, String> login(@RequestParam("userName") String username,
                                     @RequestParam("password") String password,
                                     HttpSession session) {
        RequestResponse resp = new RequestResponse();
        if (userDB.authenticate(username, password) == UserDB.UserAuthenticationStatus.SUCCESSFUL) {
            User user = userDB.findUser(username);
            session.setAttribute("username", username);
            session.setAttribute("user_id", user.getId());
            session.setAttribute("identity", user.getRole());
            resp.setSuccess(true);
            return resp.map();
        }
        else{
            resp.setSuccess(false);
            return resp.map();
        }
    }

    /**
     * Redirect user page base on they role in the current session.
     *
     * @param session -current user session
     * @return ModelAndView redirect path for different role of user
     */
    @RequestMapping(value = "/role", method = RequestMethod.GET)
    public ModelAndView rolePageRedirect(HttpSession session) {
        String username = (String) session.getAttribute("username");
        String identity = (String) session.getAttribute("identity");
        if (username != null && identity != null) {
            if (identity.equals("teacher") ) {
                return new ModelAndView("redirect:/teacher");
            } else if (identity.equals("student"))
                return new ModelAndView("redirect:/student");
        }
        return new ModelAndView("redirect:/");
    }


    @GetMapping("/forgotpassword")
    public String forgotPassword(HttpSession session) {
        return "forgotPassword";
    }
}