package com.ayouris.tawassol.rest.controller;

import com.ayouris.tawassol.common.model.bean.NotificationBean;
import com.ayouris.tawassol.service.NotificationService;
import io.swagger.annotations.Api;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping(value = "/notifications")
@Api(value = "notification-api")
public class NotificationController extends BaseController {

    @Autowired
    private NotificationService notificationService;

    @RequestMapping(value = "{userId}", method = RequestMethod.GET)
    public ResponseEntity<List<NotificationBean>> getAllFromUser(@PathVariable("userId") Long userId) {
        return new ResponseEntity<>(this.notificationService.getAllOfUser(userId), HttpStatus.OK);
    }


    @RequestMapping(value = "{userId}/number", method = RequestMethod.GET)
    public ResponseEntity<Integer> getNotSeenNumber(@PathVariable("userId") Long userId) {
        return new ResponseEntity<>(this.notificationService.getNotSeenNumber(userId), HttpStatus.OK);
    }


    @RequestMapping(value = "{userId}/seen/all", method = RequestMethod.GET)
    public ResponseEntity<Boolean> markAllSeen(@PathVariable("userId") Long id) {
        this.notificationService.markAllAsSeen(id);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

    @RequestMapping(value = "{tokenId}/seen", method = RequestMethod.GET)
    public ResponseEntity<Boolean> markSeen(@PathVariable("tokenId") Long id) {
        this.notificationService.markAsSeen(id);
        return new ResponseEntity<>(true, HttpStatus.OK);

    }

    @RequestMapping(value = "test", method = RequestMethod.GET)
    public ResponseEntity<Void> markSeen() throws IOException, JSONException {
        this.notificationService.testNotification();
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
