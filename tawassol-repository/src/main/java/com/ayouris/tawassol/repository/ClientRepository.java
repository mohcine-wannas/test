package com.ayouris.tawassol.repository;

import com.ayouris.tawassol.common.model.bean.ClientBean;
import com.ayouris.tawassol.common.model.entity.Client;
import com.ayouris.tawassol.common.repository.CommonRepository;
import com.ayouris.tawassol.repository.custom.GridRepositoryCustom;

/**
 * 
 * @author m.wannas
 *
 */
public interface ClientRepository extends CommonRepository<Client>, GridRepositoryCustom<ClientBean>{
}
