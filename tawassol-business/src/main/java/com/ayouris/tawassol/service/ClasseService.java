package com.ayouris.tawassol.service;

import java.util.List;

import com.ayouris.tawassol.common.model.bean.ClasseBean;
import com.ayouris.tawassol.common.model.bean.EleveBean;
import com.ayouris.tawassol.common.model.entity.Classe;
import com.ayouris.tawassol.common.service.BaseService;

/**
 * 
 * @author m.wannas
 *
 */

public interface ClasseService extends GenericService<Classe,Long> {


	List<ClasseBean> getAll();

	List<EleveBean> getAllByNiveauId(Long id);

}
