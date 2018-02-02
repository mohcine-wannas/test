package ma.salamgaz.gwic.rest.controller;

import ma.salamgaz.gwic.common.model.entity.Categorie;
import ma.salamgaz.gwic.service.CategorieService;
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


@RequestMapping(value = "/categorie")
public class CategorieController extends BaseController {
    private final CategorieService categorieService;

    @Autowired
    public CategorieController(CategorieService categorieService) {
        this.categorieService = categorieService;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<List<Categorie>> getCategorieList() throws Exception {
        List<Categorie> categorieList = categorieService.findAll();

        return new ResponseEntity<List<Categorie>>(categorieList, HttpStatus.OK);
    }
}
