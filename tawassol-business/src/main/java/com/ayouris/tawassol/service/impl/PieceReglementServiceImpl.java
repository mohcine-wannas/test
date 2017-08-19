package com.ayouris.tawassol.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.jdo.annotations.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.ayouris.tawassol.admin.model.enums.ViewName;
import com.ayouris.tawassol.common.exception.ErrorMessageType;
import com.ayouris.tawassol.common.mapper.CustomModelMapper;
import com.ayouris.tawassol.common.model.bean.GridListBean;
import com.ayouris.tawassol.common.model.bean.PageDataBean;
import com.ayouris.tawassol.common.model.bean.PieceReglementBean;
import com.ayouris.tawassol.common.model.entity.ColumnDef;
import com.ayouris.tawassol.common.model.entity.PieceReglement;
import com.ayouris.tawassol.common.model.entity.QPieceReglement;
import com.ayouris.tawassol.common.model.entity.Ventilation;
import com.ayouris.tawassol.common.model.enums.Periodicite;
import com.ayouris.tawassol.common.model.enums.StatutPieceReglement;
import com.ayouris.tawassol.common.util.PropertiesHolders;
import com.ayouris.tawassol.repository.PieceReglementRepository;
import com.ayouris.tawassol.service.ColumnDefService;
import com.ayouris.tawassol.service.ConcessionnaireService;
import com.ayouris.tawassol.service.PieceReglementService;
import com.ayouris.tawassol.service.ServiceException;
import com.ayouris.tawassol.service.VentilationService;

