package ma.salamgaz.gwic.service;

import org.springframework.beans.factory.annotation.Autowired;

import ma.salamgaz.gwic.common.model.entity.CategorieDocument;
import ma.salamgaz.gwic.repository.CategorieDocumentRepository;
import ma.salamgaz.gwic.service.impl.GenericServiceImpl;

/**
 * Created by chamakh on 06/01/2017.
 */
public class CategorieDocumentServiceImpl extends GenericServiceImpl<CategorieDocument, Long> implements CategorieDocumentService {

    private final CategorieDocumentRepository categorieDocumentRepository;

    @Autowired
    public CategorieDocumentServiceImpl(CategorieDocumentRepository categorieDocumentRepository) {
        this.categorieDocumentRepository = categorieDocumentRepository;
    }

}
