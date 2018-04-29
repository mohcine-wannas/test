package com.ayouris.tawassol.common.model.bean;

import com.ayouris.tawassol.common.model.entity.Niveau;
import com.ayouris.tawassol.common.model.entity.Unite;
import com.google.common.reflect.TypeToken;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;

@Setter
@Getter
public class UniteBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1229946509233692921L;

	@SuppressWarnings("serial")
	public static final Type LIST_BEAN_TYPE = new TypeToken<ArrayList<UniteBean>>() {
	}.getType();

	@SuppressWarnings("serial")
	public static final Type LIST_ENTITY_TYPE = new TypeToken<ArrayList<Unite>>() {
	}.getType();
	
	private Long id;
	private String libelle;

}
