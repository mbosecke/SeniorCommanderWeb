package com.mitchellbosecke.seniorcommander.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by mitch_000 on 2016-09-11.
 */
@RestController
public class RootController {

    @RequestMapping("/")
    public ModelAndView login() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("login");
        return mav;
    }

    @RequestMapping("/privacy")
    public ModelAndView privacy() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("legal/privacy");
        return mav;
    }

    @RequestMapping("/terms-of-service")
    public ModelAndView termsOfService() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("legal/terms-of-service");
        return mav;
    }
}
