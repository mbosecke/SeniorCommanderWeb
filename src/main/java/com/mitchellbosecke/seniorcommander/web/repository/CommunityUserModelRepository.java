package com.mitchellbosecke.seniorcommander.web.repository;

import com.mitchellbosecke.seniorcommander.web.domain.CommunityUserModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Set;

/**
 * Created by mitch_000 on 2016-09-11.
 */
@Repository
public interface CommunityUserModelRepository extends JpaRepository<CommunityUserModel, Long> {

    Set<CommunityUserModel> findByName(String name);

    //@formatter:off
    @Query("SELECT cu " +
            "FROM CommunityUserModel cu " +
            "WHERE cu.name = :userName AND cu.communityModel.name = :communityName")
    //@formatter:on
    CommunityUserModel findByNameAndCommunityName(@Param("userName") String userName, @Param("communityName") String communityName);

    //@formatter:off
    @Query("SELECT cu " +
            "FROM CommunityUserModel cu " +
            "WHERE cu.communityModel.name = :communityName " +
            "ORDER BY cu.points DESC")
    //@formatter:on
    List<CommunityUserModel> findByCommunityNameOrderByPoints(@Param("communityName") String communityName, Pageable pageable);

    //@formatter:off
    @Query("SELECT cu FROM CommunityUserModel cu " +
            "WHERE cu.communityModel.name = :communityName")
    //@formatter:on
    Page<CommunityUserModel> findAllByComunityName(@Param("communityName") String communityName, Pageable pageable);
}
