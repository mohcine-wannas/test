package com.ayouris.tawassol.service;

import com.ayouris.tawassol.common.model.bean.CategorieAdminBean;
import com.ayouris.tawassol.common.model.bean.CategorieProfBean;

import java.util.List;

public interface CategorieService {

    List<CategorieAdminBean> getAllCategoriesAdmin();

    List<CategorieProfBean> getAllCategoriesProf();
}