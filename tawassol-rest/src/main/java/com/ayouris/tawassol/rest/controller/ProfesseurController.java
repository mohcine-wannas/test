package com.ayouris.tawassol.rest.controller;

import com.ayouris.tawassol.common.model.bean.ProfesseurBean;
import com.ayouris.tawassol.common.model.bean.ProfesseurEditBean;
import com.ayouris.tawassol.service.ProfesseurService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(value = "/professeurs")
@Api(value = "professeur-api")
public class ProfesseurController extends BaseController {

    @Autowired
    private ProfesseurService professeurService;

    @RequestMapping(value = "{codeSchool}/register-validate", method = RequestMethod.GET)
    public ResponseEntity<Boolean> validateCodeSchool(@PathVariable("codeSchool") String codeSchool) throws Exception {

        return new ResponseEntity<>(professeurService.validateCodeSchool(codeSchool), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Long> create(@RequestBody ProfesseurBean professeur) throws Exception {
        return new ResponseEntity<Long>(professeurService.create(professeur), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<ProfesseurEditBean>> getAllByCurrentSchool() throws Exception {
        return new ResponseEntity<>(professeurService.getAllByCurrentSchool(), HttpStatus.OK);
    }


    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Long> delete(@PathVariable("id") Long id) throws Exception {
        professeurService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "{id}/password-change/", method = RequestMethod.PUT)
    public ResponseEntity<Long> passwordChange(@PathVariable("id") Long id, @RequestBody Map<String, String> bean) throws Exception {
        return new ResponseEntity<Long>(professeurService.passwordChange(id, bean), HttpStatus.OK);
    }

    @RequestMapping(value = "{id}/cycle", method = RequestMethod.GET)
    public ResponseEntity<List<ProfesseurEditBean>> getAllByCycleIdAndCurrentSchool(@PathVariable("id") Long id) throws Exception {
        return new ResponseEntity<>(professeurService.getAllByCycleIdAndCurrentSchoolAndCurrentAnneScolaire(id), HttpStatus.OK);
    }

    @RequestMapping(value = "enable-all", method = RequestMethod.PUT)
    public ResponseEntity<Long> enableAllProf(@RequestBody Boolean enabled) throws Exception {
        professeurService.enableAllProf(enabled);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "{id}/enable", method = RequestMethod.PUT)
    public ResponseEntity<Long> enableProf(@PathVariable("id") Long id, @RequestBody Boolean enabled) throws Exception {
        professeurService.enableProf(id, enabled);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @RequestMapping(value = "{id}/unites", method = RequestMethod.PUT)
    public ResponseEntity<Long> updateProfUnites(@PathVariable("id") Long id, @RequestBody ProfesseurBean professeur) throws Exception {
        return new ResponseEntity<>(professeurService.updateProfUnites(id, professeur), HttpStatus.OK);
    }


    @RequestMapping(value = "{id}/niveaux-classes", method = RequestMethod.PUT)
    public ResponseEntity<Long> updateProfNivauxClasses(@PathVariable("id") Long id, @RequestBody ProfesseurBean professeur) throws Exception {
        return new ResponseEntity<>(professeurService.updateProfNivauxClasses(id, professeur), HttpStatus.OK);
    }
}
