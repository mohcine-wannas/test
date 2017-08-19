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
import com.ayouris.tawassol.admin.model.beans.SiteBean;
import com.ayouris.tawassol.common.model.entity.FileAttente;
import com.ayouris.tawassol.common.model.enums.StatutFileAttente;
import com.ayouris.tawassol.common.view.CommonView;
import com.ayouris.tawassol.common.view.EditionView;
import com.ayouris.tawassol.common.view.ListView;
import com.ayouris.tawassol.common.view.Views.EntryControlView;

@Setter
@Getter
public class FileAttenteBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5110474668922097541L;

	@SuppressWarnings("serial")
	public static final Type LIST_BEAN_TYPE = new TypeToken<ArrayList<FileAttenteBean>>() {
	}.getType();

	@SuppressWarnings("serial")
	public static final Type LIST_ENTITY_TYPE = new TypeToken<ArrayList<FileAttente>>() {
	}.getType();

	@JsonView(CommonView.class)
	private Long id;
	@JsonView(CommonView.class)
	private String numBC;
	
	@JsonView(CommonView.class)
	private String numOL;
	
	@JsonView(CommonView.class)
	private Date heureArrivee;
//	@JsonView(CommonView.class)
//	private Date tempsAttente;
	@JsonView(CommonView.class)
	private StatutFileAttente statut;
	
	@JsonView(EditionView.class)
	private ClientBean client;
	@JsonView(CommonView.class)
	private TypeFileAttenteBean typeFileAttente;
	@JsonView(EditionView.class)
	private ConcessionnaireBean concessionnaire;
	@JsonView(EditionView.class)
	private ChauffeurBean chauffeur;
	@JsonView(EditionView.class)
	private CamionBean camion;
	@JsonView(EditionView.class)
	private SiteBean site;
	@JsonView(EditionView.class)
	private List<LigneFileAttenteBean> ligneFileAttente; 
	@JsonView(EditionView.class)
	private CiterneBean citerne;
	
	@JsonView(ListView.class)
	private String clientLibelle;
	@JsonView(ListView.class)
	private String concessionnaireLibelle;
	@JsonView(ListView.class)
	private String camionMatricule;
	@JsonView(ListView.class)
	private String typeFileAttenteType;
	
	private String motif;
	
	@JsonView(EntryControlView.class)
	private List<ControleCamionConformityBean> controleCamionConformities;


	public void setNumBC(String numBC) {
		this.numBC = numBC != null ? numBC.toUpperCase() : null;
	}


}
