package com.mitchellbosecke.seniorcommander.web.service;

import com.mitchellbosecke.seniorcommander.domain.CommunityUserModel;
import com.mitchellbosecke.seniorcommander.web.repository.CommunityUserModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class HighlightServiceImpl implements HighlightService {

    @Autowired
    private CommunityUserModelRepository userRepository;

    @Override
    public List<Highlight> findHighlights(String communityName) {
        List<Highlight> highlights = new ArrayList<>();
        highlights.add(newestFollower(communityName));
        highlights.add(chattiest(communityName));
        return highlights;
    }

    private Highlight newestFollower(String communityName) {

        Page<CommunityUserModel> page = userRepository
                .findFollowersByCommunityName(communityName, new PageRequest(0, 1, Sort.Direction.DESC, "firstFollowed"));

        if(!page.getContent().isEmpty()){
            return new Highlight(page.getContent().get(0), "Newest Follower");
        }
        return null;
    }

    private Highlight chattiest(String communityName) {
        ZonedDateTime lastWeek = ZonedDateTime.now(ZoneId.of("UTC")).minusDays(7);
        List<CommunityUserModel> users = userRepository.findChattiest(communityName, lastWeek, new PageRequest(0, 1));
        if(!users.isEmpty()){
            return new Highlight(users.get(0), "Chattiest");
        }
        return null;
    }
}
