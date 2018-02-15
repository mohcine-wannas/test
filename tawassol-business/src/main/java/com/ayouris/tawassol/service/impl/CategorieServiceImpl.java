package com.ayouris.tawassol.service.impl;

import com.ayouris.tawassol.common.model.entity.Categorie;
import com.ayouris.tawassol.repository.CategorieRepository;
import com.ayouris.tawassol.service.CategorieService;
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
