package com.ayouris.tawassol.common.model.bean;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.sql.Date;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonView;
import com.google.common.reflect.TypeToken;

import lombok.Getter;
import lombok.Setter;
import com.ayouris.tawassol.common.model.entity.DefinitionPeriodes;
import com.ayouris.tawassol.common.view.CommonView;

@Setter
@Getter
public class DefinitionPeriodesBean implements Serializable {

	private static final long serialVersionUID = -5029569008726313519L;

	@SuppressWarnings("serial")
	public static final Type LIST_BEAN_TYPE = new TypeToken<ArrayList<DefinitionPeriodesBean>>() {
	}.getType();

	@SuppressWarnings("serial")
	public static final Type LIST_ENTITY_TYPE = new TypeToken<ArrayList<DefinitionPeriodes>>() {
	}.getType();

	@JsonView(CommonView.class)
	private Long id;
	@JsonView(CommonView.class)
	private Date dateReglement;
	@JsonView(CommonView.class)
	private Date dateTolerance;
	@JsonView(CommonView.class)
	private Date dateDebut;
	@JsonView(CommonView.class)
	private Date dateFin;

}
