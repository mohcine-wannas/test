package ma.salamgaz.gwic.service;

import java.util.List;

import ma.salamgaz.gwic.common.model.bean.FabriquantBean;
import ma.salamgaz.gwic.common.model.entity.Fabriquant;

/**
 * 
 * @author m.wannas
 *
 */

public interface FabriquantService extends GenericService<Fabriquant, Long>, RefService<FabriquantBean> {

	List<FabriquantBean> getAll();
}
