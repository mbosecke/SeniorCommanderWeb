package com.mitchellbosecke.seniorcommander.web.service;

import com.mitchellbosecke.seniorcommander.web.domain.TimerModel;
import com.mitchellbosecke.seniorcommander.web.repository.TimerModelRepository;
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
public class TimerServiceImpl implements TimerService {

    @Autowired
    private TimerModelRepository timerRepository;

    @Override
    public Page<TimerModel> findTimers(String communityName, Pageable pageable) {
        return timerRepository.findAllByComunityName(communityName, pageable);
    }

}
