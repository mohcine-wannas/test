package ma.salamgaz.tawassol.security.service;

import java.util.List;

import ma.salamgaz.tawassol.common.model.bean.CycleBean;
import ma.salamgaz.tawassol.common.model.entity.Cycle;
import ma.salamgaz.tawassol.common.service.BaseService;

/**
 * 
 * @author m.wannas
 *
 */

public interface CycleSecurityService extends BaseService<Cycle> {

	List<CycleBean> getAll();

}
