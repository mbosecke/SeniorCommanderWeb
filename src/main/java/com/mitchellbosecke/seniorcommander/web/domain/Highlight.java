package com.mitchellbosecke.seniorcommander.web.domain;

/**
 * Created by mitch_000 on 2016-10-10.
 */
public class Highlight {

    private final CommunityUserModel user;
    private final String description;

    public Highlight(CommunityUserModel user, String description) {
        this.user = user;
        this.description = description;
    }

    public CommunityUserModel getUser() {
        return user;
    }

    public String getDescription() {
        return description;
    }
}
