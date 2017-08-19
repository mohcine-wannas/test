package com.ayouris.tawassol.repository;

import com.ayouris.tawassol.admin.model.beans.SiteBean;
import com.ayouris.tawassol.common.model.entity.Site;
import com.ayouris.tawassol.common.repository.CommonRepository;
import com.ayouris.tawassol.repository.custom.GridRepositoryCustom;
import com.ayouris.tawassol.repository.custom.SiteRepositoryCustom;

public interface SiteRepository extends  CommonRepository<Site>,SiteRepositoryCustom,GridRepositoryCustom<SiteBean>  {

	
}