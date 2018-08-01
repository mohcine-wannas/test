package com.ayouris.tawassol.service.impl;

import com.ayouris.tawassol.common.mapper.CustomModelMapper;
import com.ayouris.tawassol.common.model.bean.CategorieAdminBean;
import com.ayouris.tawassol.common.model.bean.CategorieProfBean;
import com.ayouris.tawassol.common.model.bean.MessageModelAdminBean;
import com.ayouris.tawassol.common.model.entity.CategorieAdmin;
import com.ayouris.tawassol.common.model.entity.CategorieProf;
import com.ayouris.tawassol.common.model.entity.QCategorieAdmin;
import com.ayouris.tawassol.common.model.entity.QCategorieProf;
import com.ayouris.tawassol.repository.CategorieAdminRepository;
import com.ayouris.tawassol.repository.CategorieProfRepository;
import com.ayouris.tawassol.service.CategorieService;
import com.querydsl.core.types.OrderSpecifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CategorieServiceImpl implements CategorieService {

    @Autowired
    private CategorieAdminRepository categorieAdminRepository;
    @Autowired
    private CategorieProfRepository categorieProfRepository;

    @Autowired
    private CustomModelMapper mapper;

    @Override
    public List<CategorieAdminBean> getAllCategoriesAdmin() {
        QCategorieAdmin qCategorieAdmin = QCategorieAdmin.categorieAdmin;
        OrderSpecifier<String> sortOrder = qCategorieAdmin.libelle.asc();

        List<CategorieAdmin> categories = (List<CategorieAdmin>) categorieAdminRepository.findAll(qCategorieAdmin.active.isTrue(), sortOrder);
        return mapper.map(categories, CategorieAdminBean.LIST_BEAN_TYPE);
    }

    @Override
    public List<CategorieProfBean> getAllCategoriesProf() {
        QCategorieProf qCategorieProf = QCategorieProf.categorieProf;
        OrderSpecifier<String> sortOrder = qCategorieProf.libelle.asc();

        List<CategorieProf> categories = (List<CategorieProf>) categorieProfRepository.findAll(qCategorieProf.active.isTrue(), sortOrder);
        return mapper.map(categories, CategorieProfBean.LIST_BEAN_TYPE);
    }
}