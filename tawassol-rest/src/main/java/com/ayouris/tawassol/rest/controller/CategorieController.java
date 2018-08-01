package com.ayouris.tawassol.rest.controller;

import com.ayouris.tawassol.common.model.bean.CategorieAdminBean;
import com.ayouris.tawassol.common.model.bean.CategorieProfBean;
import com.ayouris.tawassol.service.CategorieService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(value = "/categories")
@Api(value = "categories-api")
public class CategorieController extends BaseController {

    @Autowired
    private CategorieService categorieService;

    @RequestMapping(value = "admin", method = RequestMethod.GET)
    public ResponseEntity<List<CategorieAdminBean>> getAllCategoriesAdmin() throws Exception {
        return new ResponseEntity<>(categorieService.getAllCategoriesAdmin(), HttpStatus.OK);
    }

    @RequestMapping(value = "prof", method = RequestMethod.GET)
    public ResponseEntity<List<CategorieProfBean>> getAllCategoriesProf() throws Exception {
        return new ResponseEntity<>(categorieService.getAllCategoriesProf(), HttpStatus.OK);
    }
}
