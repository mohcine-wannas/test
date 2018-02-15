package ma.salamgaz.tawassol.service;

import java.util.List;

import ma.salamgaz.tawassol.common.model.bean.CiterneBean;
import ma.salamgaz.tawassol.common.model.entity.Citerne;

/**
 * 
 * @author m.wannas
 *
 */

public interface CiterneService extends GenericService<Citerne, Long>, RefService<CiterneBean> {

	List<CiterneBean> getAll();
}