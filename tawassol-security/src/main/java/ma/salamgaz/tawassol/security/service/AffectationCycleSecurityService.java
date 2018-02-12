package ma.salamgaz.tawassol.security.service;

import java.util.List;

import ma.salamgaz.tawassol.common.model.bean.CycleBean;
import ma.salamgaz.tawassol.common.model.entity.AffectationCycle;
import ma.salamgaz.tawassol.common.service.BaseService;

/**
 * 
 * @author m.wannas
 *
 */

public interface AffectationCycleSecurityService extends BaseService<AffectationCycle> {


    List<CycleBean> getCurrentCycles();


}
