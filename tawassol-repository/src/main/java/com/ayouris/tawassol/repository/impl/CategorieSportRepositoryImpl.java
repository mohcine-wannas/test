package com.ayouris.tawassol.repository.impl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ayouris.tawassol.common.model.entity.CategorieSport;
import com.ayouris.tawassol.common.model.entity.QAdherent;
import com.ayouris.tawassol.common.model.entity.QCategorieSport;
import com.ayouris.tawassol.repository.custom.CategorieSportRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by chamakh on 12/03/2017.
 */
public class CategorieSportRepositoryImpl implements CategorieSportRepositoryCustom {
    private final JPAQueryFactory queryFactory;
    private final QCategorieSport qCategorieSport = QCategorieSport.categorieSport;
    private final QAdherent qAdherent = QAdherent.adherent;

    @Autowired
    public CategorieSportRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }


    @Override
    public List<CategorieSport> findByAdherentId(Long adherentId) throws Exception {
        return queryFactory
                .select(qCategorieSport)
                .from(qAdherent)
                .join(qAdherent.categorieSportList,qCategorieSport)
                .on(qAdherent.id.eq(adherentId))
                .fetch();
    }
}
