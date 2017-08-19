package com.ayouris.tawassol.common.model.bean;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;
import com.google.common.reflect.TypeToken;

import lombok.Getter;
import lombok.Setter;
import com.ayouris.tawassol.common.model.entity.BonLivraison;
import com.ayouris.tawassol.common.model.enums.StatutBonLivraison;
import com.ayouris.tawassol.common.model.enums.TypeBon;
import com.ayouris.tawassol.common.model.enums.TypeChargement;
import com.ayouris.tawassol.common.view.CommonView;
import com.ayouris.tawassol.common.view.EditionView;
import com.ayouris.tawassol.common.view.ListView;
import com.ayouris.tawassol.common.view.Views;

@Setter
@Getter
public class BonLivraisonBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5110474668922097541L;

	@SuppressWarnings("serial")
	public static final Type LIST_BEAN_TYPE = new TypeToken<ArrayList<BonLivraisonBean>>() {
	}.getType();

	@SuppressWarnings("serial")
	public static final Type LIST_ENTITY_TYPE = new TypeToken<ArrayList<BonLivraison>>() {
	}.getType();

	@JsonView(CommonView.class)
	private Long id;
	
	@JsonView(CommonView.class)
	private StatutBonLivraison statut;

	@JsonView(CommonView.class)
	private TypeChargement typeChargement;
	
	@JsonView(CommonView.class)
	private String numBL;

	@JsonView(CommonView.class)
	List<LigneBonLivraisonConditionneBean> ligneBonLivraisonConditionne;

	@JsonView(EditionView.class)
	private OrdreLivraisonBean ordreLivraison;

	@JsonView(EditionView.class)
	private LigneBonLivraisonVracBean ligneBonLivraisonVrac;

	@JsonView(CommonView.class)
	private String ordreLivraisonNumBC;

//	@JsonView(EditionView.class)
//	private BonLivraisonBean bonLivraison;

	@JsonView(ListView.class)
	private String ordreLivraisonClientLibelle;

	@JsonView(ListView.class)
	private String ordreLivraisonConcessionnaireLibelle;

	@JsonView(ListView.class)
	private String ordreLivraisonChauffeurNom;

	@JsonView(ListView.class)
	private Date ordreLivraisonDateCommande;

	@JsonView(ListView.class)
	private TypeBon ordreLivraisonTypeBon;


	@JsonView(CommonView.class)
	private String note;

	@JsonView(Views.EntryControlView.class)
	private List<ControleCamionConformityBean> controleCamionConformities;

	public void setnumBL(String numBL) {
		this.numBL = numBL != null ? numBL.toUpperCase() : null;
	}

}
