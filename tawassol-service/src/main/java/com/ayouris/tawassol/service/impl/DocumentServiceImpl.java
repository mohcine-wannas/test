package com.ayouris.tawassol.service.impl;

import com.ayouris.tawassol.common.model.dto.DocumentCriteriaDto;
import com.ayouris.tawassol.common.model.entity.Document;
import com.ayouris.tawassol.repository.DocumentRepository;
import com.ayouris.tawassol.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by chamakh on 06/01/2017.
 */
@Service
public class DocumentServiceImpl extends GenericServiceImpl<Document, Long> implements DocumentService {

    private final DocumentRepository documentRepository;

    @Autowired
    public DocumentServiceImpl(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    @Override
    public Document saveDocument(Document document) {
        return documentRepository.save(document);
    }

    @Override
    public List<Document> findDocumentListByCriteria(DocumentCriteriaDto documentCriteriaDto) {
        return documentRepository.findDocumentListByCriteria(documentCriteriaDto);
    }
}
