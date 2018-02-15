package com.ayouris.tawassol.security.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ayouris.tawassol.security.service.PasswordService;

@RestController
@RequestMapping(value = "/users")
public class UserController {

	@Autowired
	private PasswordService passwordService;

    @RequestMapping(value = "/current/password-change/", method = RequestMethod.PUT)
    public ResponseEntity<Long> passwordChange(@RequestBody Map<String, String> bean) throws Exception {
    	return new ResponseEntity<Long>(passwordService.passwordChange(bean), HttpStatus.OK);
    };


}
