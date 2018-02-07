package ma.salamgaz.tawassol.service;

import java.util.List;

import ma.salamgaz.tawassol.common.model.bean.FournisseurBean;
import ma.salamgaz.tawassol.common.model.entity.Fournisseur;

/**
 * 
 * @author m.wannas
 *
 */

public interface FournisseurService extends GenericService<Fournisseur, Long>, RefService<FournisseurBean> {

	List<FournisseurBean> getAll();
}
