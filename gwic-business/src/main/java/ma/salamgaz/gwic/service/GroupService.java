package ma.salamgaz.gwic.service;

import java.util.List;

import ma.salamgaz.gwic.common.model.bean.GroupBean;
import ma.salamgaz.gwic.common.model.entity.Group;

/**
 * 
 * @author m.wannas
 *
 */

public interface GroupService extends GenericService<Group, Long> {

	List<GroupBean> getAll();
}
