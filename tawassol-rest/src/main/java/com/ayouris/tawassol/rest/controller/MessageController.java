package com.ayouris.tawassol.rest.controller;

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
        return new ResponseEntity<List<MessageBean>>(messageService.getAllBySenderId(id), HttpStatus.OK);
    }

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

    @GetMapping("/parent/getAll")
    public ResponseEntity getAllMessageForParent() throws Exception {
        return new ResponseEntity<>(messageService.getAllMessageForParent(), HttpStatus.OK);
    }

}
