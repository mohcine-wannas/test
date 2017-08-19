package com.ayouris.tawassol.service;

import java.util.List;

import com.ayouris.tawassol.common.model.bean.FournisseurBean;
import com.ayouris.tawassol.common.model.entity.Fournisseur;

/**
 * 
 * @author m.wannas
 *
 */

public interface FournisseurService extends GenericService<Fournisseur, Long>, RefService<FournisseurBean> {

	List<FournisseurBean> getAll();
}
