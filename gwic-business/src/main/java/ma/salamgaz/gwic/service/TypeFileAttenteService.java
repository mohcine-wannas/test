package ma.salamgaz.gwic.service;

import java.util.List;

import ma.salamgaz.gwic.common.model.bean.TypeFileAttenteBean;
import ma.salamgaz.gwic.common.model.entity.TypeFileAttente;

/**
 * 
 * @author m.wannas
 *
 */

public interface TypeFileAttenteService extends GenericService<TypeFileAttente, Long>, RefService<TypeFileAttenteBean> {

	List<TypeFileAttenteBean> getAll();
}
