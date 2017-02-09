package com.mitchellbosecke.seniorcommander.web.service;

import com.mitchellbosecke.seniorcommander.domain.ChatLogModel;
import com.mitchellbosecke.seniorcommander.web.repository.ChatLogModelRepository;
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
public class ChatLogServiceImpl implements ChatLogService {

    @Autowired
    private ChatLogModelRepository chatLogRepository;

    @Override
    public Page<ChatLogModel> findLogs(String communityName, Pageable pageable) {
        return chatLogRepository.findAllByComunityName(communityName, pageable);
    }

}
