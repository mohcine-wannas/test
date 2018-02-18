package com.ayouris.tawassol.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ayouris.tawassol.common.model.bean.AffectationCycleBean;
import com.ayouris.tawassol.service.AffectationCycleService;

import io.swagger.annotations.Api;


@RestController
@RequestMapping(value = "/affectation-cycle")
@Api(value = "affectationCycle-api")
public class AffectationCycleController extends BaseController {

	@Autowired
	private AffectationCycleService affectationCycleService;

    @RequestMapping( method = RequestMethod.PUT)
    public ResponseEntity<Long> saveAffectationCycle(@RequestBody AffectationCycleBean affectationCycle) throws Exception {
        return new ResponseEntity<Long>(affectationCycleService.save(affectationCycle), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<AffectationCycleBean> getAffectationCycle() throws Exception {
        return new ResponseEntity<AffectationCycleBean>(affectationCycleService.getCurrentAffectationCycleBean(), HttpStatus.OK);
    }
}
