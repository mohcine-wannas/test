package ma.salamgaz.tawassol.service;

import java.util.List;

import ma.salamgaz.tawassol.common.model.bean.ControleCamionBean;
import ma.salamgaz.tawassol.common.model.entity.ControleCamion;

/**
 * 
 * @author m.wannas
 *
 */

public interface ControleCamionService extends GenericService<ControleCamion, Long>, RefService<ControleCamionBean> {

	List<ControleCamionBean> getAll();
}
