package com.mitchellbosecke.seniorcommander.web.controller;

import com.mitchellbosecke.seniorcommander.web.service.ChannelService;
import com.mitchellbosecke.seniorcommander.web.service.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * Created by mitch_000 on 2016-09-11.
 */
@Controller
@RequestMapping("admin")
public class AdminController {

    private final CommunityService communityService;

    private final ChannelService channelService;

    @Autowired
    public AdminController(CommunityService communityService, ChannelService channelService) {
        this.communityService = communityService;
        this.channelService = channelService;
    }

    @RequestMapping("/{communityName}/points")
    public String savePointSettings(@PathVariable String communityName, @Valid PointsForm pointsForm,
                                    BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (!bindingResult.hasErrors()) {
            communityService.updatePoints(communityName, pointsForm.getPointsOnline(), pointsForm.getPointsPlural());
            redirectAttributes.addFlashAttribute("pointsFormSuccessMessage", "Point settings have been updated.");
        } else {
            redirectAttributes.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "pointsForm", bindingResult);
        }
        return "redirect:/" + communityName + "/admin#points";
    }

    @RequestMapping("/{communityName}/manage-channel")
    public String restartChannel(@PathVariable String communityName, long channelId, String action,
                                 RedirectAttributes redirectAttributes) {
        String message = null;
        Boolean result = null;
        switch (action) {
            case "stop":
                result = channelService.stop(communityName, channelId);
                message = "stopped";
                break;
            case "start":
                result = channelService.start(communityName, channelId);
                message = "started";
                break;
            case "restart":
                result = channelService.restart(communityName, channelId);
                message = "restarted";
                break;
        }
        if (result) {
            redirectAttributes.addFlashAttribute("twitchFormSuccessMessage", String
                    .format("Channel has been successfully %s.", message));
        }
        return "redirect:/" + communityName + "/admin";
    }
}
