package com.ayouris.tawassol.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import com.ayouris.tawassol.common.model.bean.SchoolBean;
import com.ayouris.tawassol.service.SchoolService;


@RestController
@RequestMapping(value = "/ecoles")
@Api(value = "school-api")
public class SchoolController extends BaseController {

	@Autowired
	private SchoolService schoolService;

    @RequestMapping( method = RequestMethod.PUT)
    public ResponseEntity<Long> updateSchool(@RequestBody SchoolBean school) throws Exception {
        return new ResponseEntity<Long>(schoolService.update(school), HttpStatus.OK);
    }


}
