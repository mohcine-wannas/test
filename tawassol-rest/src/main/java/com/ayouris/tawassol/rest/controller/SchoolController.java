package com.ayouris.tawassol.rest.controller;

import com.google.common.io.CountingOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;

import com.ayouris.tawassol.common.exception.ErrorMessageType;
import com.ayouris.tawassol.common.model.bean.SchoolBean;
import com.ayouris.tawassol.service.SchoolService;
import com.ayouris.tawassol.service.ServiceException;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.ws.Service;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Files;


@RestController
@RequestMapping(value = "/ecoles")
@Api(value = "school-api")
public class SchoolController extends BaseController {

	@Autowired
	private SchoolService schoolService;

    @RequestMapping( method = RequestMethod.PUT)
    public ResponseEntity<Long> updateSchool(@RequestBody SchoolBean school) throws Exception {
		validateRequiredFields(school);
        return new ResponseEntity<Long>(schoolService.update(school), HttpStatus.OK);
    }
    
	private void validateRequiredFields(SchoolBean schoolBean) {
		if(schoolBean.getId() == null) {
			throw new ServiceException(ErrorMessageType.MISSING_REQUIRED_FIELDS);

		}
		if(schoolBean.getCycles() == null || schoolBean.getCycles().isEmpty() ) {
			throw new ServiceException(ErrorMessageType.SCHOOL_NO_CYCLE);
		}

	}


	@PostMapping(value = "change-logo", consumes="multipart/form-data" )
	public  ResponseEntity<Long>  upload( @RequestPart("file") MultipartFile multipartFile) throws Exception {
			InputStream in = multipartFile.getInputStream();

			//TODO validation
            schoolService.updateLogo(in);
            return new ResponseEntity<>(HttpStatus.OK);
	}
}
