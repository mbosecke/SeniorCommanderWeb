package com.mitchellbosecke.seniorcommander.web.controller;

import com.mitchellbosecke.seniorcommander.web.domain.CommunityUserModel;
import com.mitchellbosecke.seniorcommander.web.service.CommunityUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Set;

/**
 * Created by mitch_000 on 2016-09-11.
 */
@Controller
public class RootController {

    @Autowired
    private CommunityUserService communityUserService;

    @RequestMapping("/")
    public String community() {
        return "redirect:/home";
    }

    @RequestMapping("/home")
    public String home() {
        if (!(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken)) {
            // TODO: don't redirect to a random community, list out the available communities (if more than one)
            Set<CommunityUserModel> communityUserModels = communityUserService.findMemberships();
            return "redirect:/" + communityUserModels.iterator().next().getCommunityModel().getName();
        }else{
            return "home";
        }
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
