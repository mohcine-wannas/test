package com.ayouris.tawassol.rest.controller;

import com.ayouris.tawassol.common.model.bean.AffectationUniteBean;
import com.ayouris.tawassol.service.AffectationUniteService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/affectations-unite")
@Api(value = "affectations-unite-api")
public class AffectationUniteController extends BaseController {

    @Autowired
    private AffectationUniteService affectationUniteService;

    @RequestMapping(value = "{cycleId}/cycle", method = RequestMethod.GET)
    public ResponseEntity<List<AffectationUniteBean>> getAffectationsUniteByCycle(@PathVariable("cycleId") Long cycleId) throws Exception {
        return new ResponseEntity<>(affectationUniteService
                .getAffectationsUniteByCycleId(cycleId), HttpStatus.OK);
    }

    @GetMapping(value = "/")
    public ResponseEntity<List<AffectationUniteBean>> getAffectationsUnite() throws Exception {
        return new ResponseEntity<>(affectationUniteService
                .getAffectationsUnite(), HttpStatus.OK);
    }


    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity updateAffectationsUnite(@RequestBody List<AffectationUniteBean> affectation) throws Exception {
        affectationUniteService.updateAffectationsUnite(affectation);
        return new ResponseEntity(HttpStatus.OK);
    }

}