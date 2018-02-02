package ma.salamgaz.gwic.repository;

import ma.salamgaz.gwic.common.model.bean.TypeFileAttenteBean;
import ma.salamgaz.gwic.common.model.entity.TypeFileAttente;
import ma.salamgaz.gwic.common.model.enums.CategorieTypeFileAttente;
import ma.salamgaz.gwic.common.repository.CommonRepository;
import ma.salamgaz.gwic.repository.custom.TypeFileAttenteRepositoryCustom;
import ma.salamgaz.gwic.repository.custom.GridRepositoryCustom;

public interface TypeFileAttenteRepository extends  CommonRepository<TypeFileAttente>,TypeFileAttenteRepositoryCustom, GridRepositoryCustom<TypeFileAttenteBean> {


	TypeFileAttente findOneByCategorieAndIdNotAndTypePardefautTrue(CategorieTypeFileAttente categorie, Long id);

	TypeFileAttente findOneByCategorieAndTypePardefautTrue(CategorieTypeFileAttente categorie);

}
