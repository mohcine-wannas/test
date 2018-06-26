package com.ayouris.tawassol.common.model.bean;

import com.ayouris.tawassol.common.model.entity.AffectationMessageClasse;
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
public class AffectationMessageClasseBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1229946509233692921L;

	@SuppressWarnings("serial")
	public static final Type LIST_BEAN_TYPE = new TypeToken<ArrayList<AffectationMessageClasseBean>>() {
	}.getType();

	@SuppressWarnings("serial")
	public static final Type LIST_ENTITY_TYPE = new TypeToken<ArrayList<AffectationMessageClasse>>() {
	}.getType();

	private Long id;
	private ClasseBean classe;

}
