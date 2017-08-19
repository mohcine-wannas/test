package com.ayouris.tawassol.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.ayouris.tawassol.common.model.bean.SignataireBean;
import com.ayouris.tawassol.common.model.entity.Signataire;

/**
 * 
 * @author m.wannas
 *
 */

public interface SignataireService extends GenericService<Signataire, Long>, RefService<SignataireBean> {

	List<SignataireBean> getAll();

	void saveSignature(Long id, InputStream in) throws IOException;

	File getSignature(Long id);

	List<SignataireBean> findByConcessionnaireId(Long id);
}
