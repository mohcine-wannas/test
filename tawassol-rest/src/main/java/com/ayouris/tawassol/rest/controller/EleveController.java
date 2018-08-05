package com.ayouris.tawassol.rest.controller;

import com.ayouris.tawassol.common.model.bean.EleveBean;
import com.ayouris.tawassol.common.model.bean.ParentBean;
import com.ayouris.tawassol.common.model.bean.AffectationParentEleveBean;
import com.ayouris.tawassol.common.model.bean.EleveBean;
import com.ayouris.tawassol.common.model.bean.AffectationParentEleveBean;
import com.ayouris.tawassol.common.model.bean.EleveBean;
import com.ayouris.tawassol.common.model.enums.ParentingRelationship;
import com.ayouris.tawassol.service.EleveService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ayouris.tawassol.common.model.bean.ParentBean;
import com.ayouris.tawassol.service.EleveService;

import io.swagger.annotations.Api;

import java.util.List;


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
    public ResponseEntity<Long> enableParent(@PathVariable("id") Long id, @RequestBody Boolean enabled) throws Exception {
        eleveService.enableParent(id, enabled);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "{codeMassar}/__exists", method = RequestMethod.GET)
    public ResponseEntity<Boolean> verifierCodeMassar(@PathVariable("codeMassar") String codeMassar) throws Exception {
        return new ResponseEntity<>(eleveService.verifierCodeMassar(codeMassar), HttpStatus.OK);
    }

    @PutMapping(value = "{codeMassar}/{parentingRelationship}/set-parent")
    public ResponseEntity<Boolean> addStudent(@PathVariable("codeMassar") String codeMassar,@PathVariable("parentingRelationship") ParentingRelationship parentingRelationship) throws Exception {
        return new ResponseEntity<>(eleveService.addStudent(codeMassar,parentingRelationship),HttpStatus.OK);
    }
    @GetMapping(value = "/by-parent")
    public ResponseEntity<List<AffectationParentEleveBean>> getAllByCurrentParent() throws Exception {
        return new ResponseEntity<>(eleveService.getAllByCurrentParent(),HttpStatus.OK);
    }
    @DeleteMapping(value = "/delete-affectation/{id}")
    public ResponseEntity<Boolean> deleteAffectation(@PathVariable("id") Long idAffectation) throws Exception {
        return new ResponseEntity<>(eleveService.deleteAffectation(idAffectation) ,HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Long> create(@RequestBody EleveBean eleve) throws Exception {
        return new ResponseEntity<Long>(eleveService.create(eleve), HttpStatus.OK);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<Long> update(@PathVariable("id") Long id, @RequestBody EleveBean eleve) throws Exception {
        return new ResponseEntity<Long>(eleveService.update(id, eleve), HttpStatus.OK);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity<EleveBean> get(@PathVariable("id") Long id) throws Exception {
        return new ResponseEntity<EleveBean>(eleveService.getEleve(id), HttpStatus.OK);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Long> delete(@PathVariable("id") Long id) throws Exception {
        eleveService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "enable-all", method = RequestMethod.PUT)
    public ResponseEntity<Long> enableAllEleve(@RequestBody Boolean enabled) throws Exception {
        eleveService.enableAllEleve(enabled);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "{id}/enable-eleve", method = RequestMethod.PUT)
    public ResponseEntity<Long> enableEleve(@PathVariable("id") Long id, @RequestBody Boolean enabled) throws Exception {
        eleveService.enableEleve(id, enabled);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
