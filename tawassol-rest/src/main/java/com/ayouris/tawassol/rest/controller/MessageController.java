package com.ayouris.tawassol.rest.controller;

import com.ayouris.tawassol.common.enums.MessageType;
import com.ayouris.tawassol.common.model.bean.MessageBean;
import com.ayouris.tawassol.service.MessageService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/messages")
@Api(value = "message-api")
public class MessageController extends BaseController {

	@Autowired
	private MessageService messageService;

    @RequestMapping(value = "{id:\\d+}/eleves",method = RequestMethod.GET)
    public ResponseEntity<List<MessageBean>> getAllMessageBySender(@PathVariable("id") Long id) throws Exception {
        return new ResponseEntity<>(messageService.getAllBySenderId(id), HttpStatus.OK);
    }

    @RequestMapping(value = "admin/not-validated/",method = RequestMethod.GET)
    public ResponseEntity<List<MessageBean>> getAllMessageForValidation() throws Exception {
        //TODO if current user is administrateur
        return new ResponseEntity<>(messageService.getAllForValidation(), HttpStatus.OK);
    }

    @GetMapping("prof/not-validated/")
    public ResponseEntity<List<MessageBean>> getAllProfNotYetValidatedMessages() throws Exception {
        return new ResponseEntity<>(messageService.getAllProfMessages(false), HttpStatus.OK);
    }

    @GetMapping("prof/validated/")
    public ResponseEntity<List<MessageBean>> getAllProfValidatedMessages() throws Exception {
        return new ResponseEntity<>(messageService.getAllProfMessages(true), HttpStatus.OK);
    }

    //TODO Remove
    @PostMapping("/send")
    public ResponseEntity sendMessage(@RequestBody MessageBean messageBean) throws Exception {
        messageService.sendMessage(messageBean);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/admin/send")
    public ResponseEntity sendAdminMessage(@RequestBody MessageBean messageBean) throws Exception {
        messageService.sendAdminMessage(messageBean);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/admin/sendToProf")
    public ResponseEntity sendAdminMessageToProf(@RequestBody MessageBean messageBean) throws Exception {
        messageService.sendAdminMessageToProf(messageBean);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping("/prof/send")
    public ResponseEntity sendProfMessage(@RequestBody MessageBean messageBean) throws Exception {
        messageService.sendProfMessage(messageBean);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/parent/get-all")
    public ResponseEntity getAllMessageForParent() throws Exception {
        return new ResponseEntity<>(messageService.getAllMessageForParent(), HttpStatus.OK);
    }

    @GetMapping("/prof/get-all")
    public ResponseEntity getAllMessageForProf() throws Exception {
        return new ResponseEntity<>(messageService.getAllMessageForProf(), HttpStatus.OK);
    }

    @GetMapping("/parent/get-all/{messageType}")
    public ResponseEntity getAllMessageForParentByMessageType(@PathVariable("messageType") MessageType messageType) throws Exception {
        return new ResponseEntity<>(messageService.getAllMessageForParentByMessageType(messageType), HttpStatus.OK);
    }

    @PutMapping("{id:\\d+}/seen")
    public ResponseEntity setSeen(@PathVariable("id") Long idAffectation) throws Exception {
        messageService.setSeen(idAffectation);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/prof/{id:\\d+}/seen")
    public ResponseEntity setSeenProf(@PathVariable("id") Long idAffectation) throws Exception {
        messageService.setSeen(idAffectation);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @PutMapping("admin/{id:\\d+}/enable")
    public ResponseEntity setValidated(@PathVariable("id") Long id) throws Exception {
        messageService.enableMessage(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }


    @DeleteMapping("admin/{id:\\d+}")
    public ResponseEntity<List<MessageBean>> deleteMessage(@PathVariable("id") Long id) throws Exception {
        messageService.deleteMessage(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("prof/{id:\\d+}")
    public ResponseEntity<List<MessageBean>> deleteProfMessage(@PathVariable("id") Long id) throws Exception {
        messageService.deleteMessage(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }


}
