package ma.salamgaz.gwic.service;

import java.util.List;

import ma.salamgaz.gwic.common.model.bean.TransporteurBean;
import ma.salamgaz.gwic.common.model.entity.Transporteur;

/**
 * 
 * @author m.wannas
 *
 */

public interface TransporteurService extends GenericService<Transporteur, Long>, RefService<TransporteurBean> {

	List<TransporteurBean> getAll();
}
