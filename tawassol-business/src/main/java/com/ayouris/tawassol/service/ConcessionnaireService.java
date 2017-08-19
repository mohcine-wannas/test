package com.ayouris.tawassol.service;

import java.util.List;

import com.ayouris.tawassol.common.model.bean.CamionBean;
import com.ayouris.tawassol.common.model.bean.ChauffeurBean;
import com.ayouris.tawassol.common.model.bean.ConcessionnaireBean;
import com.ayouris.tawassol.common.model.bean.ProduitBean;
import com.ayouris.tawassol.common.model.bean.SignataireBean;
import com.ayouris.tawassol.common.model.entity.Concessionnaire;

/**
 * 
 * @author m.wannas
 *
 */

public interface ConcessionnaireService extends GenericService<Concessionnaire, Long>, RefService<ConcessionnaireBean> {

	List<ConcessionnaireBean> getAll();

	List<ChauffeurBean> getAllChauffeursByConcessionnaireId(Long id);

	List<CamionBean> getAllCamionsByConcessionnaireId(Long id);

	List<ConcessionnaireBean> findByClientId(Long id);

	List<ProduitBean> getAllProduitByConcessionnaireId(Long id);

	void remove(Long id);

	List<SignataireBean> getAllSignatairesByConcessionnaireId(Long id);
}
