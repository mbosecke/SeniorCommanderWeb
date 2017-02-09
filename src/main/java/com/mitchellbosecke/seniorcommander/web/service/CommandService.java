package com.mitchellbosecke.seniorcommander.web.service;

import com.mitchellbosecke.seniorcommander.domain.CommandModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by mitch_000 on 2016-09-11.
 */
public interface CommandService {

      Page<CommandModel> findCommands(String communityName, Pageable pageable);
}
