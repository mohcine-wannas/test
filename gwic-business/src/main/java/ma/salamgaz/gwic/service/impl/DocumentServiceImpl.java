package ma.salamgaz.gwic.service.impl;

import ma.salamgaz.gwic.common.model.dto.DocumentCriteriaDto;
import ma.salamgaz.gwic.common.model.entity.Document;
import ma.salamgaz.gwic.repository.DocumentRepository;
import ma.salamgaz.gwic.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by chamakh on 06/01/2017.
 */
public class DocumentServiceImpl extends GenericServiceImpl<Document, Long> implements DocumentService {

    private final DocumentRepository documentRepository;

    @Autowired
    public DocumentServiceImpl(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    @Override
    public Document saveDocument(Document document) {
       // return documentRepository.save(document);
    	return null;
    }

    @Override
    public List<Document> findDocumentListByCriteria(DocumentCriteriaDto documentCriteriaDto) {
        return documentRepository.findDocumentListByCriteria(documentCriteriaDto);
    }
}
