package com.ayouris.tawassol.service;

import java.util.List;

import com.ayouris.tawassol.common.enums.MessageType;
import com.ayouris.tawassol.common.model.bean.MessageBean;
import com.ayouris.tawassol.common.model.entity.Message;

/**
 * 
 * @author m.wannas
 *
 */

public interface MessageService extends GenericService<Message,Long> {


	List<MessageBean> getAll();

	List<MessageBean> getAllForValidation();

	List<MessageBean> getAllBySenderId(Long id);

	void sendMessage(MessageBean messageBean); //TODO DELETE

	void sendAdminMessage(MessageBean messageBean);

	void sendProfMessage(MessageBean messageBean);

	void validateMessage(Long messageId) throws Exception;

	void enableMessage(Long messageId) throws Exception;

	List<MessageBean> getAllMessageForParent();

	void setSeen(Long idAffectation);

	List<MessageBean> getAllMessageForParentByMessageType(MessageType messageType);

	void deleteMessage(Long id) throws Exception;
}
