package com.mitchellbosecke.seniorcommander.web.service;

import com.mitchellbosecke.seniorcommander.domain.TimerModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;

/**
 * Created by mitch_000 on 2016-09-11.
 */
public interface TimerService {

    @PreAuthorize("@communityPermissions.hasAccess(#communityName, principal, 'OWNER')")
    Page<TimerModel> findTimers(String communityName, Pageable pageable);

}
