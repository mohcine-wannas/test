package com.ayouris.tawassol.service.impl;

import com.ayouris.tawassol.admin.model.entity.User;
import com.ayouris.tawassol.common.enums.MessageDestinationType;
import com.ayouris.tawassol.common.mapper.CustomModelMapper;
import com.ayouris.tawassol.common.model.bean.MessageBean;
import com.ayouris.tawassol.common.model.bean.ParentBean;
import com.ayouris.tawassol.common.model.bean.UserBean;
import com.ayouris.tawassol.common.model.bean.ViewBean;
import com.ayouris.tawassol.common.model.entity.*;
import com.ayouris.tawassol.common.model.enums.MessageStatus;
import com.ayouris.tawassol.common.model.enums.MessageType;
import com.ayouris.tawassol.repository.AffectationMessageUserRepository;
import com.ayouris.tawassol.repository.MessageRepository;
import com.ayouris.tawassol.security.utils.SecurityUtils;
import com.ayouris.tawassol.service.*;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.JPAExpressions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;


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
    private AffectationMessageUserRepository affectationMessageUserRepository;

    @Autowired
    private CustomModelMapper mapper;

    @Autowired
    private ClasseService classeService;

    @Autowired
    private EleveService eleveService;

    @Autowired
    private ProfesseurService professeurService;

    @Autowired
    private AffectationParentEleveService affectationParentEleveService;

    @Override
    public List<MessageBean> getAll() {
        List<Message> messages = findAll();
        return mapper.map(messages, MessageBean.LIST_BEAN_TYPE);
    }

    @Override
    public List<MessageBean> getAllForValidation() {
        QMessage message = QMessage.message1;
        //TODO use messageStatus instead of validated
        List<Message> list = (List<Message>)  messageRepository.findAll(message.validated.isFalse().or(message.validated.isNull()));
        traiterLesMessages(list);
        List<MessageBean> messageBeans =  mapper.map(list, MessageBean.LIST_BEAN_TYPE);
        Collections.sort(messageBeans);
        return messageBeans;

    }

    @Override
    public List<MessageBean> getAllProfMessages(Boolean valid) {
        User currentUser = SecurityUtils.getCurrentUser();
        List<Message> list = getAllMessageByUser(currentUser,valid);

        traiterLesMessages(list);

        List<MessageBean> messageBeans =  mapper.map(list, MessageBean.LIST_BEAN_TYPE);
        Collections.sort(messageBeans);
        return messageBeans;

    }

    @Override
    public List<MessageBean> getAllProfMessages(MessageStatus messageStatus) {
        User currentUser = SecurityUtils.getCurrentUser();
        List<Message> list = getAllMessageByUser(currentUser, messageStatus);

        traiterLesMessages(list);

        List<MessageBean> messageBeans =  mapper.map(list, MessageBean.LIST_BEAN_TYPE);
        Collections.sort(messageBeans);
        return messageBeans;

    }


    private void traiterLesMessages(List<Message> list) {
        list.forEach(item -> {
            item.getRecipients().removeIf((affectationUser) -> {
                User user =  affectationUser.getUser();
                if(user instanceof Eleve) {
                    Eleve recipient = (Eleve) user;
                    for(AffectationMessageNiveau affectationNiveau : item.getNiveaux()) {
                        List<Classe> classes = classeService.getClassesByNiveauId(affectationNiveau.getNiveau().getId());
                        if(isEleveExistsInClasses(classes, recipient)) return  true;
                    }
                    return isEleveExistsInAffectationClasses(item.getClasses(), recipient);
                } else if(user instanceof Professeur) {
                    Professeur recipient = (Professeur) user;
                    for(AffectationMessageNiveau affectationNiveau : item.getNiveaux()) {
                        List<Classe> classes = classeService.getClassesByNiveauId(affectationNiveau.getNiveau().getId());
                        if(isProfhasThoseClasses(classes, recipient)) return  true;
                    }
                    return isProfhasThisAffectationClasses(item.getClasses(), recipient);
                }
                return false;
            });
        });
    }

    private boolean isEleveExistsInAffectationClasses(List<AffectationMessageClasse> affectationMessageClasses, Eleve recipient) {
        for(AffectationMessageClasse affectationClasse : affectationMessageClasses) {
            if (isEleveExistsInClasse(recipient, affectationClasse.getClasse())) return true;
        }
        return false;
    }

    private boolean isProfhasThisAffectationClasses(List<AffectationMessageClasse> affectationMessageClasses, Professeur recipient) {
        for(AffectationMessageClasse affectationClasse : affectationMessageClasses) {
            if (isProfHasThisClasse(recipient, affectationClasse.getClasse())) return true;
        }
        return false;
    }

    private boolean isEleveExistsInClasses(List<Classe> classes, Eleve recipient) {
        for(Classe classe : classes) {
            if (isEleveExistsInClasse(recipient, classe)) return true;
        }
        return false;
    }
    private boolean isProfhasThoseClasses(List<Classe> classes, Professeur recipient) {
        for(Classe classe : classes) {
            if (isProfHasThisClasse(recipient, classe)) return true;
        }
        return false;
    }

    private boolean isEleveExistsInClasse(Eleve recipient, Classe classe) {
        List<Eleve> eleves = eleveService.getElevesByClasseId(classe.getId());
        for (Eleve eleve : eleves) {
            if (eleve.getId() == recipient.getId()) {
                return true;
            }
        }
        return false;
    }
    private boolean isProfHasThisClasse(Professeur recipient, Classe classe) {
        List<Professeur> profs = professeurService.getProfsByClasseId(classe.getId());
        for (Professeur prof : profs) {
            if (prof.getId() == recipient.getId()) {
                return true;
            }
        }
        return false;
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
        //validation
        Message message =mapper.map(messageBean, Message.class);
        message.setMessageDestinationType(MessageDestinationType.ADMINISTRATION_TO_PARENT);
        sendMessage(message);
    }


    @Override
    public void sendProfMessage(MessageBean messageBean) {
        //validation
        Message message =mapper.map(messageBean, Message.class);
        message.setMessageDestinationType(MessageDestinationType.PROFESSEUR_TO_PARENT);
        sendMessage(message);
    }

    @Override
    public void sendAdminMessageToProf(MessageBean messageBean) {
        //validation
        Message message =mapper.map(messageBean, Message.class);
        message.setMessageDestinationType(MessageDestinationType.ADMINISTRATION_TO_PROF);
        sendMessage(message,Professeur.class);
    }

    private void sendMessage(Message message, Class recipientClass) {

        User currentUser = SecurityUtils.getCurrentUser();
        message.setSender(currentUser);

        List<AffectationMessageUser> users = message.getRecipients();
        message.setRecipients(null);
        save(message);
        flush();

        if(message.getSender().getAutoSendMessage() != null && message.getSender().getAutoSendMessage()) {
            message.setValidated(true);
            message.setMessageStatus(MessageStatus.VALIDE);
        }else {
            message.setMessageStatus(MessageStatus.EN_INSTANCE);
        }

        if(users != null) {
            for(AffectationMessageUser user : users) {
                user.setMessage(message);
                user.setParentRecipients(getParentRecipient(user));
            }
            message.setRecipients(users);
        }

        if(message.getUnites() != null && recipientClass.equals(Professeur.class)) {
            for (AffectationMessageUnite unite : message.getUnites()) {
                unite.setMessage(message);
                setProfsToMessageFromUnite(message, unite.getUnite());
            }
        }

        if(message.getClasses() != null) {
            for (AffectationMessageClasse classe : message.getClasses()) {
                classe.setMessage(message);
                if(recipientClass.equals(Professeur.class)) {
                    setProfsToMessageFromClasse(message, classe.getClasse());
                }else {
                    setElevesToMessageFromClasse(message, classe.getClasse());
                }
            }
        }
        if(message.getNiveaux() != null) {
            for (AffectationMessageNiveau niveau : message.getNiveaux()) {
                niveau.setMessage(message);
                List<Classe> classes = classeService.getClassesByNiveauId(niveau.getNiveau().getId());
                for(Classe classe : classes) {
                    if(recipientClass.equals(Professeur.class)) {
                        setProfsToMessageFromClasse(message, classe);
                    }else {
                        setElevesToMessageFromClasse(message, classe);
                    }
                }
            }
        }

        save(message);
    }

    private void sendMessage(Message message) {
         sendMessage(message,Eleve.class);
    }

   /* private void sendMessage2(Message message) {

        User currentUser = SecurityUtils.getCurrentUser();
        message.setSender(currentUser);

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

        save(message);
    }

*/

    private void setProfsToMessageFromClasse(Message message, Classe classe) {
        List<Professeur> professeurs = professeurService.getProfsByClasseId(classe.getId());
        setAffectationUserToMessage(message, professeurs);
    }

    private void setElevesToMessageFromClasse(Message message, Classe classe) {
        List<Eleve> eleves = eleveService.getElevesByClasseId(classe.getId());

        List<AffectationMessageUser> affecations = new ArrayList<>();
        for(Eleve eleve :eleves) {
            AffectationMessageUser affectation = new AffectationMessageUser();
            affectation.setMessage(message);
            affectation.setUser(eleve);
            affecations.add(affectation);
            affectation.setParentRecipients(getParentRecipient(affectation));
        }
        if(!affecations.isEmpty()) {
            if(message.getRecipients() == null) {
                message.setRecipients(new ArrayList<>());
            }
            message.getRecipients().addAll(affecations);
        }
    }

    private List<AffectationMessageUserParent> getParentRecipient(AffectationMessageUser affectation) {
        List<AffectationMessageUserParent> affectationsParent = new ArrayList<>();
        Eleve eleve;
        if(affectation.getUser() instanceof Eleve) {
            eleve = (Eleve) affectation.getUser() ;
        }else {
            eleve = eleveService.findOne(affectation.getUser().getId());
        }
        if(eleve == null) {
            return affectationsParent;
        }

        List<AffectationParentEleve> affectationsParentEleve = eleve.getAffectationParents();
        if(affectation != null) {
            for(AffectationParentEleve affectationParentEleve : affectationsParentEleve) {
                if(affectationParentEleve.getEnabled() == null || !affectationParentEleve.getEnabled()) {
                    continue;
                }
                if(!affectationParentEleve.getParent().isEnabled()) {
                    continue;
                }
                AffectationMessageUserParent affectationMessageParent = new AffectationMessageUserParent();
                affectationMessageParent.setParent(affectationParentEleve.getParent());
                affectationMessageParent.setAffectationMessageUser(affectation);
                affectationsParent.add(affectationMessageParent);
            }
        }
        return affectationsParent;
    }

    private void setProfsToMessageFromUnite(Message message, Unite unite) {
        List<Professeur> professeurs = professeurService.getProfsByUniteId(unite.getId());
        setAffectationUserToMessage(message, professeurs);
    }

    private void setAffectationUserToMessage(Message message, List<Professeur> professeurs) {
        List<AffectationMessageUser> affecations = new ArrayList<>();

        PROF_FOR : for(Professeur professeur :professeurs) {
            if(message.getRecipients() != null) {
                for(AffectationMessageUser affectation : message.getRecipients()) {
                    if(affectation.getUser().getId().equals(professeur.getId())) {
                        continue PROF_FOR;
                    }
                }
            }
            AffectationMessageUser affectation = new AffectationMessageUser();
            affectation.setMessage(message);
            affectation.setUser(professeur);
            affecations.add(affectation);
        }
        if(!affecations.isEmpty()) {
            if(message.getRecipients() == null) {
                message.setRecipients(new ArrayList<>());
            }
            message.getRecipients().addAll(affecations);
        }
    }

    @Override
    public void validateMessage(Long messageId) throws Exception {
        Message message = findOne(messageId);
        if(message == null) {
            throw new Exception("Message not found");
        }

        message.setValidated(true);
        message.setMessageStatus(MessageStatus.VALIDE);

    }

    @Override
    public void deleteMessage(Long messageId) throws Exception {
        User currentUser = SecurityUtils.getCurrentUser();
        if(currentUser instanceof Professeur || currentUser instanceof Eleve || currentUser instanceof Parent) {
            Message message = findOne(messageId);

            if (message == null) {
                throw new Exception("Message not found");
            }

            if (message.getSender() != null && message.getSender().getId() == currentUser.getId()) {
                delete(message);
            }else {
                throw new Exception("Forbidden");
            }
        }else { //if administrateur
            Message message = findOne(messageId);

            if (message == null) {
                throw new Exception("Message not found");
            }

            //Verification de l'ecole
            if (message.getSender() != null && message.getSender().getSchool() != null
                    && message.getSender().getSchool().getId().equals(currentUser.getSchool().getId())) {
                delete(message);
            }
        }
    }
    @Override
    public void rejectMessage(Long messageId) throws Exception {
        User currentUser = SecurityUtils.getCurrentUser();
        if(currentUser instanceof Professeur || currentUser instanceof Eleve || currentUser instanceof Parent) {
                throw new Exception("Forbidden");
        }else { //if administrateur
            Message message = findOne(messageId);
            if (message == null) {
                throw new Exception("Message not found");
            }

            //Verification de l'ecole
            if (message.getSender() != null && message.getSender().getSchool() != null
                    && message.getSender().getSchool().getId().equals(currentUser.getSchool().getId())) {
                message.setMessageStatus(MessageStatus.REJETE);
                save(message);
            }else {
                throw new Exception("Forbidden");
            }
        }
    }

    @Override
    public void enableMessage(Long messageId) throws Exception {
        User currentUser = SecurityUtils.getCurrentUser();
        if(currentUser instanceof Professeur || currentUser instanceof Eleve || currentUser instanceof Parent) {
                throw new Exception("Forbidden");
        }else { //if administrateur
            Message message = findOne(messageId);
            if (message == null) {
                throw new Exception("Message not found");
            }

            //Verification de l'ecole
            if (message.getSender() != null && message.getSender().getSchool() != null
                    && message.getSender().getSchool().getId().equals(currentUser.getSchool().getId())) {
                message.setValidated(true);
                message.setMessageStatus(MessageStatus.VALIDE);
                save(message);
            }else  {
                throw new Exception("Forbidden");
            }

        }
    }


    private List<Message> getAllMessageByUser(User user) {
        QMessage message = QMessage.message1;

        QAffectationMessageUser affectationMessageUser = QAffectationMessageUser.affectationMessageUser;

        OrderSpecifier<Date> sortOrder = QMessage.message1.createdOn.desc();
        BooleanExpression predicat = message.id.in(JPAExpressions.selectFrom(affectationMessageUser)
                .where(affectationMessageUser.user.id.eq(user.getId()))
                .select(affectationMessageUser.message.id));

        return (List<Message>) messageRepository.findAll(predicat,sortOrder);
    }

    private List<Message> getAllMessageByUser(User user,Boolean validated) {
        QMessage message = QMessage.message1;

        BooleanExpression predicat = message.sender.id.eq(user.getId());
        OrderSpecifier<Date> sortOrder = QMessage.message1.createdOn.desc();

        if(validated != null) {
                //TODO use messageStatus instead of validated
                predicat = predicat.and( validated ? message.validated.isTrue() : message.validated.isFalse().or(message.validated.isNull()));
        }

        return (List<Message>) messageRepository.findAll(predicat,sortOrder);
    }

    private List<Message> getAllMessageByUser(User user, MessageStatus messageStatus) {
        QMessage message = QMessage.message1;

        BooleanExpression predicat = message.sender.id.eq(user.getId());
        OrderSpecifier<Date> sortOrder = QMessage.message1.createdOn.desc();

        if(messageStatus != null) {
                predicat = predicat.and(message.messageStatus.eq(messageStatus));
        }

        return (List<Message>) messageRepository.findAll(predicat,sortOrder);
    }

    private List<AffectationMessageUser> getAllAffectationMessageByUser(User user) {

        QAffectationMessageUser affectationMessageUser = QAffectationMessageUser.affectationMessageUser;
        OrderSpecifier<Date> sortOrder = QAffectationMessageUser.affectationMessageUser.createdOn.desc();
        return (List<AffectationMessageUser>) affectationMessageUserRepository.findAll(affectationMessageUser.user.id.eq(user.getId()),sortOrder);
    }

    @Override
    public List<MessageBean> getAllMessageForParent() {
        return getAllMessageForParentByMessageType(null);
    }

    @Override
    public List<MessageBean> getAllMessageByMessageDestinationType(MessageDestinationType messageDestinationType) {
        QMessage message = QMessage.message1;

        OrderSpecifier<Date> sortOrder = message.createdOn.desc();

        //TODO use messageStatus instead of validated
        List<Message> messages = (List<Message>) messageRepository.findAll(message.messageDestinationType.eq(messageDestinationType).and(message.validated.isTrue()), sortOrder);
        Map<Long,Integer> totalRecipients = new HashMap<>();
        Map<Long,Integer> totalOfViews = new HashMap<>();

        for(Message entity : messages) {
            Integer count = 0;
            if(entity.getRecipients() != null) {
                for(AffectationMessageUser affectation : entity.getRecipients()) {
                    if(affectation.getSeen() != null && affectation.getSeen()) {
                        count++;
                    }
                }
            }
            totalOfViews.put(entity.getId(),count);
            totalRecipients.put(entity.getId(),entity.getRecipients() == null ? 0 : entity.getRecipients().size());
        }
        traiterLesMessages(messages);

        List<MessageBean> messageBeans = mapper.map(messages, MessageBean.LIST_BEAN_TYPE);

        messageBeans.forEach((bean) -> {
            bean.setTotalRecipients(totalRecipients.get(bean.getId()));
            bean.setTotalOfViews(totalOfViews.get(bean.getId()));
        });
        return messageBeans;
    }


    @Override
    public List<MessageBean> getAllMessageForParentByMessageType(MessageType messageType) {
        //  validateIsparent
        List<Message> messages = new ArrayList<>();

        List<MessageBean> messageBeans = new ArrayList();

        User currentUser = SecurityUtils.getCurrentUser();
        Parent parent = (Parent) currentUser;
        List<AffectationParentEleve> affectations = affectationParentEleveService.findByParent(parent);
        for(AffectationParentEleve affectation : affectations) {
            if(affectation.getEnabled() != null && affectation.getEnabled()) {
                Eleve eleve = affectation.getEleve();
                if(eleve.isEnabled()) {
                    List<AffectationMessageUser> affectationsMessage = getAllAffectationMessageByUser(eleve);
                    for(AffectationMessageUser affectationMessage : affectationsMessage) {
                        final Message message1 = affectationMessage.getMessage();
                        if(message1 == null || message1.getMessageStatus() != MessageStatus.VALIDE) {
                            continue;
                        }
                        MessageType thisMessageType = message1.getSender() instanceof Professeur ? MessageType.PROFESSEUR : MessageType.ADMINISTRATION;
                        if(messageType != null && !thisMessageType.equals(messageType)) {
                            continue;
                        }
                        MessageBean message = mapper.map(message1, MessageBean.class);
                        message.setMessageType(thisMessageType);
                        message.setClasses(null);
                        message.setRecipients(null);
                        message.setNiveaux(null);
                        UserBean recipient = new UserBean();
                        recipient.setFirstname(eleve.getFirstname());
                        recipient.setLastname(eleve.getLastname());
                        message.setRecipient(recipient);
                        if(affectationMessage.getParentRecipients() != null) {
                            for(AffectationMessageUserParent affectationMessageUserParent : affectationMessage.getParentRecipients()) {
                                if (affectationMessageUserParent.getParent() == null || affectationMessageUserParent.getParent().getId() != parent.getId()) {
                                    continue;
                                }
                                message.setSeen(affectationMessageUserParent.getSeen());
                                affectationMessageUserParent.setSeenDate(LocalDateTime.now());
                                break;
                            }
                        }

                        message.setRecipientMessageId(affectationMessage.getId());
                        messageBeans.add(message);
                    }
                }
            }
        }
        Collections.sort(messageBeans);
        return messageBeans;
    }

    @Override
    public List<MessageBean> getAllMessageForProf() {
        //  validateIsProf

        List<MessageBean> messageBeans = new ArrayList();
        Professeur prof;
        User currentUser = SecurityUtils.getCurrentUser();
        if(currentUser instanceof Professeur ) {
            prof = (Professeur) currentUser;

        }else {
            prof = professeurService.findOne((currentUser.getId()));
        }

        if(prof == null) {
            return messageBeans;
        }

        if(prof.isEnabled()) {
            List<AffectationMessageUser> affectationsMessage = getAllAffectationMessageByUser(prof);
            for(AffectationMessageUser affectationMessage : affectationsMessage) {

                MessageBean message = mapper.map(affectationMessage.getMessage(), MessageBean.class);
                message.setMessageType(MessageType.ADMINISTRATION);
                message.setClasses(null);
                message.setRecipients(null);
                message.setNiveaux(null);
                UserBean recipient = new UserBean();
                recipient.setFirstname(prof.getFirstname());
                recipient.setLastname(prof.getLastname());
                message.setRecipient(recipient);
                message.setSeen(affectationMessage.getSeen());
                message.setRecipientMessageId(affectationMessage.getId());
                messageBeans.add(message);
            }
        }

        Collections.sort(messageBeans);
        return messageBeans;
    }

    @Override
    public void setSeen(Long idAffectation) {
        AffectationMessageUser affectation =affectationMessageUserRepository.findOne(idAffectation);
        if(affectation != null) {
            if(affectation.getSeen() == null || !affectation.getSeen() ) {
                affectation.setSeen(true);
                affectation.setSeenDate(LocalDateTime.now());
            }

            if(affectation.getParentRecipients() != null) {
                User currentParent = SecurityUtils.getCurrentUser();
                for(AffectationMessageUserParent affectationMessageUserParent : affectation.getParentRecipients()) {
                    if (affectationMessageUserParent.getParent() == null || affectationMessageUserParent.getParent().getId() != currentParent.getId()) {
                        continue;
                    }
                    if(affectationMessageUserParent.getSeen() == null || !affectationMessageUserParent.getSeen() ) {
                        affectationMessageUserParent.setSeen(true);
                        affectationMessageUserParent.setSeenDate(LocalDateTime.now());
                        break;
                    }
                }
            }
            affectationMessageUserRepository.save(affectation);
        }
    }
    @Override
    public List<ViewBean>  getViewsDetails(Long idMessage) {
        List<ViewBean> views = new ArrayList<>();
        Message message = findOne(idMessage);

        if(message.getRecipients() != null) {

            for(AffectationMessageUser affectation : message.getRecipients()) {
                Boolean seen = affectation.getSeen() != null && affectation.getSeen();

                    ViewBean view = new ViewBean();
                if(seen) {
                    view.setUserSeen(mapper.map(affectation.getUser(), UserBean.class));
                    view.setDateSeen(affectation.getSeenDate());
                }else {
                    view.setUserNotSeen(mapper.map(affectation.getUser(), UserBean.class));
                }
                if(message.getMessageDestinationType() != null &&
                        (message.getMessageDestinationType().equals(MessageDestinationType.ADMINISTRATION_TO_PARENT) ||
                         message.getMessageDestinationType().equals(MessageDestinationType.PROFESSEUR_TO_PARENT))) {
                    for(AffectationMessageUserParent affectationParent : affectation.getParentRecipients()) {
                        view.addParents(seen,mapper.map(affectationParent.getParent(), ParentBean.class),affectationParent.getSeen(), affectationParent.getSeenDate());
                    }

                }
                views.add(view);

            }
        }
        return views;
    }


}
