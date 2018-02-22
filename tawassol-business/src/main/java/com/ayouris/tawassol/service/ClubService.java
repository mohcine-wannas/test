package com.ayouris.tawassol.service;

import com.ayouris.tawassol.common.model.entity.Club;

/**
 * Created by chamakh on 06/01/2017.
 */
public interface ClubService extends GenericService<Club, Long> {
    Club saveClub(Club club) throws Exception;
}

