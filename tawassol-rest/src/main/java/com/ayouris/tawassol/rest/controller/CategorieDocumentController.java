package com.ayouris.tawassol.rest.controller;

import com.ayouris.tawassol.common.model.entity.CategorieDocument;
import com.ayouris.tawassol.service.CategorieDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by chamakh on 02/03/2017.
 */

public class CategorieDocumentController extends BaseController {
    private final CategorieDocumentService categorieDocumentService;

    @Autowired
    public CategorieDocumentController(CategorieDocumentService categorieDocumentService) {
        this.categorieDocumentService = categorieDocumentService;
    }

    @RequestMapping(value = "/categorieDocument/list", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<List<CategorieDocument>> getCategorieDocumentList() throws Exception {
        List<CategorieDocument> categorieDocumentList = categorieDocumentService.findAll();

        return new ResponseEntity<List<CategorieDocument>>(categorieDocumentList, HttpStatus.OK);
    }
}
