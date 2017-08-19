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
import com.ayouris.tawassol.common.model.entity.JourneeActivite;
import com.ayouris.tawassol.common.model.enums.ActiviteJourneeActivite;
import com.ayouris.tawassol.common.model.enums.StatutJourneeActivite;
import com.ayouris.tawassol.common.view.CommonView;

@Setter
@Getter
public class JourneeActiviteBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5110474668922097541L;

	@SuppressWarnings("serial")
	public static final Type LIST_BEAN_TYPE = new TypeToken<ArrayList<JourneeActiviteBean>>() {
	}.getType();

	@SuppressWarnings("serial")
	public static final Type LIST_ENTITY_TYPE = new TypeToken<ArrayList<JourneeActivite>>() {
	}.getType();

	
	
	@JsonView(CommonView.class)
	private Long id;
	
	@JsonView(CommonView.class)
	private String code;
	
	@JsonView(CommonView.class)
	private Date dateOuverture;
	
	@JsonView(CommonView.class)
	private Date dateJournee;
	
	@JsonView(CommonView.class)
	private Date dateFermeture;
	
	@JsonView(CommonView.class)
	private StatutJourneeActivite statut;
	
	@JsonView(CommonView.class)
	private ActiviteJourneeActivite activite;
	
	private SiteBean site;
	
	@JsonView(CommonView.class)
	private String siteLibelle;
	
	public void setCode(String code) {
		this.code = code != null ? code.toUpperCase() : null;
	}


}
