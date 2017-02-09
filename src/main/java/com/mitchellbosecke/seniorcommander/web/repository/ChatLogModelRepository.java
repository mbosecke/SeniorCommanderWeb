package com.mitchellbosecke.seniorcommander.web.repository;

import com.mitchellbosecke.seniorcommander.domain.ChatLogModel;
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
public interface ChatLogModelRepository extends JpaRepository<ChatLogModel, Long> {

    @Query("SELECT cl FROM ChatLogModel cl WHERE cl.channel.communityModel.name = :communityName")
    Page<ChatLogModel> findAllByComunityName(@Param("communityName") String communityName, Pageable pageable);
}
