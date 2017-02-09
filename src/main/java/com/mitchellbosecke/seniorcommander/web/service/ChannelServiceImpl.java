package com.mitchellbosecke.seniorcommander.web.service;

import com.mitchellbosecke.seniorcommander.domain.ChannelModel;
import com.mitchellbosecke.seniorcommander.domain.CommunityModel;
import com.mitchellbosecke.seniorcommander.web.repository.ChannelModelRepository;
import com.mitchellbosecke.seniorcommander.web.repository.CommunityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

/**
 * Created by mitch_000 on 2017-02-05.
 */
@Service
@Transactional
public class ChannelServiceImpl implements ChannelService {

    private final CommunityRepository communityRepository;

    private final ChannelModelRepository channelModelRepository;

    @Autowired
    public ChannelServiceImpl(CommunityRepository communityRepository, ChannelModelRepository channelModelRepository) {
        this.communityRepository = communityRepository;
        this.channelModelRepository = channelModelRepository;
    }

    @Override
    public boolean stop(String communityName, long channelId) {
        botApi(communityName, channelId, "STOP:" + channelId);
        return true;
    }

    @Override
    public boolean start(String communityName, long channelId) {
        botApi(communityName, channelId, "START:" + channelId);
        return true;
    }

    @Override
    public boolean restart(String communityName, long channelId) {
        botApi(communityName, channelId, "RESTART:" + channelId);
        return true;
    }

    @Override
    public boolean status(String communityName, long channelId) {
        String response = botApi(communityName, channelId, "STATUS:" + channelId);
        return Boolean.parseBoolean(response.trim());
    }

    private String botApi(String communityName, long channelId, String command) {
        CommunityModel community = communityRepository.findOneByName(communityName);
        ChannelModel channel = channelModelRepository.findOne(channelId);
        if (!channel.getCommunityModel().equals(community)) {
            throw new RuntimeException("Community and channel ID must match for authorization purposes.");
        }
        HttpEntity<String> request = new HttpEntity<>(command);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate
                .postForEntity("http://" + channel.getCommunityModel().getServer() + ":8888", request, String.class);
        return response.getBody();
    }
}
