package com.ayouris.tawassol.rest.controller;

import com.ayouris.tawassol.common.model.enums.ParentingRelationship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ayouris.tawassol.common.model.bean.ParentBean;
import com.ayouris.tawassol.service.EleveService;

import io.swagger.annotations.Api;


@RestController
@RequestMapping(value = "/eleves")
@Api(value = "eleve-api")
public class EleveController extends BaseController {

	@Autowired
	private EleveService eleveService;

    @RequestMapping(value = "{codeMassar}/{parentingRelationship}/__set-parent", method = RequestMethod.PUT)
    public ResponseEntity<Long> setParent(@PathVariable("codeMassar") String codeMassar, @PathVariable("parentingRelationship") ParentingRelationship parentingRelationship,
                                          @RequestBody ParentBean parent) throws Exception {
        return new ResponseEntity<Long>(eleveService.setParent(codeMassar, parent, parentingRelationship), HttpStatus.OK);
    }
    
    @RequestMapping(value = "{id:\\d+}/enable", method = RequestMethod.PUT)
    public ResponseEntity<Long> enableParent(@PathVariable("id") Long id,@RequestBody Boolean enabled) throws Exception {
    	eleveService.enableParent(id,enabled);
    	return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @RequestMapping(value = "{codeMassar}/__exists", method = RequestMethod.GET)
    public ResponseEntity<Boolean> verifierCodeMassar(@PathVariable("codeMassar") String codeMassar) throws Exception {
    	return new ResponseEntity<>(eleveService.verifierCodeMassar(codeMassar),HttpStatus.OK);
    }

}
