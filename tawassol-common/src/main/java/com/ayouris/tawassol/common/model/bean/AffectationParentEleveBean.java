package com.ayouris.tawassol.common.model.bean;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;

import com.ayouris.tawassol.common.model.entity.AffectationParentEleve;
import com.google.common.reflect.TypeToken;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author m.wannas
 *
 */

@Setter
@Getter
public class AffectationParentEleveBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1229946509233692921L;

	@SuppressWarnings("serial")
	public static final Type LIST_BEAN_TYPE = new TypeToken<ArrayList<AffectationParentEleveBean>>() {
	}.getType();

	@SuppressWarnings("serial")
	public static final Type LIST_ENTITY_TYPE = new TypeToken<ArrayList<AffectationParentEleve>>() {
	}.getType();

	private Long id;
	private ParentBean parent;
	private Boolean enabled;

}
