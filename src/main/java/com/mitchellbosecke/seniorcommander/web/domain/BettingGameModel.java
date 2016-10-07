package com.mitchellbosecke.seniorcommander.web.domain;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by mitch_000 on 2016-07-12.
 */
@Entity
@Table(name = "betting_game", schema = "core")
public class BettingGameModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;

    @ManyToOne
    @JoinColumn(name = "community_id")
    private CommunityModel communityModel;

    @OneToMany(mappedBy = "bettingGameModel", fetch = FetchType.EAGER)
    @Cascade(CascadeType.ALL)
    private Set<BettingOptionModel> options;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public CommunityModel getCommunityModel() {
        return communityModel;
    }

    public void setCommunityModel(CommunityModel communityModel) {
        this.communityModel = communityModel;
    }

    public Set<BettingOptionModel> getOptions() {
        return options;
    }

    public void setOptions(Set<BettingOptionModel> options) {
        this.options = options;
    }

    @Transient
    public int getTotalNumberOfBets(){
        int total = 0;
        for(BettingOptionModel option : options){
            total += option.getBets().size();
        }
        return total;
    }
}
