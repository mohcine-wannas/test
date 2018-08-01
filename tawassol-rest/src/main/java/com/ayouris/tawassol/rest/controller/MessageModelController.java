package com.ayouris.tawassol.rest.controller;

import com.ayouris.tawassol.common.model.bean.MessageBean;
import com.ayouris.tawassol.common.model.bean.MessageModelAdminBean;
import com.ayouris.tawassol.common.model.bean.MessageModelBean;
import com.ayouris.tawassol.common.model.bean.MessageModelProfBean;
import com.ayouris.tawassol.service.MessageModelService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/message-models")
@Api(value = "message-model-api")
public class MessageModelController extends BaseController {

    @Autowired
    private MessageModelService messageModelService;

    @RequestMapping(value = "category-admin/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<MessageModelAdminBean>> getMessageModelsByCategorieAdminId(@PathVariable("id") Long id) throws Exception {
        return new ResponseEntity<>(messageModelService.findAllByCategorieAdminId(id), HttpStatus.OK);
    }

    @RequestMapping(value = "category-prof/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<MessageModelProfBean>> getMessageModelsByCategorieProfId(@PathVariable("id") Long id) throws Exception {
        return new ResponseEntity<>(messageModelService.findAllByCategorieProfId(id), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteProfMessage(@PathVariable("id") Long id) throws Exception {
        messageModelService.deleteMessageModel(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Long> create(@RequestBody MessageModelBean messageModelBean) throws Exception {
        return new ResponseEntity<Long>(messageModelService.create(messageModelBean), HttpStatus.OK);
    }

    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    public ResponseEntity<Long> update(@PathVariable("id") Long id, @RequestBody MessageModelBean messageModelBean) throws Exception {
        return new ResponseEntity<Long>(messageModelService.update(id, messageModelBean), HttpStatus.OK);
    }

}
