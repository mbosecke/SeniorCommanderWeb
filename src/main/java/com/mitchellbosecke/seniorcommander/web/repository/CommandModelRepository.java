package com.mitchellbosecke.seniorcommander.web.repository;

import com.mitchellbosecke.seniorcommander.domain.AccessLevel;
import com.mitchellbosecke.seniorcommander.domain.CommandModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by mitch_000 on 2016-09-11.
 */
@Repository
public interface CommandModelRepository extends JpaRepository<CommandModel, Long> {

    //@formatter:off
    @Query("SELECT cm FROM CommandModel cm " +
            "WHERE cm.communityModel.name = :communityName " +
            "AND cm.accessLevel in :accessLevels")
    //@formatter:on
    Page<CommandModel> findAllByComunityName(@Param("communityName") String communityName,
                                             @Param("accessLevels") List<AccessLevel> accessLevels, Pageable pageable);
}
