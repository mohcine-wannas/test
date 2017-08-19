package com.ayouris.tawassol.service;

import java.util.List;
import java.util.Map;

import com.ayouris.tawassol.common.model.bean.EmplissageBean;
import com.ayouris.tawassol.common.model.bean.EmplissageControlColonneBean;
import com.ayouris.tawassol.common.model.bean.EmplissageControlLigneBean;
import com.ayouris.tawassol.common.model.entity.EmplissageControlColonne;
import com.ayouris.tawassol.common.model.entity.EmplissageControlLigne;
import com.ayouris.tawassol.common.model.entity.EmplissageResultControl;

public interface EmplissageResultControlService extends GenericService<EmplissageResultControl, Long> {

	Map<Long, Long> getAllColumnControlByIdLine(Long idEmplissage, Long idLigneControle);

	List<EmplissageResultControl> getAllLineByIdEmplissage(Long id);

	List<EmplissageControlLigne> getAllControlLigne();

	List<EmplissageControlColonne> getAllControlColonne();

	void create(Long id);

	EmplissageResultControl getEmplissageResultControl(Long idEmplissage, Long idLigneControle, Long idColonneControle);

	List<EmplissageControlLigneBean> getAllControlLigneBean();

	List<EmplissageControlColonneBean> getAllControlColonneBean();

	void update(EmplissageBean emplissageBean);
}

