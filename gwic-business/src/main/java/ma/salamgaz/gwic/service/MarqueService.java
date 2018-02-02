package ma.salamgaz.gwic.service;

import java.util.List;

import ma.salamgaz.gwic.common.model.bean.MarqueBean;
import ma.salamgaz.gwic.common.model.entity.Marque;

/**
 * 
 * @author m.wannas
 *
 */

public interface MarqueService extends GenericService<Marque, Long>, RefService<MarqueBean> {

	List<MarqueBean> getAll();
}
