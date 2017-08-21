package com.ayouris.tawassol.repository.impl;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ayouris.tawassol.common.model.dto.AdherentCriteriaDto;
import com.ayouris.tawassol.common.model.entity.Adherent;
import com.ayouris.tawassol.common.model.entity.QAdherent;
import com.ayouris.tawassol.common.model.enumeration.ENUM_SEXE;
import com.ayouris.tawassol.repository.custom.AdherentRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by chamakh on 12/03/2017.
 */
public class AdherentRepositoryImpl implements AdherentRepositoryCustom {
    private final JPAQueryFactory queryFactory;
    private final QAdherent adherent = QAdherent.adherent;

    @Autowired
    public AdherentRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public List<Adherent> findAdherentListByCriteria(AdherentCriteriaDto adherentCriteriaDto) {
        JPAQuery<Adherent> query =  queryFactory.selectFrom(adherent);

        if(null != adherentCriteriaDto.getNom() && !adherentCriteriaDto.getNom().isEmpty()){
            query.where(adherent.nom.toLowerCase().contains(adherentCriteriaDto.getNom().toLowerCase()));
        }
        if(null != adherentCriteriaDto.getPrenom() && !adherentCriteriaDto.getPrenom().isEmpty()){
            query.where(adherent.prenom.toLowerCase().contains(adherentCriteriaDto.getPrenom().toLowerCase()));
        }

        if(null != adherentCriteriaDto.getCin() && !adherentCriteriaDto.getCin().isEmpty()){
            query.where(adherent.cin.toLowerCase().contains(adherentCriteriaDto.getCin().toLowerCase()));
        }

        if(null != adherentCriteriaDto.getDateNaissance()){
            query.where(adherent.dateNaissance.eq(adherentCriteriaDto.getDateNaissance()));
        }

        if(null != adherentCriteriaDto.getSexe() && !adherentCriteriaDto.getSexe().isEmpty()){
            ENUM_SEXE enum_sexe = ENUM_SEXE.valueOf(adherentCriteriaDto.getSexe());
            query.where(adherent.sexe.eq(enum_sexe));
        }

        if(null != adherentCriteriaDto.getCategorie()){
            query.where(adherent.categorie.eq(adherentCriteriaDto.getCategorie()));
        }

        return query.fetch();
    }
}
