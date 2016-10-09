package com.mitchellbosecke.seniorcommander.web.security;

import com.mitchellbosecke.seniorcommander.web.domain.CommunityUserModel;
import com.mitchellbosecke.seniorcommander.web.repository.CommunityUserModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * Created by mitch_000 on 2016-10-09.
 */
@Component("communityPermissions")
public class CommunityPermissions {

    @Autowired
    private CommunityUserModelRepository userRepository;

    public boolean hasAccess(String communityName, String minimumAccessLevel) {
        String username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        return hasAccess(communityName, username, minimumAccessLevel);
    }

    public boolean hasAccess(String communityName, AccessLevel minimumAccessLevel) {
        String username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        return hasAccess(communityName, username, minimumAccessLevel);
    }


    public boolean hasAccess(String communityName, String username, String minimumAccessLevel) {
        return hasAccess(communityName, username, AccessLevel.valueOf(minimumAccessLevel));
    }

    public boolean hasAccess(String communityName, String username, AccessLevel minimumAccessLevel) {
        boolean hasPermission = false;
        if (minimumAccessLevel != null) {
            CommunityUserModel user = userRepository.findByNameAndCommunityName(username, communityName);
            if (user != null && user.getAccessLevel().hasAccess(minimumAccessLevel)) {
                hasPermission = true;
            }
        }
        return hasPermission;
    }
}
