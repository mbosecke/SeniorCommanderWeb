package com.mitchellbosecke.seniorcommander.web.controller;

import com.mitchellbosecke.seniorcommander.web.domain.CommunityUserModel;
import com.mitchellbosecke.seniorcommander.web.service.CommandService;
import com.mitchellbosecke.seniorcommander.web.service.CommunityUserService;
import com.mitchellbosecke.seniorcommander.web.service.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Set;

/**
 * Created by mitch_000 on 2016-09-11.
 */
@RestController
public class CommunityController {

    @Autowired
    private CommunityUserService communityUserService;

    @Autowired
    private QuoteService quoteService;

    @Autowired
    private CommandService commandService;

    @RequestMapping("/community")
    public View community() {
        Set<CommunityUserModel> communityUserModels = communityUserService.findMemberships();
        return new RedirectView("/community/" + communityUserModels.iterator().next().getCommunityModel().getName());
    }

    @RequestMapping(value = {"/community/{communityName}", "/community/{communityName}/dashboard"})
    public ModelAndView dashboard(@PathVariable String communityName) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("dashboard");

        addCommonMavObjects(mav, communityName);
        mav.addObject("activeTab", "dashboard");
        mav.addObject("randomQuote", quoteService.findRandomQuote(communityName));
        mav.addObject("topUsers", communityUserService.findTopUsers(communityName));
        return mav;
    }

    @RequestMapping("/community/{communityName}/quotes")
    public ModelAndView quotes(@PathVariable String communityName,
                               @PageableDefault(page = 0, size = 50, direction = Sort.Direction.DESC, sort = {"createdDate"}) Pageable pageable) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("quotes");

        addCommonMavObjects(mav, communityName);
        mav.addObject("activeTab", "quotes");
        mav.addObject("quotes", quoteService.findQuotes(communityName, pageable));
        mav.addObject("page", pageable.getPageNumber());
        return mav;
    }

    @RequestMapping("/community/{communityName}/commands")
    public ModelAndView commands(@PathVariable String communityName,
                                 @PageableDefault(page = 0, size = 50, direction = Sort.Direction.ASC, sort = {"trigger"}) Pageable pageable) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("commands");

        addCommonMavObjects(mav, communityName);
        mav.addObject("activeTab", "commands");
        mav.addObject("commands", commandService.findCommands(communityName, pageable));
        mav.addObject("page", pageable.getPageNumber());
        return mav;
    }

    @RequestMapping("/community/{communityName}/users")
    public ModelAndView users(@PathVariable String communityName,
                              @PageableDefault(page = 0, size = 50, direction = Sort.Direction.ASC, sort = {"name"}) Pageable pageable) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("users");

        addCommonMavObjects(mav, communityName);
        mav.addObject("activeTab", "users");
        mav.addObject("users", communityUserService.findUsers(communityName, pageable));
        mav.addObject("page", pageable.getPageNumber());
        return mav;
    }

    @RequestMapping("/community/{communityName}/timers")
    public ModelAndView timers(@PathVariable String communityName) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("timers");

        addCommonMavObjects(mav, communityName);
        mav.addObject("activeTab", "timers");
        return mav;
    }

    @RequestMapping("/community/{communityName}/log")
    public ModelAndView log(@PathVariable String communityName) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("log");

        addCommonMavObjects(mav, communityName);
        mav.addObject("activeTab", "log");
        return mav;
    }

    @RequestMapping("/community/{communityName}/admin")
    public ModelAndView admin(@PathVariable String communityName) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("admin");

        addCommonMavObjects(mav, communityName);
        mav.addObject("activeTab", "admin");
        return mav;
    }

    private void addCommonMavObjects(ModelAndView mav, String communityName) {
        mav.addObject("username", SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        mav.addObject("communityUserModels", communityUserService.findMemberships());
        mav.addObject("communityUserModel", communityUserService.findCommunityUserModel(communityName));
    }
}
