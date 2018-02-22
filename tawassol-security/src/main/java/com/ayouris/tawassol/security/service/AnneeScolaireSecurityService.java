package com.ayouris.tawassol.security.service;

import java.util.List;

import com.ayouris.tawassol.common.model.bean.AnneeScolaireBean;
import com.ayouris.tawassol.common.model.entity.AnneeScolaire;
import com.ayouris.tawassol.common.service.BaseService;

/**
 * 
 * @author m.wannas
 *
 */

public interface AnneeScolaireSecurityService extends BaseService<AnneeScolaire> {


    AnneeScolaire getCurrentAnneeScolaire();

	List<AnneeScolaireBean> getAll();


}
