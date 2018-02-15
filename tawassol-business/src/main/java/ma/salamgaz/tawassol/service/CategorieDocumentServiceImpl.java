package ma.salamgaz.tawassol.service;

import org.springframework.beans.factory.annotation.Autowired;

import ma.salamgaz.tawassol.common.model.entity.CategorieDocument;
import ma.salamgaz.tawassol.repository.CategorieDocumentRepository;
import ma.salamgaz.tawassol.service.impl.GenericServiceImpl;

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
