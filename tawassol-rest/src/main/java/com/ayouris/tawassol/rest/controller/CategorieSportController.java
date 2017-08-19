//package com.ayouris.tawassol.rest.controller;
//
//import com.ayouris.tawassol.common.model.entity.CategorieSport;
//import com.ayouris.tawassol.service.CategorieSportService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
///**
// * Created by chamakh on 02/03/2017.
// */
//
//public class CategorieSportController extends BaseController {
//    private final CategorieSportService categorieSportService;
//
//    @Autowired
//    public CategorieSportController(CategorieSportService categorieSportService) {
//        this.categorieSportService = categorieSportService;
//    }
//
//    @RequestMapping(value = "/categorieSport/getCategorieSportList", method = RequestMethod.GET)
//    @PreAuthorize("hasRole('ROLE_USER')")
//    public ResponseEntity<List<CategorieSport>> getCategorieSportList() throws Exception {
//        List<CategorieSport> categorieSportList = categorieSportService.findAll();
//
//        return new ResponseEntity<List<CategorieSport>>(categorieSportList, HttpStatus.OK);
//    }
//}
