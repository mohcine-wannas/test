package ma.salamgaz.gwic.service;

import java.util.List;

import ma.salamgaz.gwic.common.model.bean.CiterneBean;
import ma.salamgaz.gwic.common.model.entity.Citerne;

/**
 * 
 * @author m.wannas
 *
 */

public interface CiterneService extends GenericService<Citerne, Long>, RefService<CiterneBean> {

	List<CiterneBean> getAll();
}
