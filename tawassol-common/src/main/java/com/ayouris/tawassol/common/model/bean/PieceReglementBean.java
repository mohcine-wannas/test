package com.ayouris.tawassol.common.model.bean;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonView;
import com.google.common.reflect.TypeToken;

import lombok.Getter;
import lombok.Setter;
import com.ayouris.tawassol.common.model.entity.PieceReglement;
import com.ayouris.tawassol.common.model.enums.StatutPieceReglement;
import com.ayouris.tawassol.common.view.CommonView;
import com.ayouris.tawassol.common.view.EditionView;
import com.ayouris.tawassol.common.view.ListView;

@Setter
@Getter
public class PieceReglementBean implements Serializable {

	private static final long serialVersionUID = -502974008476313519L;

	@SuppressWarnings("serial")
	public static final Type LIST_BEAN_TYPE = new TypeToken<ArrayList<PieceReglementBean>>() {
	}.getType();

	@SuppressWarnings("serial")
	public static final Type LIST_ENTITY_TYPE = new TypeToken<ArrayList<PieceReglement>>() {
	}.getType();

	@JsonView(CommonView.class)
	private Long id;

	@JsonView(EditionView.class)
	private BanqueBean banque;

	@JsonView(CommonView.class)
	private Double montant;

	@JsonView(CommonView.class)
	private Double montantDu;


	@JsonView(CommonView.class)
	private Double montantRestant;

	@JsonView(CommonView.class)
	private Date dateReglement;

	@JsonView(CommonView.class)
	private Date dateEligible;

	@JsonView(EditionView.class)
	private ConcessionnaireBean concessionnaire;
	@JsonView(CommonView.class)
	private String reference;

	@JsonView(EditionView.class)
	private String note;

	@JsonView(EditionView.class)
	private StatutPieceReglement statut;

	@JsonView(EditionView.class)
	private TypePieceReglementBean typePieceReglement;

	@JsonView(EditionView.class)
	private String pj;

	@JsonView(ListView.class)
	private String typePieceReglementLibelle;

	@JsonView(ListView.class)
	private String concessionnairetLibelle;

}
