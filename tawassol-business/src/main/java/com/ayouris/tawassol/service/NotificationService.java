package com.ayouris.tawassol.service;

import com.ayouris.tawassol.common.model.bean.NotificationBean;
import com.ayouris.tawassol.common.model.entity.Notification;
import org.json.JSONException;

import java.io.IOException;
import java.util.List;

public interface NotificationService extends GenericService<Notification, Long> {

    void create(Notification notification);

    int getNotSeenNumber(Long id);

    void sendNotificationToUser(Notification notification) throws JSONException, IOException;

    void markAsSeen(Long id);

    void markAllAsSeen(Long id);

    List<NotificationBean> getAllOfUser(Long idUser);

    void testNotification() throws IOException, JSONException;

}
