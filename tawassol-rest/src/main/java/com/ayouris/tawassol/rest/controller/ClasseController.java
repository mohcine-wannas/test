package com.ayouris.tawassol.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ayouris.tawassol.common.model.bean.EleveBean;
import com.ayouris.tawassol.common.model.entity.Eleve;
import com.ayouris.tawassol.service.EleveService;

import io.swagger.annotations.Api;


@RestController
@RequestMapping(value = "/classes")
@Api(value = "classe-api")
public class ClasseController extends BaseController {

	@Autowired
	private EleveService eleveService;

    @RequestMapping(value = "{id:\\d+}/eleves",method = RequestMethod.GET)
    public ResponseEntity<List<EleveBean>> getAffectationCycle(@PathVariable("id") Long id) throws Exception {
        return new ResponseEntity<List<EleveBean>>(eleveService.getAllByClasseId(id), HttpStatus.OK);
    }
}
