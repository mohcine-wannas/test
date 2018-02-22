package com.ayouris.tawassol.security.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ayouris.tawassol.common.model.bean.UserDataAuthenticate;
import com.ayouris.tawassol.security.model.UserData;

@RestController
@RequestMapping(value = "/auth")
public interface AuthenticateController {



    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<UserData> authenticate(@RequestBody UserDataAuthenticate user,HttpServletRequest request) throws Exception ;


}
