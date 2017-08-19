package com.ayouris.tawassol.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import com.ayouris.tawassol.common.model.bean.PieceReglementBean;
import com.ayouris.tawassol.common.model.entity.PieceReglement;

/**
 * Created by Issmahane EL BAZ on 20/07/2017.
 */
public interface PieceReglementService extends GenericService<PieceReglement,Long>,
        RefService<PieceReglementBean> {
    List<PieceReglementBean> getAll();

	void savePieceReglementPJ(Long id, InputStream in) throws IOException;

	File getPieceReglementPJ(Long id);

	List<PieceReglement> getAllPREnlevementNonSoldeByConcessId(Long id);

	void annuler(Long id);
}
