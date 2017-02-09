package com.mitchellbosecke.seniorcommander.web.repository;

import com.mitchellbosecke.seniorcommander.domain.TimerModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created by mitch_000 on 2016-09-11.
 */
@Repository
public interface TimerModelRepository extends JpaRepository<TimerModel, Long> {

    @Query("SELECT tm FROM TimerModel tm WHERE tm.channelModel.communityModel.name = :communityName")
    Page<TimerModel> findAllByComunityName(@Param("communityName") String communityName, Pageable pageable);
}
