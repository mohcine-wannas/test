package com.ayouris.tawassol.service.impl;

import com.ayouris.tawassol.admin.model.entity.User;
import com.ayouris.tawassol.common.exception.ErrorMessageType;
import com.ayouris.tawassol.common.mapper.CustomModelMapper;
import com.ayouris.tawassol.common.model.bean.MessageModelAdminBean;
import com.ayouris.tawassol.common.model.bean.MessageModelBean;
import com.ayouris.tawassol.common.model.bean.MessageModelProfBean;
import com.ayouris.tawassol.common.model.entity.*;
import com.ayouris.tawassol.repository.CategorieAdminRepository;
import com.ayouris.tawassol.repository.CategorieProfRepository;
import com.ayouris.tawassol.repository.MessageModelRepository;
import com.ayouris.tawassol.security.utils.SecurityUtils;
import com.ayouris.tawassol.service.MessageModelService;
import com.ayouris.tawassol.service.ServiceException;
import com.querydsl.core.types.OrderSpecifier;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class MessageModelServiceImpl implements MessageModelService {

    @Autowired
    private MessageModelRepository messageModelRepository;

    @Autowired
    private CategorieProfRepository categorieProfRepository;

    @Autowired
    private CategorieAdminRepository categorieAdminRepository;

    @Autowired
    private CustomModelMapper mapper;

    @Override
    public List<MessageModelAdminBean> findAllByCategorieAdminId(Long id) {

        QMessageModel qMessageModel = QMessageModel.messageModel;
        OrderSpecifier<Date> sortOrderDate = qMessageModel.createdOn.desc();
        OrderSpecifier<Boolean> sortOrderFige = qMessageModel.fige.desc();

        List<MessageModel> models = (List<MessageModel>) messageModelRepository.findAll(qMessageModel.categorieAdmin.id.eq(id).
                and(qMessageModel.fige.isTrue().or(qMessageModel.fige.isFalse()
                        .and(qMessageModel.school.id.eq(SecurityUtils.getCurrentSchool().getId())))), sortOrderFige, sortOrderDate);

        return mapper.map(models, MessageModelAdminBean.LIST_BEAN_TYPE);
    }

    @Override
    public List<MessageModelProfBean> findAllByCategorieProfId(Long id) {

        QMessageModel qMessageModel = QMessageModel.messageModel;
        OrderSpecifier<Date> sortOrderDate = qMessageModel.createdOn.desc();
        OrderSpecifier<Boolean> sortOrderFige = qMessageModel.fige.desc();

        List<MessageModel> models = (List<MessageModel>) messageModelRepository.findAll(qMessageModel.categorieProf.id.eq(id).
                and((qMessageModel.fige.isTrue().and(qMessageModel.school.id.eq(SecurityUtils.getCurrentSchool().getId()))).or(qMessageModel.fige.isFalse()
                        .and(qMessageModel.user.id.eq(SecurityUtils.getCurrentUser().getId())))), sortOrderFige, sortOrderDate);

        return mapper.map(models, MessageModelProfBean.LIST_BEAN_TYPE);
    }

    @Override
    public void deleteMessageModel(Long messageModelId) throws Exception {
        MessageModel messageModel = messageModelRepository.findOne(messageModelId);

        if (messageModel == null) {
            throw new Exception("Model Message not found");
        } else if (messageModel.getFige()) {
            throw new Exception("Model Message cannot be deleted");
        }

        User currentUser = SecurityUtils.getCurrentUser();

        if (currentUser instanceof Professeur) {

            if (!messageModel.getFige() && messageModel.getUser() != null
                    && messageModel.getUser().getId().equals(currentUser.getId())) {
                messageModelRepository.delete(messageModel);
            } else {
                throw new Exception("Forbidden");
            }
        } else {
            if (!messageModel.getFige() && messageModel.getSchool() != null
                    && messageModel.getSchool().getId().equals(currentUser.getSchool().getId())) {
                messageModelRepository.delete(messageModel);
            } else {
                throw new Exception("Forbidden");
            }
        }
    }

    @Override
    public Long create(MessageModelBean messageModelBean) throws Exception {
        validationRequiredValue(messageModelBean);

        User currentUser = SecurityUtils.getCurrentUser();
        MessageModel entity = new MessageModel();

        if (currentUser instanceof Professeur) {
            entity.setFige(false);
            entity.setUser(currentUser);
            return mappingToMessageModelProfEntityAndSave(messageModelBean, entity);

        } else {
            entity.setFige(false);
            entity.setSchool(currentUser.getSchool());
            return mappingToMessageModelAdminEntityAndSave(messageModelBean, entity);
        }
    }

    @Override
    public Long update(Long id, MessageModelBean messageModelBean) throws Exception {
        MessageModel entity = messageModelRepository.findOne(id);

        if (entity == null) {
            throw new Exception("Model Message not found");
        } else if (entity.getFige()) {
            throw new Exception("Model Message cannot be deleted");
        }

        validationRequiredValue(messageModelBean);

        User currentUser = SecurityUtils.getCurrentUser();

        if (currentUser instanceof Professeur) {
            if (!entity.getFige() && entity.getUser() != null
                    && entity.getUser().getId().equals(currentUser.getId())) {
                return mappingToMessageModelProfEntityAndSave(messageModelBean, entity);
            } else {
                throw new Exception("Forbidden");
            }
        } else {
            if (!entity.getFige() && entity.getSchool() != null
                    && entity.getSchool().getId().equals(currentUser.getSchool().getId())) {
                return mappingToMessageModelAdminEntityAndSave(messageModelBean, entity);
            } else {
                throw new Exception("Forbidden");
            }
        }
    }

    private Long mappingToMessageModelProfEntityAndSave(MessageModelBean messageModelBean, MessageModel entity) throws Exception {
        entity.setMessage(messageModelBean.getMessage());
        entity.setTitre(messageModelBean.getTitre());
        CategorieProf categorieProf = categorieProfRepository.findOne(messageModelBean.getCategorie().getId());
        if (categorieProf == null) {
            throw new Exception("Category not found");
        }
        entity.setCategorieProf(categorieProf);
        return messageModelRepository.save(entity).getId();
    }

    private Long mappingToMessageModelAdminEntityAndSave(MessageModelBean messageModelBean, MessageModel entity) throws Exception {
        entity.setMessage(messageModelBean.getMessage());
        entity.setTitre(messageModelBean.getTitre());
        CategorieAdmin categorieAdmin = categorieAdminRepository.findOne(messageModelBean.getCategorie().getId());
        if (categorieAdmin == null) {
            throw new Exception("Category not found");
        }
        entity.setCategorieAdmin(categorieAdmin);
        return messageModelRepository.save(entity).getId();
    }

    private void validationRequiredValue(MessageModelBean messageModelBean) {
        if (StringUtils.isEmpty(messageModelBean.getMessage())
                || StringUtils.isEmpty(messageModelBean.getTitre())
                || messageModelBean.getCategorie() == null
                || messageModelBean.getCategorie().getId() == null) {
            throw new ServiceException(ErrorMessageType.MISSING_REQUIRED_FIELDS);
        }
    }
}
