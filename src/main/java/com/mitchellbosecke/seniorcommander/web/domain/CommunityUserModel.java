package com.mitchellbosecke.seniorcommander.web.domain;

import com.mitchellbosecke.seniorcommander.web.security.AccessLevel;

import javax.persistence.*;
import java.time.ZonedDateTime;

/**
 * Created by mitch_000 on 2016-07-10.
 */
@Entity
@Table(name = "community_user", schema = "bot")
public class CommunityUserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;

    @ManyToOne
    @JoinColumn(name = "community_id")
    private CommunityModel communityModel;

    @Column
    private String name;

    @Column
    private int points;

    @Column(name = "access_level")
    @Enumerated(EnumType.STRING)
    private AccessLevel accessLevel;

    @Column(name = "first_seen")
    private ZonedDateTime firstSeen;

    @Column(name = "last_chatted")
    private ZonedDateTime lastChatted;

    @Column(name = "first_followed")
    private ZonedDateTime firstFollowed;

    @Column
    private boolean bot;

    public AccessLevel getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(AccessLevel accessLevel) {
        this.accessLevel = accessLevel;
    }

    public CommunityModel getCommunityModel() {
        return communityModel;
    }

    public void setCommunityModel(CommunityModel communityModel) {
        this.communityModel = communityModel;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public ZonedDateTime getFirstSeen() {
        return firstSeen;
    }

    public void setFirstSeen(ZonedDateTime firstSeen) {
        this.firstSeen = firstSeen;
    }

    public ZonedDateTime getLastChatted() {
        return lastChatted;
    }

    public void setLastChatted(ZonedDateTime lastChatted) {
        this.lastChatted = lastChatted;
    }

    public ZonedDateTime getFirstFollowed() {
        return firstFollowed;
    }

    public void setFirstFollowed(ZonedDateTime firstFollowed) {
        this.firstFollowed = firstFollowed;
    }

    public boolean isBot() {
        return bot;
    }

    public void setBot(boolean bot) {
        this.bot = bot;
    }
}
