package com.ayouris.tawassol.rest.controller;

import com.ayouris.tawassol.common.model.bean.ParentBean;
import com.ayouris.tawassol.service.ParentService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/parents")
@Api(value = "parent-api")
public class ParentController extends BaseController {

	@Autowired
	private ParentService parentService;

    @GetMapping("/{id:\\d+}/__is-enabled")
    public ResponseEntity<Boolean> isEnabled(@PathVariable Long id) throws Exception {
        return new ResponseEntity<Boolean>(parentService.isValidated(id), HttpStatus.OK);
    }


}
