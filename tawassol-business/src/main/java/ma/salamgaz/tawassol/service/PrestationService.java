package ma.salamgaz.tawassol.service;

import java.util.List;

import ma.salamgaz.tawassol.common.model.bean.PrestationBean;
import ma.salamgaz.tawassol.common.model.entity.Prestation;

/**
 * 
 * @author m.wannas
 *
 */

public interface PrestationService extends GenericService<Prestation, Long>, RefService<PrestationBean> {

	List<PrestationBean> getAll();
}
