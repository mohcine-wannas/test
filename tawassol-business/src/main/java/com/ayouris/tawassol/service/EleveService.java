package com.ayouris.tawassol.service;

import com.ayouris.tawassol.common.model.bean.EleveBean;
import com.ayouris.tawassol.common.model.bean.ParentBean;
import com.ayouris.tawassol.common.model.entity.Eleve;
import com.ayouris.tawassol.common.model.enums.ParentingRelationship;

import java.io.InputStream;
import java.util.List;

/**
 * 
 * @author m.wannas
 *
 */

public interface EleveService extends GenericService<Eleve, Long> {

	List<EleveBean> getAllByClasseId(Long classeId);

	List<EleveBean> getAllByParentId(Long parentId);

	Long setParent(String codeMassar, ParentBean parent, ParentingRelationship parentingRelationship);

	void enableParent(Long id, Boolean enable);

	Boolean verifierCodeMassar(String codeMassar);

    Long create(EleveBean eleveBean);

	Long update(Long id, EleveBean eleveBean);

	EleveBean getEleve(Long id);

	void delete(Long id);

	void enableEleve(Long id, Boolean enable);

	void enableAllEleve(Boolean enable);

	int importFromMassarFileUpload(Long idClasse, InputStream in) throws Exception;
}
