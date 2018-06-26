package com.ayouris.tawassol.service;

import java.util.List;

import com.ayouris.tawassol.common.model.bean.ParentBean;
import com.ayouris.tawassol.common.model.entity.Parent;

/**
 * 
 * @author m.wannas
 *
 */

public interface ParentService extends GenericService<Parent, Long> {

	List<ParentBean> getAllByEleveId(Long eleveId);

	Parent createOrUpdate(ParentBean parent);

	boolean isValidated(Long parenId) throws Exception;

}
