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
import com.ayouris.tawassol.common.model.entity.LigneBonLivraisonConditionne;
import com.ayouris.tawassol.common.model.enums.StatutLigneBonLivraison;
import com.ayouris.tawassol.common.view.CommonView;


@Setter
@Getter
public class LigneBonLivraisonConditionneBean implements Serializable{

	private static final long serialVersionUID = -5110474668922097541L;

	@SuppressWarnings("serial")
	public static final Type LIST_BEAN_TYPE = new TypeToken<ArrayList<LigneBonLivraisonConditionneBean>>() {
	}.getType();

	@SuppressWarnings("serial")
	public static final Type LIST_ENTITY_TYPE = new TypeToken<ArrayList<LigneBonLivraisonConditionne>>() {
	}.getType();
	
	@JsonView(CommonView.class)
	private Long id;
	
	@JsonView(CommonView.class)
	private ProduitBean produit;

    @JsonView(CommonView.class)
    private List<LignePrestationBean> lignePrestation;

    @JsonView(CommonView.class)
    private StatutLigneBonLivraison statut;

    @JsonView(CommonView.class)
    private Date heureDebutChargement;

    @JsonView(CommonView.class)
    private Date heureFinChargement;

    @JsonView(CommonView.class)
    private Integer qteDechargee;

    @JsonView(CommonView.class)
    private Integer monchonDebut;

    @JsonView(CommonView.class)
    private Integer monchonFin;

    @JsonView(CommonView.class)
    private Integer retourneeEtrangere;

    @JsonView(CommonView.class)
    private Integer retourneeSansChap;

    @JsonView(CommonView.class)
    private Integer retourneeAutre;

    @JsonView(CommonView.class)
    private Integer avecEchange;

    @JsonView(CommonView.class)
    private Integer sansEchange;

    @JsonView(CommonView.class)
    private Integer priseStockPlein;

    @JsonView(CommonView.class)
    private Integer empliesSurChaine;

    @JsonView(CommonView.class)
    private Integer qteBD;

    @JsonView(CommonView.class)
    private Integer qteTM;

    @JsonView(CommonView.class)
    private MotifBean motifManque;

    @JsonView(CommonView.class)
    private Integer qteCommande;




	
}
