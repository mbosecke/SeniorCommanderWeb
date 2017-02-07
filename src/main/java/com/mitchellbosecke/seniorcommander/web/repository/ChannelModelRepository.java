package com.mitchellbosecke.seniorcommander.web.repository;

import com.mitchellbosecke.seniorcommander.web.domain.ChannelModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by mitch_000 on 2017-02-05.
 */
@Repository
public interface ChannelModelRepository extends JpaRepository<ChannelModel, Long> {

}
