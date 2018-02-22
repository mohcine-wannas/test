package com.ayouris.tawassol.service;

import java.util.List;

import com.ayouris.tawassol.common.model.bean.TypeFileAttenteBean;
import com.ayouris.tawassol.common.model.entity.TypeFileAttente;

/**
 * 
 * @author m.wannas
 *
 */

public interface TypeFileAttenteService extends GenericService<TypeFileAttente, Long>, RefService<TypeFileAttenteBean> {

	List<TypeFileAttenteBean> getAll();
}
