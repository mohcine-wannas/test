package com.ayouris.tawassol.common.model.bean;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonView;
import com.google.common.reflect.TypeToken;

import lombok.Getter;
import lombok.Setter;
import com.ayouris.tawassol.admin.model.beans.SiteBean;
import com.ayouris.tawassol.common.model.entity.PrixVenteClient;
import com.ayouris.tawassol.common.view.CommonView;
import com.ayouris.tawassol.common.view.ListView;

@Setter
@Getter
public class PrixVenteClientBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5110474668922097541L;

	@SuppressWarnings("serial")
	public static final Type LIST_BEAN_TYPE = new TypeToken<ArrayList<PrixVenteClientBean>>() {
	}.getType();

	@SuppressWarnings("serial")
	public static final Type LIST_ENTITY_TYPE = new TypeToken<ArrayList<PrixVenteClient>>() {
	}.getType();

	@JsonView(CommonView.class)
	private Long id;
	
	@JsonView(CommonView.class)
	private Long prix;
	
	@JsonView(CommonView.class)
	private Date dateDebut;
	
	@JsonView(CommonView.class)
	private Date dateFin;
	
	@JsonView(CommonView.class)
	private ClientBean client;
	
	@JsonView(CommonView.class)
	private SiteBean site;
	
	@JsonView(CommonView.class)
	private ProduitBean produit;
	
	@JsonView(ListView.class)
	private String clientLibelle;

	@JsonView(ListView.class)
	private String produitLibelle;
	
	@JsonView(ListView.class)
	private String siteLibelle;
}
