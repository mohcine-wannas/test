package ma.salamgaz.gwic.service;

import java.util.List;

import ma.salamgaz.gwic.common.model.bean.ControleCamionBean;
import ma.salamgaz.gwic.common.model.entity.ControleCamion;

/**
 * 
 * @author m.wannas
 *
 */

public interface ControleCamionService extends GenericService<ControleCamion, Long>, RefService<ControleCamionBean> {

	List<ControleCamionBean> getAll();
}
