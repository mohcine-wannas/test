package com.ayouris.tawassol.common.model.bean;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonView;
import com.google.common.reflect.TypeToken;

import lombok.Getter;
import lombok.Setter;
import com.ayouris.tawassol.admin.model.beans.SiteBean;
import com.ayouris.tawassol.common.model.entity.OrdreLivraison;
import com.ayouris.tawassol.common.model.enums.StatutOrdreLivraison;
import com.ayouris.tawassol.common.model.enums.TypeBon;
import com.ayouris.tawassol.common.model.enums.TypeChargement;
import com.ayouris.tawassol.common.view.CommonView;
import com.ayouris.tawassol.common.view.EditionView;
import com.ayouris.tawassol.common.view.ListView;

@Setter
@Getter
public class OrdreLivraisonBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5110474668922097541L;

	@SuppressWarnings("serial")
	public static final Type LIST_BEAN_TYPE = new TypeToken<ArrayList<OrdreLivraisonBean>>() {
	}.getType();

	@SuppressWarnings("serial")
	public static final Type LIST_ENTITY_TYPE = new TypeToken<ArrayList<OrdreLivraison>>() {
	}.getType();

	@JsonView(CommonView.class)
	private Long id;
	
	@JsonView(CommonView.class)
	private String numBC;
	
	@JsonView(CommonView.class)
	private String numOL;	
	
	@JsonView(CommonView.class)
	private Date dateCommande;
	
	@JsonView(CommonView.class)
	private StatutOrdreLivraison statut;

	@JsonView(CommonView.class)
	private TypeBon typeBon;

	@JsonView(CommonView.class)
	private TypeChargement typeChargement;

	@JsonView(EditionView.class)
	private ClientBean client;

	@JsonView(CommonView.class)
	private DestinationBean destination;

	@JsonView(EditionView.class)
	private ConcessionnaireBean concessionnaire;

	@JsonView(EditionView.class)
	private ChauffeurBean chauffeur;

	@JsonView(EditionView.class)
	private CamionBean camion;

	@JsonView(EditionView.class)
	private SiteBean site;
	
	@JsonView(EditionView.class)
	private CiterneBean citerne;

	@JsonView(EditionView.class)
	Set<LigneOrdreLivraisonBean> ligneOrdreLivraison;

	@JsonView(EditionView.class)
	LigneOrdreLivraisonVracBean ligneOrdreLivraisonVrac;

	@JsonView(ListView.class)
	private String clientLibelle;

	@JsonView(ListView.class)
	private String concessionnaireLibelle;

	@JsonView(ListView.class)
	private String camionMatricule;

	@JsonView(ListView.class)
	private String destinationLibelle;
	
	@JsonView(ListView.class)
	private String chauffeurNom;
	
	@JsonView(ListView.class)
	private String citerneCode;

	@JsonView(ListView.class)
	private String ligneOrdreLivraisonVracQteCommande;
	
	@JsonView(ListView.class)
	private String ligneOrdreLivraisonVracProduitLibelle;

	private String motif;

	public void setNumBC(String numBC) {
		this.numBC = numBC != null ? numBC.toUpperCase() : null;
	}

	public String getMotif() {
		
		return motif;
	}

}
