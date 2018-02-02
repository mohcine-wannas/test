package ma.salamgaz.gwic.repository;

import ma.salamgaz.gwic.common.model.bean.FournisseurBean;
import ma.salamgaz.gwic.common.model.entity.Fournisseur;
import ma.salamgaz.gwic.common.repository.CommonRepository;
import ma.salamgaz.gwic.repository.custom.FournisseurRepositoryCustom;
import ma.salamgaz.gwic.repository.custom.GridRepositoryCustom;

public interface FournisseurRepository extends  CommonRepository<Fournisseur>,FournisseurRepositoryCustom, GridRepositoryCustom<FournisseurBean> {

	
}
