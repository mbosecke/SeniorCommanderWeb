package com.mitchellbosecke.seniorcommander.web.repository;

import com.mitchellbosecke.seniorcommander.web.domain.CommandModel;
import com.mitchellbosecke.seniorcommander.web.domain.QuoteModel;
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
public interface CommandModelRepository extends JpaRepository<CommandModel, Long> {

    //@formatter:off
    @Query("SELECT qm FROM CommandModel qm " +
            "WHERE qm.communityModel.name = :communityName")
    //@formatter:on
    Page<CommandModel> findAllByComunityName(@Param("communityName") String communityName, Pageable pageable);
}
