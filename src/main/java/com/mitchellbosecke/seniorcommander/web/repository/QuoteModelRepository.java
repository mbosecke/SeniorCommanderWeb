package com.mitchellbosecke.seniorcommander.web.repository;

import com.mitchellbosecke.seniorcommander.domain.QuoteModel;
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
public interface QuoteModelRepository extends JpaRepository<QuoteModel, Long> {

    //@formatter:off
    @Query("SELECT qm FROM QuoteModel qm " +
            "WHERE qm.communityModel.name = :communityName " +
            "ORDER BY rand()")
    //@formatter:on
    List<QuoteModel> findRandomQuotes(@Param("communityName") String communityName, Pageable pageable);

    @Query("SELECT qm FROM QuoteModel qm WHERE qm.communityModel.name = :communityName")
    Page<QuoteModel> findAllByComunityName(@Param("communityName") String communityName, Pageable pageable);
}
