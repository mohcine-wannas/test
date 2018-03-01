package com.ayouris.tawassol.service;

import java.util.List;

import com.ayouris.tawassol.common.model.bean.EleveBean;
import com.ayouris.tawassol.common.model.bean.ParentBean;
import com.ayouris.tawassol.common.model.entity.Eleve;

/**
 * 
 * @author m.wannas
 *
 */

public interface EleveService extends GenericService<Eleve, Long> {

	List<EleveBean> getAllByClasseId(Long classeId);

	List<EleveBean> getAllByParentId(Long parentId);



	Long setParent(String codeMassar, ParentBean parent);


	void enableParent(Long id, Boolean enable);

	Boolean verifierCodeMassar(String codeMassar);

}