@Service
public class PieceReglementServiceImpl extends GenericServiceImpl<PieceReglement, Long>
		implements PieceReglementService {

	@Autowired
	private PieceReglementRepository pieceReglementRepository;

	@Autowired
	private ColumnDefService columnDefService;

	@Autowired
	private CustomModelMapper mapper;

	@Autowired
	private PropertiesHolders propertiesHolder;

	@Autowired
	private ConcessionnaireService concessionnaireService;

	@Autowired
	private VentilationService ventilationService;
	
	@Override
	@Transactional
	public void annuler(Long id) {

		PieceReglement entity = findOne(id);
		if (entity == null) {
			throw new ServiceException(ErrorMessageType.PIECE_REGLEMENT_NOT_FOUND);
		}
		if (StatutPieceReglement.EN_INSTANCE.equals(entity.getStatut())) {
			entity.setStatut(StatutPieceReglement.ANNULEE);
			save(entity);
		} else{
			throw new ServiceException(ErrorMessageType.FICHE_MARCHE_CLOTURE_INVALID);
		}
		
	}
	

	@Override
	@Transactional
	public Long createOrUpdate(PieceReglementBean pieceReglementBean) {
		validateRequiredValues(pieceReglementBean);
		validatePositiveValues(pieceReglementBean);
		List<Ventilation> ventilations = new ArrayList<Ventilation>();

		PieceReglement entity = (PieceReglement) mapper.mapStrict(pieceReglementBean, PieceReglement.class);
		if (isNew(entity)) {

			entity.setStatut(StatutPieceReglement.EN_INSTANCE);
			entity.setDateReglement(new Date());
			entity.setMontantRestant(entity.getMontant());
			entity.setMontantDu(entity.getMontant());
			entity.setSoldee(false);
			if (entity.getConcessionnaire().getConditionCommerciale()==null) {
				entity.setPeriodicite(Periodicite.POUR_CHAQUE_ENLEVEMENT);
			} else {
				entity.setPeriodicite(concessionnaireService.findOne(entity.getConcessionnaire().getId())
						.getConditionCommerciale().getPeriodicite());
			}
			entity.setPeriodicite(concessionnaireService.findOne(entity.getConcessionnaire().getId())
					.getConditionCommerciale().getPeriodicite());
			if (!Periodicite.POUR_CHAQUE_ENLEVEMENT.equals(entity.getPeriodicite())) {
				// TODO : Période Couverte
				ventilations = ventilationService.ventilationPeriodique(entity);
			}
		}

		try {

			if (isNew(entity)) {

				save(entity);
				if (!ventilations.isEmpty()) {
					for (Ventilation ventilation : ventilations) {
						ventilation.setPieceReglement(entity);
						ventilationService.save(ventilation);
					}
				}
				return entity.getId();

			} else {
				PieceReglement oldEntity = findOne(entity.getId());
				oldEntity.setBanque(entity.getBanque());
				oldEntity.setConcessionnaire(entity.getConcessionnaire());
				oldEntity.setMontant(entity.getMontant());
				oldEntity.setReference(entity.getReference());
				oldEntity.setNote(entity.getNote());
				oldEntity.setTypePieceReglement(entity.getTypePieceReglement());
				return save(oldEntity).getId();
			}

		} catch (DataIntegrityViolationException ex) {
			throw new ServiceException(ErrorMessageType.PIECE_REGLEMENT_INVALID);
		}
	}
	private void validatePositiveValues(PieceReglementBean pieceReglementBean) {
		if (pieceReglementBean.getMontant() <= 0) {
			throw new ServiceException(ErrorMessageType.PIECE_REGLEMENT_MONTANT_POSITIVE);
		}
	}

	public void validateRequiredValues(PieceReglementBean pieceReglementBean) {
		if (pieceReglementBean.getConcessionnaire() == null && pieceReglementBean.getBanque() == null
				&& pieceReglementBean.getMontant() == null && pieceReglementBean.getTypePieceReglement() == null
				&& pieceReglementBean.getReference() == null) {
			throw new ServiceException(ErrorMessageType.PIECE_REGLEMENT_MISSING_REQUIRED_VALUES);
		}
	}

	@Override
	public PieceReglementBean getDetails(Long id) {

		PieceReglement pieceReglement = findOne(id);
		return mapper.map(pieceReglement, PieceReglementBean.class);
	}

	public Map<String, ColumnDef> getColumnDef() {
		return columnDefService.getColumnDefMapByViewName(ViewName.PIECE_REGLEMENT);
	}

	@Override
	public GridListBean<PieceReglementBean> list(PageDataBean paginateData) {
		return pieceReglementRepository.findEntities(paginateData, getColumnDef());
	}

	@Override
	public List<PieceReglementBean> getAll() {
		List<PieceReglement> pieceReglements = findAll();
		return mapper.map(pieceReglements, PieceReglementBean.LIST_BEAN_TYPE);
	}

	@Override
	public void savePieceReglementPJ(Long id, InputStream in) throws IOException {
		Path folderPath = Paths.get(propertiesHolder.getUploadPathPieceReglement());
		if (!folderPath.toFile().exists()) {
			Files.createDirectories(folderPath);
		}
		String filename = "pieceReglement_" + id + ".jpg";
		java.nio.file.Path path = Paths.get(folderPath.toString(), filename);
		Files.deleteIfExists(path);

		Files.copy(in, path);
		PieceReglement pieceReglement = findOne(id);
		if (pieceReglement != null) {
			pieceReglement.setPj(path.toRealPath().toString());
			save(pieceReglement);
		} else {
			throw new RuntimeException();
		}
	}

	@Override
	public File getPieceReglementPJ(Long id) {
		Path folderPath = Paths.get(propertiesHolder.getUploadPathPieceReglement());
		String filename = "pieceReglement_" + id + ".jpg";
		Path path = Paths.get(folderPath.toString(), filename);
		return Files.exists(path) ? path.toFile() : null;
	}

	@Override
	@Transactional
	public List<PieceReglement> getAllPREnlevementNonSoldeByConcessId(Long id) {
		QPieceReglement pieceReglement = QPieceReglement.pieceReglement;
		return (List<PieceReglement>) pieceReglementRepository.findAll(pieceReglement.concessionnaire.id.eq(id)
				.and(pieceReglement.soldee.isFalse()).and(pieceReglement.statut.ne(StatutPieceReglement.ANNULEE)
						.and(pieceReglement.periodicite.eq(Periodicite.POUR_CHAQUE_ENLEVEMENT))));
	}

}
