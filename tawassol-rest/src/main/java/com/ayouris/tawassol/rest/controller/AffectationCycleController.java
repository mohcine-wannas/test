package com.ayouris.tawassol.rest.controller;

import com.ayouris.tawassol.common.model.bean.CycleBean;
import com.ayouris.tawassol.common.model.entity.AffectationCycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ayouris.tawassol.common.model.bean.AffectationCycleBean;
import com.ayouris.tawassol.service.AffectationCycleService;

import io.swagger.annotations.Api;

import java.util.List;


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

    @GetMapping("prof")
    public ResponseEntity<AffectationCycleBean> getAffectationCycleForProf() throws Exception {
        return new ResponseEntity<AffectationCycleBean>(affectationCycleService.getCurrentAffectationCycleForProfBean(), HttpStatus.OK);
    }


    @RequestMapping(value = "{codeSchool}/school/{cycleId}/cycle", method = RequestMethod.GET)
    public ResponseEntity<AffectationCycleBean> getAffectationCycleBySchoolCodeAndByCycleId(@PathVariable("codeSchool") String codeSchool, @PathVariable("cycleId") Long cycleId) throws Exception {

        return new ResponseEntity<>(affectationCycleService.getAffectationCycleBySchoolCodeAndByCycleId(codeSchool, cycleId), HttpStatus.OK);
    }
}
