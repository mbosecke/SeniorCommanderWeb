package com.mitchellbosecke.seniorcommander.web.service;

import com.mitchellbosecke.seniorcommander.web.domain.CommandModel;
import com.mitchellbosecke.seniorcommander.web.domain.CommunityUserModel;
import com.mitchellbosecke.seniorcommander.web.repository.CommandModelRepository;
import com.mitchellbosecke.seniorcommander.web.repository.CommunityUserModelRepository;
import com.mitchellbosecke.seniorcommander.web.security.AccessLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mitch_000 on 2016-09-11.
 */
@Service
@Transactional
public class CommandServiceImpl implements CommandService {

    @Autowired
    private CommandModelRepository commandModelRepository;

    @Autowired
    private CommunityUserModelRepository userRepository;

    @Override
    public Page<CommandModel> findCommands(String communityName, Pageable pageable) {

        String username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        CommunityUserModel user = userRepository.findByNameAndCommunityName(username, communityName);

        List<AccessLevel> userAccessLevels = new ArrayList<>();
        for (AccessLevel level : AccessLevel.values()) {
            if (user.getAccessLevel().hasAccess(level)) {
                userAccessLevels.add(level);
            }
        }

        return commandModelRepository.findAllByComunityName(communityName, userAccessLevels, pageable);
    }
}
