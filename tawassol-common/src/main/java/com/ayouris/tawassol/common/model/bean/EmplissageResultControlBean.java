package com.ayouris.tawassol.common.model.bean;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonView;
import com.google.common.reflect.TypeToken;

import lombok.Getter;
import lombok.Setter;
import com.ayouris.tawassol.common.model.entity.EmplissageControlColonne;
import com.ayouris.tawassol.common.model.entity.EmplissageControlLigne;
import com.ayouris.tawassol.common.model.entity.EmplissageResultControl;
import com.ayouris.tawassol.common.view.CommonView;
import com.ayouris.tawassol.common.view.EditionView;

@Setter
@Getter
public class EmplissageResultControlBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5110474668922097541L;

	@SuppressWarnings("serial")
	public static final Type LIST_BEAN_TYPE = new TypeToken<ArrayList<EmplissageResultControlBean>>() {
	}.getType();

	@SuppressWarnings("serial")
	public static final Type LIST_ENTITY_TYPE = new TypeToken<ArrayList<EmplissageResultControl>>() {
	}.getType();

	@JsonView(CommonView.class)
	private Long id;
	
	@JsonView(CommonView.class)
	private Long resultat;
	
	@JsonView(CommonView.class)
	private EmplissageControlLigne emplissageControlLigne;
	
	@JsonView(CommonView.class)
	private EmplissageControlColonne emplissageControlColonne;
	
	@JsonView(EditionView.class)
	private EmplissageBean emplissage;
}
