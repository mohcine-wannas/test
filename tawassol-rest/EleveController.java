package com.ayouris.tawassol.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import com.ayouris.tawassol.common.model.bean.EleveBean;
import com.ayouris.tawassol.service.EleveService;


@RestController
@RequestMapping(value = "/ecoles")
@Api(value = "eleve-api")
public class EleveController extends BaseController {

	@Autowired
	private EleveService eleveService;

    @RequestMapping( method = RequestMethod.GET)
    public ResponseEntity<Long> updateEleve(@RequestBody EleveBean eleve) throws Exception {
        return new ResponseEntity<Long>(eleveService.update(eleve), HttpStatus.OK);
    }


}
