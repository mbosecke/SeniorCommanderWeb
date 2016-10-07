package com.mitchellbosecke.seniorcommander.web.domain;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by mitch_000 on 2016-07-09.
 */
@Entity
@Table(name = "community", schema="core")
public class CommunityModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;

    @Column
    private String name;

    @OneToMany(mappedBy="communityModel")
    private Set<ChannelModel> channelModels;

    @OneToOne(mappedBy = "communityModel")
    private BettingGameModel bettingGameModel;

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

    public Set<ChannelModel> getChannelModels() {
        return channelModels;
    }

    public void setChannelModels(Set<ChannelModel> channelModels) {
        this.channelModels = channelModels;
    }

    public BettingGameModel getBettingGameModel() {
        return bettingGameModel;
    }

    public void setBettingGameModel(BettingGameModel bettingGameModel) {
        this.bettingGameModel = bettingGameModel;
    }
}
