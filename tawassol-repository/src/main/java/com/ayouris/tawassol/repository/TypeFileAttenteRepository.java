package com.ayouris.tawassol.repository;

import com.ayouris.tawassol.common.model.bean.TypeFileAttenteBean;
import com.ayouris.tawassol.common.model.entity.TypeFileAttente;
import com.ayouris.tawassol.common.model.enums.CategorieTypeFileAttente;
import com.ayouris.tawassol.common.repository.CommonRepository;
import com.ayouris.tawassol.repository.custom.TypeFileAttenteRepositoryCustom;
import com.ayouris.tawassol.repository.custom.GridRepositoryCustom;

public interface TypeFileAttenteRepository extends  CommonRepository<TypeFileAttente>,TypeFileAttenteRepositoryCustom, GridRepositoryCustom<TypeFileAttenteBean> {


	TypeFileAttente findOneByCategorieAndIdNotAndTypePardefautTrue(CategorieTypeFileAttente categorie, Long id);

	TypeFileAttente findOneByCategorieAndTypePardefautTrue(CategorieTypeFileAttente categorie);

}
