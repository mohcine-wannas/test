package ma.salamgaz.gwic.repository.custom;

import ma.salamgaz.gwic.common.model.dto.DocumentCriteriaDto;
import ma.salamgaz.gwic.common.model.entity.Document;

import java.util.List;

/**
 * Created by chamakh on 06/01/2017.
 */
public interface DocumentRepositoryCustom {
    List<Document> findDocumentListByCriteria(DocumentCriteriaDto documentCriteriaDto);
}
