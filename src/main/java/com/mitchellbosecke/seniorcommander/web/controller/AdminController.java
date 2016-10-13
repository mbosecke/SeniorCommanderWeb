package com.mitchellbosecke.seniorcommander.web.controller;

import com.mitchellbosecke.seniorcommander.web.service.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

/**
 * Created by mitch_000 on 2016-09-11.
 */
@Controller
@RequestMapping("admin")
public class AdminController {

    private final CommunityService communityService;

    @Autowired
    public AdminController(CommunityService communityService) {
        this.communityService = communityService;
    }

    @RequestMapping("/{communityName}/points")
    public String savePointSettings(@PathVariable String communityName, @Valid PointsForm pointsForm,
                                    BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            communityService.updatePoints(communityName, pointsForm.getPointsOnline(), pointsForm.getPointsPlural());
        }
        return "redirect:/community/" + communityName + "/admin";
    }
}
