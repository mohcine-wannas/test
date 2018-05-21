package com.ayouris.tawassol.rest.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.ayouris.tawassol.common.model.bean.ClasseBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ayouris.tawassol.common.model.bean.EleveBean;
import com.ayouris.tawassol.common.model.bean.NiveauBean;
import com.ayouris.tawassol.service.ClasseService;
import com.ayouris.tawassol.service.NiveauService;

import io.swagger.annotations.Api;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping(value = "/niveaux")
@Api(value = "classe-api")
public class NiveauController extends BaseController {

	@Autowired
	private NiveauService niveauService;
	
	@Autowired
	private ClasseService classeService;

    @RequestMapping(value = "{id:\\d+}/classes",method = RequestMethod.GET)
    public ResponseEntity<List<ClasseBean>> getAllClasseByNiveau(@PathVariable("id") Long id) throws Exception {
        return new ResponseEntity<List<ClasseBean>>(classeService.getAllByNiveauId(id), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public ResponseEntity<List<NiveauBean>> getAll() throws Exception {
    	return new ResponseEntity<List<NiveauBean>>(niveauService.getAll(), HttpStatus.OK);
    }



}
