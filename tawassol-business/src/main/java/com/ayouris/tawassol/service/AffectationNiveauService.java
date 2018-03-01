package com.ayouris.tawassol.service;

import java.util.List;

import com.ayouris.tawassol.common.model.bean.AffectationNiveauBean;
import com.ayouris.tawassol.common.model.entity.AffectationNiveau;

/**
 * 
 * @author m.wannas
 *
 */

public interface AffectationNiveauService extends  GenericService<AffectationNiveau,Long> {



	List<AffectationNiveauBean> getAll();


}
