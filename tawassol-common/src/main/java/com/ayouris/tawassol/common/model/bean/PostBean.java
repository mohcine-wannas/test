package com.ayouris.tawassol.common.model.bean;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonView;
import com.google.common.reflect.TypeToken;

import lombok.Getter;
import lombok.Setter;
import com.ayouris.tawassol.common.model.entity.Post;
import com.ayouris.tawassol.common.view.CommonView;

@Setter
@Getter
public class PostBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5110474668922097541L;

	@SuppressWarnings("serial")
	public static final Type LIST_BEAN_TYPE = new TypeToken<ArrayList<PostBean>>() {
	}.getType();

	@SuppressWarnings("serial")
	public static final Type LIST_ENTITY_TYPE = new TypeToken<ArrayList<Post>>() {
	}.getType();

	@JsonView(CommonView.class)
	private Date heureDebut;
	
	@JsonView(CommonView.class)
	private Long id;
	
	@JsonView(CommonView.class)
    private Boolean active;

	@JsonView(CommonView.class)
	private Date heureFin;

	@JsonView(CommonView.class)
	String libelle;
}
