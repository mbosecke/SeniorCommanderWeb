package com.mitchellbosecke.seniorcommander.web.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by mitch_000 on 2016-09-11.
 */
@RestController
public class LoginController {

    @RequestMapping("/")
    public ModelAndView login() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("login");
        mav.addObject("username", SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal().toString());
        return mav;
    }
}
