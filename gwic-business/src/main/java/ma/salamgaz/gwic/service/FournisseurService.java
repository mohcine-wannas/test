package ma.salamgaz.gwic.service;

import java.util.List;

import ma.salamgaz.gwic.common.model.bean.FournisseurBean;
import ma.salamgaz.gwic.common.model.entity.Fournisseur;

/**
 * 
 * @author m.wannas
 *
 */

public interface FournisseurService extends GenericService<Fournisseur, Long>, RefService<FournisseurBean> {

	List<FournisseurBean> getAll();
}
