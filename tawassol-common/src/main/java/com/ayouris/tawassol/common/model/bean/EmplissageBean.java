package com.ayouris.tawassol.common.model.bean;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonView;
import com.google.common.reflect.TypeToken;

import lombok.Getter;
import lombok.Setter;
import com.ayouris.tawassol.admin.model.beans.SiteBean;
import com.ayouris.tawassol.common.model.entity.Emplissage;
import com.ayouris.tawassol.common.model.enums.OrigineEmplissage;
import com.ayouris.tawassol.common.model.enums.StatutEmplissage;
import com.ayouris.tawassol.common.view.CommonView;
import com.ayouris.tawassol.common.view.EditionView;
import com.ayouris.tawassol.common.view.ListView;

@Setter
@Getter
public class EmplissageBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5110474668922097541L;

	@SuppressWarnings("serial")
	public static final Type LIST_BEAN_TYPE = new TypeToken<ArrayList<EmplissageBean>>() {
	}.getType();

	@SuppressWarnings("serial")
	public static final Type LIST_ENTITY_TYPE = new TypeToken<ArrayList<Emplissage>>() {
	}.getType();

	@JsonView(CommonView.class)
	private Long id;
	
	@JsonView(CommonView.class)
	private Date createdOn;
	
	@JsonView(CommonView.class)
	private String numOE;
	
	@JsonView(CommonView.class)
	private Integer qteEmplissage;
	
	@JsonView(CommonView.class)
	private StatutEmplissage statut;
	
	@JsonView(EditionView.class)
	private Date heureDebut;
	
	@JsonView(EditionView.class)
	private Date heureFin;
	
	@JsonView(EditionView.class)
	private Integer qteBDLavee;
	
	@JsonView(EditionView.class)
	private Integer cadence;
	
	@JsonView(EditionView.class)
	private Integer bdControle;
	
	@JsonView(EditionView.class)
	private Integer bdDefect;
	
	@JsonView(EditionView.class)
	private Integer bdAReparer;
	
	@JsonView(CommonView.class)
	private OrigineEmplissage origine;
	
	@JsonView(EditionView.class)
	private ClientBean client;
	
	@JsonView(EditionView.class)
	private ConcessionnaireBean concessionnaire;
	
	@JsonView(EditionView.class)
	private SiteBean site;
	
	@JsonView(EditionView.class)
	private ProduitBean produit;
	
	@JsonView(ListView.class)
	private String clientLibelle;

	@JsonView(ListView.class)
	private String concessionnaireLibelle;
	
	@JsonView(ListView.class)
	private String produitLibelle;
	
	@JsonView(EditionView.class)
	private  Map<Long,Map<Long,Long>> matriceEmplissageResultat = new LinkedHashMap<Long,Map<Long,Long>>();
	
	public void setNumOE(String numOE) {
		this.numOE = numOE != null ? numOE.toUpperCase() : null;
	}

}
