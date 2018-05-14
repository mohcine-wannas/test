package com.ayouris.tawassol.service.impl;

import com.ayouris.tawassol.admin.model.entity.User;
import com.ayouris.tawassol.common.mapper.CustomModelMapper;
import com.ayouris.tawassol.common.model.bean.MessageBean;
import com.ayouris.tawassol.common.model.entity.*;
import com.ayouris.tawassol.repository.MessageRepository;
import com.ayouris.tawassol.security.utils.SecurityUtils;
import com.ayouris.tawassol.service.AffectationParentEleveService;
import com.ayouris.tawassol.service.ClasseService;
import com.ayouris.tawassol.service.EleveService;
import com.ayouris.tawassol.service.MessageService;
import com.querydsl.jpa.JPAExpressions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * 
 * @author m.wannas
 *
 */

@Service
public class MessageServiceImpl extends GenericServiceImpl2<Message,Long,MessageBean> implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private CustomModelMapper mapper;

    @Autowired
    private ClasseService classeService;

    @Autowired
    private EleveService eleveService;

    @Autowired
    private AffectationParentEleveService affectationParentEleveService;

    @Override
    public List<MessageBean> getAll() {
        List<Message> messages = findAll();
        return mapper.map(messages, MessageBean.LIST_BEAN_TYPE);
    }

	@Override
	public List<MessageBean> getAllBySenderId(Long id) {
		QMessage message = QMessage.message1;
		Iterable<Message> list = messageRepository.findAll(message.sender.id.eq(id));
		return mapper.map(list, MessageBean.LIST_BEAN_TYPE);
	}

    @Override
    public void sendMessage(MessageBean messageBean) {
        Message message =mapper.map(messageBean, Message.class);
        sendMessage(message);
    }

    @Override
    public void sendAdminMessage(MessageBean messageBean) {
        Message message =mapper.map(messageBean, Message.class);
        User currentUser = SecurityUtils.getCurrentUser();
        message.setSender(currentUser);
        sendMessage(message);
    }

    private void sendMessage(Message message) {



        List<AffectationMessageUser> users = message.getRecipients();
        message.setRecipients(null);
        save(message);
        flush();

        if(message.getSender().getAutoSendMessage() != null && message.getSender().getAutoSendMessage()) {
            message.setValidated(true);
        }

        if(users != null) {
            for(AffectationMessageUser user : users) {
                user.setMessage(message);
            }
            message.setRecipients(users);
        }
        if(message.getClasses() != null) {
            for (AffectationMessageClasse classe : message.getClasses()) {
                classe.setMessage(message);
                setElevesToMessageFromClasse(message, classe.getClasse());

            }
        }
        if(message.getNiveaux() != null) {
            for (AffectationMessageNiveau niveau : message.getNiveaux()) {
                niveau.setMessage(message);
                List<Classe> classes = classeService.getClassesByNiveauId(niveau.getNiveau().getId());
                for(Classe classe : classes) {
                    setElevesToMessageFromClasse(message, classe);
                }
            }
        }

        if(message.getUnites() != null) {
            for (AffectationMessageUnite unite : message.getUnites()) {
                unite.setMessage(message);
            }
        }
        save(message);
    }



    private void setElevesToMessageFromClasse(Message message, Classe classe) {
        List<Eleve> eleves = eleveService.getElevesByClasseId(classe.getId());

        List<AffectationMessageUser> affecations = new ArrayList<>();
        for(Eleve eleve :eleves) {
            AffectationMessageUser affectation = new AffectationMessageUser();
            affectation.setMessage(message);
            affectation.setUser(eleve);
            affecations.add(affectation);
        }
        if(!affecations.isEmpty()) {
            message.setRecipients(affecations);
        }
    }

    @Override
    public void validateMessage(Long messageId) throws Exception {
        Message message = findOne(messageId);
        if(message == null) {
            throw new Exception("Message not found");
        }

        message.setValidated(true);
    }

    @Override
    public List<MessageBean> getAllMessageForParent() {
        //  validateIsparent
        List<Message> messages = new ArrayList<>();
        User currentUser = SecurityUtils.getCurrentUser();
        Parent parent = (Parent) currentUser;
        List<AffectationParentEleve> affectations = affectationParentEleveService.findByParent(parent);
        for(AffectationParentEleve affectation : affectations) {
            if(affectation.getEnabled() != nulll && affectation.getEnabled()) {
                Eleve eleve = affectation.getEleve();
                if(eleve.isEnabled()) {
                    messages.addAll(getAllMessageByUser(eleve));
                }
            }
        }

        List<MessageBean> messageBeans =  mapper.map(messages, MessageBean.LIST_BEAN_TYPE);
        for(MessageBean message : messageBeans) {
            message.setClasses(null);
            message.setRecipients(null);
            message.setNiveaux(null);
        }
    }

    private List<Message> getAllMessageByUser(User user) {
        QMessage message = QMessage.message1;
        QAffectationMessageUser affectationMessageUser = QAffectationMessageUser.affectationMessageUser;
        return (List<Message>) messageRepository.findAll(message.id.in(JPAExpressions.selectFrom(affectationMessageUser)
                .where(affectationMessageUser.user.id.eq(user.getId()))
                .select(affectationMessageUser.message.id)));
    }


}
