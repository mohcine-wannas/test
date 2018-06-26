package com.ayouris.tawassol.rest.controller;

import com.ayouris.tawassol.common.model.bean.AffectationCycleBean;
import com.ayouris.tawassol.common.model.bean.AffectationUniteBean;
import com.ayouris.tawassol.common.model.bean.CycleBean;
import com.ayouris.tawassol.common.model.bean.ProfesseurBean;
import com.ayouris.tawassol.service.AffectationCycleService;
import com.ayouris.tawassol.service.AffectationUniteService;
import com.ayouris.tawassol.service.CycleService;
import com.ayouris.tawassol.service.ProfesseurService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/__register")
@Api(value = "register-api")
public class RegisterController extends BaseController {

    @Autowired
    private ProfesseurService professeurService;
    @Autowired
    private CycleService cycleService;
    @Autowired
    private AffectationUniteService affectationUniteService;
    @Autowired
    private AffectationCycleService affectationCycleService;

    @RequestMapping(value = "{codeSchool}/register-validate", method = RequestMethod.GET)
    public ResponseEntity<Boolean> validateCodeSchool(@PathVariable("codeSchool") String codeSchool) throws Exception {

        return new ResponseEntity<>(professeurService.validateCodeSchool(codeSchool), HttpStatus.OK);
    }

    @RequestMapping(value = "cycles/{codeSchool}/school", method = RequestMethod.GET)
    public ResponseEntity<List<CycleBean>> getAllBySchoolCode(@PathVariable("codeSchool") String codeSchool) throws Exception {

        return new ResponseEntity<>(cycleService.getAllBySchoolCode(codeSchool), HttpStatus.OK);
    }

    @RequestMapping(value = "/unites/{codeSchool}/school/{cycleId}/cycle", method = RequestMethod.GET)
    public ResponseEntity<List<AffectationUniteBean>> getAffectationUniteBySchoolCodeAndByCycleId(@PathVariable("codeSchool") String codeSchool, @PathVariable("cycleId") Long cycleId) throws Exception {

        return new ResponseEntity<>(affectationUniteService.getAffectationsUniteBySchoolCodeAndByCycleIdAndCurrentAnneeScolaire(codeSchool, cycleId), HttpStatus.OK);
    }

    @RequestMapping(value = "/affectation-cycle/{codeSchool}/school/{cycleId}/cycle", method = RequestMethod.GET)
    public ResponseEntity<AffectationCycleBean> getAffectationCycleBySchoolCodeAndByCycleId(@PathVariable("codeSchool") String codeSchool, @PathVariable("cycleId") Long cycleId) throws Exception {

        return new ResponseEntity<>(affectationCycleService.getAffectationCycleBySchoolCodeAndByCycleIdAndCurrentAnneeScolaire(codeSchool, cycleId), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Long> create(@RequestBody ProfesseurBean professeur) throws Exception {
        return new ResponseEntity<Long>(professeurService.create(professeur), HttpStatus.OK);
    }
}
