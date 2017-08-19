//package com.ayouris.tawassol.service.impl;
//
//import com.ayouris.tawassol.common.model.entity.Club;
//import com.ayouris.tawassol.common.model.entity.Cotisation;
//import com.ayouris.tawassol.repository.ClubRepository;
//import com.ayouris.tawassol.service.ClubService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
///**
// * Created by chamakh on 06/01/2017.
// */
//public class ClubServiceImpl extends GenericServiceImpl<Club, Long> implements ClubService {
//
//    private final ClubRepository clubRepository;
//
//    @Autowired
//    public ClubServiceImpl(ClubRepository clubRepository) {
//        this.clubRepository = clubRepository;
//    }
//
//    @Override
//    public Club saveClub(Club club) throws Exception {
//        for(Cotisation cotisation: club.getCotisationList()){
//            cotisation.setClub(club);
//        }
//       // return clubRepository.save(club);
//		return club;
//    }
//}
