package com.ayouris.tawassol.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ayouris.tawassol.common.model.bean.CycleBean;
import com.ayouris.tawassol.common.model.bean.GroupeAppellationBean;
import com.ayouris.tawassol.security.service.CycleSecurityService;
import com.ayouris.tawassol.service.GroupeAppellationService;

import io.swagger.annotations.Api;


@RestController
@RequestMapping(value = "/cycles")
@Api(value = "cycles")
public class CycleController extends BaseController {

	@Autowired
	private CycleSecurityService cycleService;
	@Autowired
	private GroupeAppellationService groupeAppellationService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<CycleBean>> getAll() throws Exception {

        return new ResponseEntity<List<CycleBean>>(cycleService.getAll(), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/groupes-appellations", method = RequestMethod.GET)
    public ResponseEntity<List<GroupeAppellationBean>> getAllGroupeAppellation() throws Exception {
    	
    	return new ResponseEntity<List<GroupeAppellationBean>>(groupeAppellationService.getAll(), HttpStatus.OK);
    }


}
