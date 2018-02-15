package ma.salamgaz.tawassol.service;

import java.util.List;

import ma.salamgaz.tawassol.common.model.bean.TransporteurBean;
import ma.salamgaz.tawassol.common.model.entity.Transporteur;

/**
 * 
 * @author m.wannas
 *
 */

public interface TransporteurService extends GenericService<Transporteur, Long>, RefService<TransporteurBean> {

	List<TransporteurBean> getAll();
}
