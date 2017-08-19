package com.ayouris.tawassol.service.impl;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.jdo.annotations.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ayouris.tawassol.common.model.entity.DefinitionPeriodes;
import com.ayouris.tawassol.common.model.entity.Periode;
import com.ayouris.tawassol.common.model.entity.PieceReglement;
import com.ayouris.tawassol.common.model.entity.QDefinitionPeriodes;
import com.ayouris.tawassol.common.model.enums.Periodicite;
import com.ayouris.tawassol.repository.DefinitionPeriodesRepository;
import com.ayouris.tawassol.service.DefinitionPeriodesService;
import com.ayouris.tawassol.service.PeriodeService;

@Service
public class DefinitionPeriodesServiceImpl extends GenericServiceImpl<DefinitionPeriodes, Long>
		implements DefinitionPeriodesService {

	@Autowired
	private DefinitionPeriodesRepository definitionPeriodesRepository;

	@Autowired
	private PeriodeService periodeService;

	@Override
	public void updateDefinitionPeriodesConcessionnaire(PieceReglement pieceReglement) {
		
		List<DefinitionPeriodes> periodes = getPeriodeByConcessionnaire(pieceReglement.getConcessionnaire().getId());
		Collections.sort(periodes);
		DefinitionPeriodes lastDefPeriode = periodes.get(periodes.size() - 1);
		DefinitionPeriodes newDefPeriode = new DefinitionPeriodes();
		
		Periode periode = null;
		
		Date lastStart = new Date();
		Calendar lastStartCal = Calendar.getInstance();
		lastStartCal.setTime(lastStart);

		Date lastEnd = new Date();
		Calendar lastEndCal = Calendar.getInstance();
		lastStartCal.setTime(lastEnd);

		Date newStart = new Date();
		Calendar newStartCal = Calendar.getInstance();
		lastStartCal.setTime(newStart);

		Date newEnd = new Date();
		Calendar newEndCal = Calendar.getInstance();
		lastStartCal.setTime(newEnd);

		if (Periodicite.HEBDOMADAIRE.equals(pieceReglement.getPeriodicite())) {
			newStartCal = lastEndCal;
			newStartCal.add(Calendar.DATE, 1);
			newDefPeriode.setDateDebut(newStartCal.getTime());
			periode = periodeService.getPeriodeByConcess(
					pieceReglement.getConcessionnaire().getConditionCommerciale().getId(),
					newStartCal.get(Calendar.DAY_OF_WEEK) + 1);
			
			newEndCal = newStartCal;
			newEndCal.add(Calendar.DATE, periode.getEnd()-periode.getStart());
			if (periode.getDay() >= periode.getStart() && periode.getDay() <= periode.getEnd()){
				newEndCal.add(Calendar.DATE, periode.getEnd()-periode.getStart());
			}else {
				
			}

		}

	}

	@Override
	@Transactional
	public List<DefinitionPeriodes> getPeriodeByConcessionnaire(Long id) {
		QDefinitionPeriodes periode = QDefinitionPeriodes.definitionPeriodes;
		return (List<DefinitionPeriodes>) definitionPeriodesRepository.findAll(periode.concessionnaire.id.eq(id));
	}

}
