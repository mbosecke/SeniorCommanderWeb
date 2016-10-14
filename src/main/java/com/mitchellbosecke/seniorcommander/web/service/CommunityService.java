package com.mitchellbosecke.seniorcommander.web.service;

import org.springframework.security.access.prepost.PreAuthorize;

/**
 * Created by mitch_000 on 2016-09-11.
 */
public interface CommunityService {

    @PreAuthorize("@communityPermissions.hasAccess(#communityName, 'OWNER')")
    void updatePoints(String communityName, int onlinePoints, String pointName);

}
