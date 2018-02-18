package com.ayouris.tawassol.service;

import java.util.List;

import com.ayouris.tawassol.common.model.bean.NiveauBean;
import com.ayouris.tawassol.common.model.entity.Niveau;

/**
 * 
 * @author m.wannas
 *
 */

public interface NiveauService extends GenericService<Niveau,Long> {


	List<NiveauBean> getAll();


}
