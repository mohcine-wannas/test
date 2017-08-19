package com.ayouris.tawassol.common.model.bean;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonView;
import com.google.common.reflect.TypeToken;

import lombok.Getter;
import lombok.Setter;
import com.ayouris.tawassol.common.model.entity.LigneOrdreLivraison;
import com.ayouris.tawassol.common.model.enums.StatutLigneBonLivraison;
import com.ayouris.tawassol.common.view.CommonView;
import com.ayouris.tawassol.common.view.EditionView;

@Setter
@Getter
public class LigneBonLivraisonVracBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5110474668922097541L;

	@SuppressWarnings("serial")
	public static final Type LIST_BEAN_TYPE = new TypeToken<ArrayList<LigneBonLivraisonVracBean>>() {
	}.getType();

	@SuppressWarnings("serial")
	public static final Type LIST_ENTITY_TYPE = new TypeToken<ArrayList<LigneOrdreLivraison>>() {
	}.getType();
	
	@JsonView(CommonView.class)
	private Long id;
	
	@JsonView(EditionView.class)
	private ProduitBean produit;

	@JsonView(CommonView.class)
	private StatutLigneBonLivraison statut;

	@JsonView(CommonView.class)
	private Integer qteCommandees;

	@JsonView(CommonView.class)
	private Date heureDebutChargement;

	@JsonView(CommonView.class)
	private Date heureFinChargement;

	@JsonView(CommonView.class)
	private String nDePeseeDeSortie;

	@JsonView(CommonView.class)
	private String nDePeseeDeEntree;

	@JsonView(CommonView.class)
	private Integer poidsVide;

	@JsonView(CommonView.class)
	private Integer poidsPleine;

	@JsonView(CommonView.class)
	private Integer qteLivrees;

	
	
}
