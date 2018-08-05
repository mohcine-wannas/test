package com.ayouris.tawassol.service;

import java.util.List;

import com.ayouris.tawassol.common.enums.MessageDestinationType;
import com.ayouris.tawassol.common.model.bean.MessageBean;
import com.ayouris.tawassol.common.model.bean.ViewBean;
import com.ayouris.tawassol.common.model.entity.Message;
import com.ayouris.tawassol.common.model.enums.MessageStatus;
import com.ayouris.tawassol.common.model.enums.MessageType;

/**
 * @author m.wannas
 */

public interface MessageService extends GenericService<Message, Long> {


    List<MessageBean> getAll();

    List<MessageBean> getAllForValidation();

    List<MessageBean> getAllProfMessages(Boolean valid);

    List<MessageBean> getAllProfMessages(MessageStatus messageStatus);

    List<MessageBean> getAllBySenderId(Long id);

    void sendMessage(MessageBean messageBean); //TODO DELETE

    void sendAdminMessage(MessageBean messageBean);

    void sendAdminMessageToProf(MessageBean messageBean);

    void sendProfMessage(MessageBean messageBean);

    void validateMessage(Long messageId) throws Exception;

    void enableMessage(Long messageId) throws Exception;

    List<MessageBean> getAllMessageForParent();

    List<MessageBean> getAllMessageForProf();

    void setSeen(Long idAffectation);


    List<MessageBean> getAllMessageForParentByMessageType(MessageType messageType);

    List<MessageBean> getAllMessageByMessageDestinationType(MessageDestinationType messageDestinationType);

    void rejectMessage(Long id) throws Exception;

    void deleteMessage(Long id) throws Exception;

     List<ViewBean> getViewsDetails(Long id);

}
