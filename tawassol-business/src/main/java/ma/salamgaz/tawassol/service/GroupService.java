package ma.salamgaz.tawassol.service;

import java.util.List;

import ma.salamgaz.tawassol.common.model.bean.GroupBean;
import ma.salamgaz.tawassol.common.model.entity.Group;

/**
 * 
 * @author m.wannas
 *
 */

public interface GroupService extends GenericService<Group, Long> {

	List<GroupBean> getAll();
}
