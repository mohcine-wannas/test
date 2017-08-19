package com.ayouris.tawassol.common.model.bean;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonView;
import com.google.common.reflect.TypeToken;

import lombok.Getter;
import lombok.Setter;
import com.ayouris.tawassol.common.model.entity.ArretEmplissage;
import com.ayouris.tawassol.common.view.CommonView;

@Setter
@Getter
public class ArretEmplissageBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5110474668922097541L;

	@SuppressWarnings("serial")
	public static final Type LIST_BEAN_TYPE = new TypeToken<ArrayList<ArretEmplissageBean>>() {
	}.getType();

	@SuppressWarnings("serial")
	public static final Type LIST_ENTITY_TYPE = new TypeToken<ArrayList<ArretEmplissage>>() {
	}.getType();

	@JsonView(CommonView.class)
	private Date heureDebut;
	
	@JsonView(CommonView.class)
	private Long id;

	@JsonView(CommonView.class)
	private Date heureFin;

	@JsonView(CommonView.class)
	private CauseArretEmplissageBean causeArret;
	
//	@JsonView(CommonView.class)
//	private FicheMarcheBean ficheMarche;
}
