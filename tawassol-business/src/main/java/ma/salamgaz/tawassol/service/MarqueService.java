package ma.salamgaz.tawassol.service;

import java.util.List;

import ma.salamgaz.tawassol.common.model.bean.MarqueBean;
import ma.salamgaz.tawassol.common.model.entity.Marque;

/**
 * 
 * @author m.wannas
 *
 */

public interface MarqueService extends GenericService<Marque, Long>, RefService<MarqueBean> {

	List<MarqueBean> getAll();
}
