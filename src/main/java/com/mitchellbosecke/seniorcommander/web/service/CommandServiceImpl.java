package com.mitchellbosecke.seniorcommander.web.service;

import com.mitchellbosecke.seniorcommander.web.domain.CommandModel;
import com.mitchellbosecke.seniorcommander.web.repository.CommandModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by mitch_000 on 2016-09-11.
 */
@Service
@Transactional
public class CommandServiceImpl implements CommandService {

    @Autowired
    private CommandModelRepository commandModelRepository;

    @Override
    public Page<CommandModel> findCommands(String communityName, Pageable pageable) {
        return commandModelRepository.findAllByComunityName(communityName, pageable);
    }
}
