package com.ayouris.tawassol.service;

import com.ayouris.tawassol.common.model.bean.MessageModelAdminBean;
import com.ayouris.tawassol.common.model.bean.MessageModelBean;
import com.ayouris.tawassol.common.model.bean.MessageModelProfBean;

import java.util.List;

public interface MessageModelService {

    List<MessageModelAdminBean> findAllByCategorieAdminId(Long id);

    List<MessageModelProfBean> findAllByCategorieProfId(Long id);

    void deleteMessageModel(Long messageModelId) throws Exception;

    Long create(MessageModelBean messageModelBean) throws Exception;

    Long update(Long id, MessageModelBean messageModelBean) throws Exception;
}
