package ma.salamgaz.tawassol.security.service;

import java.util.List;

import ma.salamgaz.tawassol.common.model.bean.AnneeScolaireBean;
import ma.salamgaz.tawassol.common.model.entity.AnneeScolaire;
import ma.salamgaz.tawassol.common.service.BaseService;

/**
 * 
 * @author m.wannas
 *
 */

public interface AnneeScolaireSecurityService extends BaseService<AnneeScolaire> {


    AnneeScolaire getCurrentAnneeScolaire();

	List<AnneeScolaireBean> getAll();


}
