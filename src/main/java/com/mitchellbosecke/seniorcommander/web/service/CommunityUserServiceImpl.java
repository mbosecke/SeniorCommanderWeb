package com.mitchellbosecke.seniorcommander.web.service;

import com.mitchellbosecke.seniorcommander.web.domain.CommunityUserModel;
import com.mitchellbosecke.seniorcommander.web.repository.CommunityUserModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

/**
 * Created by mitch_000 on 2016-09-11.
 */
@Service
@Transactional
public class CommunityUserServiceImpl implements CommunityUserService {

    @Autowired
    private CommunityUserModelRepository communityUserModelRepository;

    @Override
    public Set<CommunityUserModel> findMemberships() {
        String username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        return communityUserModelRepository.findByName(username);
    }

    @Override
    public CommunityUserModel findCommunityUserModel(String communityName) {
        String username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        return communityUserModelRepository.findByNameAndCommunityName(username, communityName);
    }

    @Override
    public List<CommunityUserModel> findTopUsers(String communityName) {
        return communityUserModelRepository.findByCommunityNameOrderByPoints(communityName, new PageRequest(0, 5));
    }

    @Override
    public Page<CommunityUserModel> findUsers(String communityName, Pageable pageable) {
        return communityUserModelRepository.findAllByComunityName(communityName, pageable);
    }
}
