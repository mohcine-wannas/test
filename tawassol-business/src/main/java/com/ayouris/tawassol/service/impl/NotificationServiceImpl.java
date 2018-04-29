package com.ayouris.tawassol.service.impl;

import com.ayouris.tawassol.common.mapper.CustomModelMapper;
import com.ayouris.tawassol.common.model.bean.NotificationBean;
import com.ayouris.tawassol.common.model.entity.Notification;
import com.ayouris.tawassol.common.model.entity.QNotification;
import com.ayouris.tawassol.repository.NotificationRepository;
import com.ayouris.tawassol.security.utils.SecurityUtils;
import com.ayouris.tawassol.service.NotificationService;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.List;

import static com.ayouris.tawassol.service.utils.PushUtils.initConnection;
import static com.ayouris.tawassol.service.utils.PushUtils.postNotification;

@Service
public class NotificationServiceImpl extends GenericServiceImpl2<Notification, Long, NotificationBean>
        implements NotificationService {

    @Autowired
    NotificationRepository notificationRepository;

    @Autowired
    private CustomModelMapper mapper;


    public List<NotificationBean> getAllOfUser(Long idUser) {
        QNotification notification = QNotification.notification;
        List<Notification> notifications = (List<Notification>) notificationRepository.findAll(notification.user.id.eq(idUser),
                new OrderSpecifier<>(Order.ASC, notification.isSeen),
                new OrderSpecifier<>(Order.DESC, notification.createdOn));
        return mapper.map(notifications, NotificationBean.LIST_BEAN_TYPE);
    }

    @Override
    public void create(Notification notification) {
        Notification entity = mapper.map(notification, Notification.class);
        save(entity);
    }

    @Override
    public int getNotSeenNumber(Long id) {
        QNotification notification = QNotification.notification;
        List<Notification> notifications = (List<Notification>) notificationRepository.findAll(notification.user.id.eq(id).and(notification.isSeen.isFalse()));
        return notifications.size();
    }

    @Override
    public void sendNotificationToUser(Notification notification) throws JSONException, IOException {
        create(notification);

        JSONObject json = new JSONObject();
        JSONObject msg = new JSONObject();
        JSONObject notif = new JSONObject();
        JSONObject data = new JSONObject();

        data.put("title", notification.getTitle());
        data.put("body", notification.getBody());
        data.put("id", notification.getId().toString());

        if (notification.getTarget() != null) {
            data.put("target", notification.getTarget());
        }
        json.put("message", msg);
        msg.put("notification", notif);
        notif.put("title", notification.getTitle());
        notif.put("body", notification.getBody());
        msg.put("data", data);

        HttpURLConnection connection = initConnection();

        if (notification.getUser().getFcmWebToken() != null) {
            msg.put("token", notification.getUser().getFcmWebToken());
            postNotification(connection, json);
        }

        if (notification.getUser().getFcmMobileToken() != null) {
            msg.put("token", notification.getUser().getFcmMobileToken());
            postNotification(connection, json);
        }

    }

    @Override
    public void markAsSeen(Long id) {
        Notification notification = findOne(id);
        notification.setIsSeen(true);
        save(notification);
    }

    @Override
    public void markAllAsSeen(Long id) {
        QNotification notification = QNotification.notification;
        List<Notification> notificationList = (List<Notification>) notificationRepository.findAll(notification.user.id.eq(id));
        for (Notification notif :
                notificationList) {
            notif.setIsSeen(true);
            save(notif);
        }
    }

    @Override
    public void testNotification() throws IOException, JSONException {

        Notification notification = new Notification();

        notification.setIsSeen(false);
        notification.setBody("hello world !");
        notification.setTitle("Tawassol test");
        notification.setUser(SecurityUtils.getCurrentUser());
        sendNotificationToUser(notification);

    }
}
