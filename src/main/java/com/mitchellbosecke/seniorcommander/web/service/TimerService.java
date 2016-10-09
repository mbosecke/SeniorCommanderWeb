package com.mitchellbosecke.seniorcommander.web.service;

import com.mitchellbosecke.seniorcommander.web.domain.TimerModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by mitch_000 on 2016-09-11.
 */
public interface TimerService {

    Page<TimerModel> findTimers(String communityName, Pageable pageable);

}
