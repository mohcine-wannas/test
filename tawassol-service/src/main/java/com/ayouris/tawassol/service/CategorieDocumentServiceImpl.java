package com.ayouris.tawassol.service;

import com.ayouris.tawassol.common.model.entity.CategorieDocument;
import com.ayouris.tawassol.repository.CategorieDocumentRepository;
import com.ayouris.tawassol.service.impl.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by chamakh on 06/01/2017.
 */
@Service
public class CategorieDocumentServiceImpl extends GenericServiceImpl<CategorieDocument, Long> implements CategorieDocumentService {

    private final CategorieDocumentRepository categorieDocumentRepository;

    @Autowired
    public CategorieDocumentServiceImpl(CategorieDocumentRepository categorieDocumentRepository) {
        this.categorieDocumentRepository = categorieDocumentRepository;
    }

}
