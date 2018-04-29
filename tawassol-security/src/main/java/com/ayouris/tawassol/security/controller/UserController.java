package com.ayouris.tawassol.security.controller;

import com.ayouris.tawassol.security.service.PasswordService;
import com.ayouris.tawassol.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private PasswordService passwordService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/current/password-change/", method = RequestMethod.PUT)
    public ResponseEntity<Long> passwordChange(@RequestBody Map<String, String> bean) throws Exception {
        return new ResponseEntity<Long>(passwordService.passwordChange(bean), HttpStatus.OK);
    }

    ;

    @RequestMapping(value = "{id}/fcmWebToken", method = RequestMethod.PUT)
    public ResponseEntity setFcmWebToken(@PathVariable("id") Long id, @RequestBody Map<String, String> token) throws Exception {
        userService.setFCMToken(id, token.get("token"), false);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "{id}/fcmMobileToken", method = RequestMethod.PUT)
    public ResponseEntity setFcmMobileToken(@PathVariable("id") Long id, @RequestBody Map<String, String> token) throws Exception {
        userService.setFCMToken(id, token.get("token"), true);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @RequestMapping(value = "{id}/fcmWebToken", method = RequestMethod.DELETE)
    public ResponseEntity clearFcmWebToken(@PathVariable("id") Long id) throws Exception {
        userService.clearFCMToken(id, false);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @RequestMapping(value = "{id}/fcmMobileToken", method = RequestMethod.DELETE)
    public ResponseEntity clearFcmMobileToken(@PathVariable("id") Long id) throws Exception {
        userService.clearFCMToken(id, true);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
