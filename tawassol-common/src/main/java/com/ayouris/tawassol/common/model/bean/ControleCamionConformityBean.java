package com.ayouris.tawassol.common.model.bean;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonView;
import com.google.common.reflect.TypeToken;

import lombok.Getter;
import lombok.Setter;
import com.ayouris.tawassol.common.model.entity.ControleCamionConformity;
import com.ayouris.tawassol.common.view.CommonView;

/**
 * 
 * @author m.wannas
 *
 */

@Setter
@Getter
public class ControleCamionConformityBean implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -331893316065464619L;

	@SuppressWarnings("serial")
	public static final Type LIST_BEAN_TYPE = new TypeToken<ArrayList<ControleCamionConformityBean>>() {
	}.getType();

	@SuppressWarnings("serial")
	public static final Type LIST_ENTITY_TYPE = new TypeToken<ArrayList<ControleCamionConformity>>() {
	}.getType();

	@JsonView(CommonView.class)
	private ControleCamionBean controleCamion;
	@JsonView(CommonView.class)
	private Boolean conforme;

}
