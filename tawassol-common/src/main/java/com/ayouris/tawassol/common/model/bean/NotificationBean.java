package com.ayouris.tawassol.common.model.bean;

import com.ayouris.tawassol.common.model.entity.Notification;
import com.google.common.reflect.TypeToken;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;

@Setter
@Getter
public class NotificationBean implements Serializable {

    private static final long serialVersionUID = 314869216015470352L;

    @SuppressWarnings("serial")
    public static final Type LIST_BEAN_TYPE = new TypeToken<ArrayList<NotificationBean>>() {
    }.getType();

    @SuppressWarnings("serial")
    public static final Type LIST_ENTITY_TYPE = new TypeToken<ArrayList<Notification>>() {
    }.getType();

    private String title;

    private String body;

    private Date createdOn;

    private Boolean isSeen;

    private String target;

}
