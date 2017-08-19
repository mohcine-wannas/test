package com.ayouris.tawassol.service;

import java.util.List;

import com.ayouris.tawassol.common.model.bean.TransporteurBean;
import com.ayouris.tawassol.common.model.entity.Transporteur;

/**
 * 
 * @author m.wannas
 *
 */

public interface TransporteurService extends GenericService<Transporteur, Long>, RefService<TransporteurBean> {

	List<TransporteurBean> getAll();
}
