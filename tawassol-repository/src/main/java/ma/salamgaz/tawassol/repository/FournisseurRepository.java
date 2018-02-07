package ma.salamgaz.tawassol.repository;

import ma.salamgaz.tawassol.common.model.bean.FournisseurBean;
import ma.salamgaz.tawassol.common.model.entity.Fournisseur;
import ma.salamgaz.tawassol.common.repository.CommonRepository;
import ma.salamgaz.tawassol.repository.custom.FournisseurRepositoryCustom;
import ma.salamgaz.tawassol.repository.custom.GridRepositoryCustom;

public interface FournisseurRepository extends  CommonRepository<Fournisseur>,FournisseurRepositoryCustom, GridRepositoryCustom<FournisseurBean> {

	
}
