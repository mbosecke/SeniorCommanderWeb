package com.mitchellbosecke.seniorcommander.web.service;

import com.mitchellbosecke.seniorcommander.web.domain.CommunityUserModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Set;

/**
 * Created by mitch_000 on 2016-09-11.
 */
public interface CommunityUserService {

    Set<CommunityUserModel> findMemberships();

    CommunityUserModel findCommunityUserModel(String communityName);

    List<CommunityUserModel> findTopUsers(String communityName);

    Page<CommunityUserModel> findUsers(String communityName, Pageable pageable);
}
