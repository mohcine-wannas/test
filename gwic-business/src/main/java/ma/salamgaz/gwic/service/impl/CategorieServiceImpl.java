package ma.salamgaz.gwic.service.impl;

import ma.salamgaz.gwic.common.model.entity.Categorie;
import ma.salamgaz.gwic.repository.CategorieRepository;
import ma.salamgaz.gwic.service.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by chamakh on 06/01/2017.
 */
public class CategorieServiceImpl extends GenericServiceImpl<Categorie, Long> implements CategorieService {

    private final CategorieRepository categorieRepository;

    @Autowired
    public CategorieServiceImpl(CategorieRepository categorieRepository) {
        this.categorieRepository = categorieRepository;
    }

}
