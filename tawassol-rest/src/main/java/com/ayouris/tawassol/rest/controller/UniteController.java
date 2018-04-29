package com.ayouris.tawassol.rest.controller;

import com.ayouris.tawassol.common.model.bean.UniteBean;
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

    @RequestMapping(value = "{cycleId}/cycle", method = RequestMethod.GET)
    public ResponseEntity<List<UniteBean>> getUnitesByCycleId(@PathVariable("cycleId") Long cycleId) throws Exception {
        return new ResponseEntity<List<UniteBean>>(uniteService.findByCycleId(cycleId), HttpStatus.OK);
    }

    /*@RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<UniteBean>> getAll() throws Exception {
        return new ResponseEntity<List<UniteBean>>(uniteService.getAll(), HttpStatus.OK);
    }
    */

}
