package com.ayouris.tawassol.common.model.bean;

import com.ayouris.tawassol.common.model.entity.AffectationMessageUnite;
import com.google.common.reflect.TypeToken;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * 
 * @author m.wannas
 *
 */

@Setter
@Getter
public class AffectationMessageUniteBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1229946509233692921L;

	@SuppressWarnings("serial")
	public static final Type LIST_BEAN_TYPE = new TypeToken<ArrayList<AffectationMessageUniteBean>>() {
	}.getType();

	@SuppressWarnings("serial")
	public static final Type LIST_ENTITY_TYPE = new TypeToken<ArrayList<AffectationMessageUnite>>() {
	}.getType();

	private Long id;
	private UniteBean unite;

}
