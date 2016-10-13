package com.mitchellbosecke.seniorcommander.web.service;

import com.mitchellbosecke.seniorcommander.web.domain.CommunityModel;
import com.mitchellbosecke.seniorcommander.web.domain.CommunitySettingModel;
import com.mitchellbosecke.seniorcommander.web.repository.CommunityRepository;
import com.mitchellbosecke.seniorcommander.web.repository.CommunitySettingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by mitch_000 on 2016-09-11.
 */
@Service
@Transactional
public class CommunityServiceImpl implements CommunityService {

    private final CommunityRepository communityRepository;

    private final CommunitySettingRepository communitySettingRepository;

    @Autowired
    public CommunityServiceImpl(CommunityRepository communityRepository,
                                CommunitySettingRepository communitySettingRepository){
        this.communityRepository = communityRepository;
        this.communitySettingRepository = communitySettingRepository;
    }

    @Override
    public void updatePoints(String communityName, int onlinePoints, String pointName) {
        CommunityModel community = communityRepository.findOneByName(communityName);
        setSetting(community, "points.online", String.valueOf(onlinePoints));
        setSetting(community, "points.plural", pointName);
    }

    private void setSetting(CommunityModel community, String key, String value){
        boolean found = false;
        for (CommunitySettingModel setting : community.getSettings()) {
            if(setting.getKey().equalsIgnoreCase(key)){
                setting.setValue(value);
                found = true;
                break;
            }
        }
        if(!found){
            CommunitySettingModel setting = new CommunitySettingModel();
            setting.setCommunityModel(community);
            setting.setKey(key);
            setting.setValue(value);
            communitySettingRepository.save(setting);
        }
    }
}
