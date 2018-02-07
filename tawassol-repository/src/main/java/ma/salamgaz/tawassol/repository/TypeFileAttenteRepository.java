package ma.salamgaz.tawassol.repository;

import ma.salamgaz.tawassol.common.model.bean.TypeFileAttenteBean;
import ma.salamgaz.tawassol.common.model.entity.TypeFileAttente;
import ma.salamgaz.tawassol.common.model.enums.CategorieTypeFileAttente;
import ma.salamgaz.tawassol.common.repository.CommonRepository;
import ma.salamgaz.tawassol.repository.custom.TypeFileAttenteRepositoryCustom;
import ma.salamgaz.tawassol.repository.custom.GridRepositoryCustom;

public interface TypeFileAttenteRepository extends  CommonRepository<TypeFileAttente>,TypeFileAttenteRepositoryCustom, GridRepositoryCustom<TypeFileAttenteBean> {


	TypeFileAttente findOneByCategorieAndIdNotAndTypePardefautTrue(CategorieTypeFileAttente categorie, Long id);

	TypeFileAttente findOneByCategorieAndTypePardefautTrue(CategorieTypeFileAttente categorie);

}
