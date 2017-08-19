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
import com.ayouris.tawassol.common.model.entity.FicheMarche;
import com.ayouris.tawassol.common.model.enums.StatutFicheMarche;
import com.ayouris.tawassol.common.view.CommonView;
import com.ayouris.tawassol.common.view.EditionView;
import com.ayouris.tawassol.common.view.ListView;

@Setter
@Getter
public class FicheMarcheBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5110474668922097541L;

	@SuppressWarnings("serial")
	public static final Type LIST_BEAN_TYPE = new TypeToken<ArrayList<FicheMarcheBean>>() {
	}.getType();

	@SuppressWarnings("serial")
	public static final Type LIST_ENTITY_TYPE = new TypeToken<ArrayList<FicheMarche>>() {
	}.getType();

	@JsonView(CommonView.class)
	private Long id;

	@JsonView(CommonView.class)
	private StatutFicheMarche statut;

	@JsonView(CommonView.class)
	private Date dateJournee;

	@JsonView(CommonView.class)
	private Long qteEmplissageTotal;
	
	@JsonView(EditionView.class)
	private Date tempsEffectif;
	
	@JsonView(CommonView.class)
	private Double cadenceHorsArrets;
	
	@JsonView(CommonView.class)
	private Double cadenceArretsCompris;
	
	@JsonView(EditionView.class)
	private Date tempsPause;

	@JsonView(CommonView.class)
	private ProduitBean produit;

	@JsonView(CommonView.class)
	private UserDetailsBean user;
	
	@JsonView(CommonView.class)
	private PostBean post;
	
	@JsonView(EditionView.class)
	private Set<ArretEmplissageBean> arretsEmplissage;

	@JsonView(EditionView.class)
	private Set<CreuxEmplissageBean> creuxEmplissage;
	
	@JsonView(EditionView.class)
	private Date tempsCreux;

	@JsonView(EditionView.class)
	private Date tempsArrets;
	
	@JsonView(ListView.class)
	private String postHeureDebut;
	
	@JsonView(ListView.class)
	private String postHeureFin;
	
	@JsonView(ListView.class)
	private String postLibelle;
	
	@JsonView(ListView.class)
	private String userUsername;
	
	@JsonView(ListView.class)
	private String produitLibelle;
	


}
