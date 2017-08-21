package com.ayouris.tawassol.repository.impl;

import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.ayouris.tawassol.common.model.dto.DocumentCriteriaDto;
import com.ayouris.tawassol.common.model.entity.Document;
import com.ayouris.tawassol.common.model.entity.QDocument;
import com.ayouris.tawassol.repository.custom.DocumentRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by chamakh on 12/03/2017.
 */
public class DocumentRepositoryImpl implements DocumentRepositoryCustom {
    private final JPAQueryFactory queryFactory;
    private final QDocument document = QDocument.document;

    @Autowired
    public DocumentRepositoryImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public List<Document> findDocumentListByCriteria(DocumentCriteriaDto documentCriteriaDto) {
        JPAQuery<Document> query =  queryFactory.selectFrom(document);

        query.where(document.adherent.eq(documentCriteriaDto.getAdherent()));

        if(null != documentCriteriaDto.getCategorieDocument()){
            query.where(document.categorieDocument.eq(documentCriteriaDto.getCategorieDocument()));
        }

        if(null != documentCriteriaDto.getDescription() && !documentCriteriaDto.getDescription().isEmpty()){
            query.where(document.description.toLowerCase().contains(documentCriteriaDto.getDescription().toLowerCase()));
        }

        if(null != documentCriteriaDto.getDateDebut()){
            query.where(document.date.goe(documentCriteriaDto.getDateDebut()));
        }

        if(null != documentCriteriaDto.getDateFin()){
            query.where(document.date.loe(documentCriteriaDto.getDateFin()));
        }

        return query.fetch();
    }
}
