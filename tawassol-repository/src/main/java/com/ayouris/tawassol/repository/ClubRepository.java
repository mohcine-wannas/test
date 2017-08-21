package com.ayouris.tawassol.repository;

import com.ayouris.tawassol.common.model.entity.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by chamakh on 06/01/2017.
 */
@Repository
public interface ClubRepository extends JpaRepository<Club, Long> {

}
