package ma.salamgaz.tawassol.service;

import java.util.List;

import ma.salamgaz.tawassol.common.model.bean.FabriquantBean;
import ma.salamgaz.tawassol.common.model.entity.Fabriquant;

/**
 * 
 * @author m.wannas
 *
 */

public interface FabriquantService extends GenericService<Fabriquant, Long>, RefService<FabriquantBean> {

	List<FabriquantBean> getAll();
}
