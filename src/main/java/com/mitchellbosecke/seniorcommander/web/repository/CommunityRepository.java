package com.mitchellbosecke.seniorcommander.web.repository;

import com.mitchellbosecke.seniorcommander.domain.CommunityModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by mitch_000 on 2016-09-11.
 */
@Repository
public interface CommunityRepository extends JpaRepository<CommunityModel, Long> {

    CommunityModel findOneByName(String name);

}
