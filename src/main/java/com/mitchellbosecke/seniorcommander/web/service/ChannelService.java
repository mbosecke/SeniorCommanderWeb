package com.mitchellbosecke.seniorcommander.web.service;

import org.springframework.security.access.prepost.PreAuthorize;

/**
 * Created by mitch_000 on 2017-02-05.
 */
public interface ChannelService {


    @PreAuthorize("@communityPermissions.hasAccess(#communityName, 'OWNER')")
    boolean stop(String communityName, long channelId);


    @PreAuthorize("@communityPermissions.hasAccess(#communityName, 'OWNER')")
    boolean start(String communityName, long channelId);

    @PreAuthorize("@communityPermissions.hasAccess(#communityName, 'OWNER')")
    boolean restart(String communityName, long channelId);


    @PreAuthorize("@communityPermissions.hasAccess(#communityName, 'OWNER')")
    boolean status(String communityName, long channelId);
}
