package com.ayouris.tawassol.service;

import java.util.List;

import com.ayouris.tawassol.common.model.bean.GroupBean;
import com.ayouris.tawassol.common.model.entity.Group;

/**
 * 
 * @author m.wannas
 *
 */

public interface GroupService extends GenericService<Group, Long> {

	List<GroupBean> getAll();
}
