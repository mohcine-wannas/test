package ma.salamgaz.gwic.service;

import java.util.List;

import ma.salamgaz.gwic.common.model.bean.PrestationBean;
import ma.salamgaz.gwic.common.model.entity.Prestation;

/**
 * 
 * @author m.wannas
 *
 */

public interface PrestationService extends GenericService<Prestation, Long>, RefService<PrestationBean> {

	List<PrestationBean> getAll();
}
