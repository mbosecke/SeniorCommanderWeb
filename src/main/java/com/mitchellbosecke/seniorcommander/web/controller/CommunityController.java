package com.mitchellbosecke.seniorcommander.web.controller;

import com.mitchellbosecke.seniorcommander.web.domain.CommunityUserModel;
import com.mitchellbosecke.seniorcommander.web.security.AccessLevel;
import com.mitchellbosecke.seniorcommander.web.security.CommunityPermissions;
import com.mitchellbosecke.seniorcommander.web.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by mitch_000 on 2016-09-11.
 */
@Controller
public class CommunityController {

    @Autowired
    private CommunityUserService communityUserService;

    @Autowired
    private QuoteService quoteService;

    @Autowired
    private CommandService commandService;

    @Autowired
    private TimerService timerService;

    @Autowired
    private HighlightService highlightService;

    @Autowired
    private CommunityPermissions communityPermissions;

    @Autowired
    private ChatLogService chatLogService;

    @RequestMapping("/{communityName}")
    public String community(@PathVariable String communityName){
        return "redirect:/" + communityName + "/dashboard";
    }

    @RequestMapping(value = {"/{communityName}/dashboard"})
    public ModelAndView dashboard(HttpServletRequest request, @PathVariable String communityName) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("community/dashboard");

        addCommonMavObjects(request, mav, communityName, "dashboard");

        mav.addObject("randomQuote", quoteService.findRandomQuote(communityName));
        mav.addObject("topUsers", communityUserService.findTopUsers(communityName));
        mav.addObject("highlights", highlightService.findHighlights(communityName));
        return mav;
    }

    @RequestMapping("/{communityName}/quotes")
    public ModelAndView quotes(HttpServletRequest request, @PathVariable String communityName,
                               @PageableDefault(page = 0, size = 15, direction = Sort.Direction.DESC, sort = {"createdDate"}) Pageable pageable) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("community/quotes");
        addCommonMavObjects(request, mav, communityName, "quotes");

        mav.addObject("page", quoteService.findQuotes(communityName, pageable));
        return mav;
    }

    @RequestMapping("/{communityName}/commands")
    public ModelAndView commands(HttpServletRequest request, @PathVariable String communityName,
                                 @PageableDefault(page = 0, size = 15, direction = Sort.Direction.ASC, sort = {"trigger"}) Pageable pageable) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("community/commands");
        addCommonMavObjects(request, mav, communityName, "commands");

        mav.addObject("page", commandService.findCommands(communityName, pageable));
        return mav;
    }

    @RequestMapping("/{communityName}/leaderboard")
    public ModelAndView leaderboard(HttpServletRequest request, @PathVariable String communityName,
                                    @PageableDefault(page = 0, size = 15, direction = Sort.Direction.DESC, sort = {"points"}) Pageable pageable) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("community/leaderboard");
        addCommonMavObjects(request, mav, communityName, "leaderboard");

        mav.addObject("page", communityUserService.findUsers(communityName, pageable));
        return mav;
    }

    @RequestMapping("/{communityName}/timers")
    public ModelAndView timers(HttpServletRequest request, @PathVariable String communityName,
                               @PageableDefault(page = 0, size = 15, direction = Sort.Direction.ASC, sort = {"communitySequence"}) Pageable pageable) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("community/timers");

        addCommonMavObjects(request, mav, communityName, "timers");
        mav.addObject("page", timerService.findTimers(communityName, pageable));
        return mav;
    }

    @RequestMapping("/{communityName}/log")
    public ModelAndView log(HttpServletRequest request, @PathVariable String communityName,
                            @PageableDefault(page = 0, size = 50, direction = Sort.Direction.DESC, sort = {"date"}) Pageable pageable) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("community/log");

        addCommonMavObjects(request, mav, communityName, "log");
        mav.addObject("page", chatLogService.findLogs(communityName, pageable));
        return mav;
    }

    @RequestMapping("/{communityName}/admin")
    public ModelAndView admin(HttpServletRequest request, @PathVariable String communityName, ModelMap model) {
        if (!communityPermissions.hasAccess(communityName, AccessLevel.OWNER)) {
            throw new AccessDeniedException("Must be a channel owner");
        }
        CommunityUserModel communityUserModel = communityUserService.findCommunityUserModel(communityName);
        ModelAndView mav = new ModelAndView("community/admin", model);
        mav.addObject("pointsOnline", communityUserModel.getCommunityModel().getSetting("points.online"));
        mav.addObject("twitchChannel", communityUserModel.getCommunityModel().getChannel("irc"));
        mav.addObject("discordChannel", communityUserModel.getCommunityModel().getChannel("discord"));
        addCommonMavObjects(request, mav, communityName, "admin");
        return mav;
    }

    private void addCommonMavObjects(HttpServletRequest request, ModelAndView mav, String communityName, String activeTab) {
        CommunityUserModel communityUserModel = communityUserService.findCommunityUserModel(communityName);
        mav.addObject("username", SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        mav.addObject("communityUserModels", communityUserService.findMemberships());
        mav.addObject("communityUserModel", communityUserModel);
        mav.addObject("hasOwnerAccess", communityPermissions.hasAccess(communityName, AccessLevel.OWNER));
        String pointsPlural = communityUserModel.getCommunityModel().getSetting("points.plural");
        mav.addObject("pointsPlural", pointsPlural == null? "Points" : pointsPlural);
        mav.addObject("activeTab", activeTab);
        mav.addObject("collapsedSidebar", WebUtils.getCookie(request, "collapsed-sidebar") != null);
    }
}
