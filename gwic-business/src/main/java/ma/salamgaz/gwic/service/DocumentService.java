package ma.salamgaz.gwic.service;

import ma.salamgaz.gwic.common.model.dto.DocumentCriteriaDto;
import ma.salamgaz.gwic.common.model.entity.Document;

import java.util.List;

/**
 * Created by chamakh on 06/01/2017.
 */
public interface DocumentService extends GenericService<Document, Long> {
    /**
     * Enregistrement de document d'adherent
     * @param document
     * @return
     */
    Document saveDocument(Document document);

    /**
     * Recherche des documents d'adherent par criteres
     * @param documentCriteriaDto
     * @return
     * @throws Exception
     */
    List<Document> findDocumentListByCriteria(DocumentCriteriaDto documentCriteriaDto);
}


