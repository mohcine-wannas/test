package com.ayouris.tawassol.rest.controller;

import com.ayouris.tawassol.common.model.bean.AffectationUniteBean;
import com.ayouris.tawassol.common.model.bean.ProfesseurBean;
import com.ayouris.tawassol.common.model.bean.UniteBean;
import com.ayouris.tawassol.service.ProfesseurService;
import com.ayouris.tawassol.service.UniteService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(value = "/unites")
@Api(value = "unite-api")
public class UniteController extends BaseController {

    @Autowired
    private UniteService uniteService;
    @Autowired
    private ProfesseurService professeurService;

    @RequestMapping(value = "{cycleId}/cycle", method = RequestMethod.GET)
    public ResponseEntity<List<UniteBean>> getUnitesByCycleId(@PathVariable("cycleId") Long cycleId) throws Exception {
        return new ResponseEntity<List<UniteBean>>(uniteService.findByCycleId(cycleId), HttpStatus.OK);
    }

    @RequestMapping(value = "{id:\\d+}/profs",method = RequestMethod.GET)
    public ResponseEntity<List<ProfesseurBean>> getAllProfesseursByUniteId(@PathVariable("id") Long id) throws Exception {
        return new ResponseEntity<List<ProfesseurBean>>(professeurService.getAllByUniteId(id), HttpStatus.OK);
    }

}
