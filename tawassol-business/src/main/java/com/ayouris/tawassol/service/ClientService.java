package com.ayouris.tawassol.service;

import java.util.List;

import com.ayouris.tawassol.common.model.bean.ClientBean;
import com.ayouris.tawassol.common.model.bean.ConcessionnaireBean;
import com.ayouris.tawassol.common.model.entity.Client;

/**
 * 
 * @author m.wannas
 *
 */

public interface ClientService extends GenericService<Client, Long>, RefService<ClientBean> {

	List<ClientBean> getAll();

	List<ConcessionnaireBean> getAllConcessionnairesByClientId(Long id);

}
