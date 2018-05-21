package com.ayouris.tawassol.rest.controller;

import com.ayouris.tawassol.common.model.bean.EleveBean;
import com.ayouris.tawassol.service.EleveService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;


@RestController
@RequestMapping(value = "/classes")
@Api(value = "classe-api")
public class ClasseController extends BaseController {

	@Autowired
	private EleveService eleveService;

    @RequestMapping(value = "{id:\\d+}/eleves",method = RequestMethod.GET)
    public ResponseEntity<List<EleveBean>> getAllElevesByClasse(@PathVariable("id") Long id) throws Exception {
        return new ResponseEntity<List<EleveBean>>(eleveService.getAllByClasseId(id), HttpStatus.OK);
    }

    @RequestMapping(value = "{idClasse:\\d+}/upload",method = RequestMethod.POST)
    public ResponseEntity<Integer> handleMassarFileUpload(@PathVariable Long idClasse, @RequestParam("file") MultipartFile file) throws Exception {
        InputStream in = file.getInputStream();
        return new ResponseEntity<Integer>(eleveService.importFromMassarFileUpload(idClasse,in), HttpStatus.OK);

    }

}
