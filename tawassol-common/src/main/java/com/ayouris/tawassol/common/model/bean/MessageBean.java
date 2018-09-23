package com.ayouris.tawassol.common.model.bean;

import com.ayouris.tawassol.common.enums.MessageDestinationType;
import com.ayouris.tawassol.common.model.entity.Message;
import com.ayouris.tawassol.common.model.entity.Unite;
import com.ayouris.tawassol.common.model.enums.MessageStatus;
import com.ayouris.tawassol.common.model.enums.MessageType;
import com.google.common.reflect.TypeToken;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * author: m.wannas
 */
@Setter
@Getter
public final class MessageBean implements Comparable<MessageBean> {


    @SuppressWarnings("serial")
    public static final Type LIST_BEAN_TYPE = new TypeToken<ArrayList<MessageBean>>() {
    }
            .getType();

    @SuppressWarnings("serial")
    public static final Type LIST_ENTITY_TYPE = new TypeToken<ArrayList<Message>>() {
    }
            .getType();

    private Long id;
    private Long recipientMessageId;
    private Long affectationId;
    private UserBean sender;
    private List<AffectationMessageUserBean> recipients;
    private List<AffectationMessageNiveauBean> niveaux;
    private List<AffectationMessageClasseBean> classes;
    private List<AffectationMessageUniteBean> unites;

    private Unite unite;
    private LocalDate forDate;
    private String message;
    private String attachment;

    private MessageType messageType;
    private UserBean recipient;
    private Boolean seen;
    private Boolean favoris;

    private Date createdOn;

    private MessageDestinationType messageDestinationType;
    private MessageStatus messageStatus;

    private Integer totalRecipients;
    private Integer totalOfViews;


    @Override
    public int compareTo(MessageBean o) {
        return o == null ? -1 : o.getCreatedOn() == null ? -1 : this.getCreatedOn() == null ? 1 :
                o.getCreatedOn().compareTo(this.createdOn);
    }
}