package com.ayouris.tawassol.common.model.bean;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;

import org.springframework.data.annotation.Transient;

import com.fasterxml.jackson.annotation.JsonView;
import com.google.common.reflect.TypeToken;

import lombok.Getter;
import lombok.Setter;
import com.ayouris.tawassol.common.model.entity.LigneFileAttente;
import com.ayouris.tawassol.common.view.CommonView;
import com.ayouris.tawassol.common.view.EditionView;

/**
 * 
 * @author m.wannas
 *
 */
@Setter
@Getter
public class LigneFileAttenteBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5110474668922097541L;

	@SuppressWarnings("serial")
	public static final Type LIST_BEAN_TYPE = new TypeToken<ArrayList<LigneFileAttenteBean>>() {
	}.getType();

	@SuppressWarnings("serial")
	public static final Type LIST_ENTITY_TYPE = new TypeToken<ArrayList<LigneFileAttente>>() {
	}.getType();
	
	@JsonView(CommonView.class)
	private Long id;
	
	@JsonView(EditionView.class)
	private ProduitBean produit;
	@JsonView(CommonView.class)
	private Integer qteCommande;
	@JsonView(CommonView.class)
	private Integer qteDefectueuse;
	@Transient
	private FileAttenteBean fileAttente;

	
}
