package com.ayouris.tawassol.service;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.List;

import com.ayouris.tawassol.common.model.bean.EleveBean;
import com.ayouris.tawassol.common.model.bean.ParentBean;
import com.ayouris.tawassol.common.model.entity.Eleve;
import com.ayouris.tawassol.common.model.enums.ParentingRelationship;

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

    int importFromMassarFileUpload(Long idClasse, InputStream in) throws Exception;
}
