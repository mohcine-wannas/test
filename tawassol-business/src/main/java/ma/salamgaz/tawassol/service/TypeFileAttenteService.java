package ma.salamgaz.tawassol.service;

import java.util.List;

import ma.salamgaz.tawassol.common.model.bean.TypeFileAttenteBean;
import ma.salamgaz.tawassol.common.model.entity.TypeFileAttente;

/**
 * 
 * @author m.wannas
 *
 */

public interface TypeFileAttenteService extends GenericService<TypeFileAttente, Long>, RefService<TypeFileAttenteBean> {

	List<TypeFileAttenteBean> getAll();
}
