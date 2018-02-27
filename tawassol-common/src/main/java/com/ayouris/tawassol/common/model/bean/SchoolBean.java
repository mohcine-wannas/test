package com.ayouris.tawassol.common.model.bean;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.ayouris.tawassol.common.model.entity.School;
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
public class SchoolBean implements Serializable{

    private static final long serialVersionUID = -5029569008726313519L;

    @SuppressWarnings("serial")
	public static final Type LIST_BEAN_TYPE = new TypeToken<ArrayList<SchoolBean>>(){}
            .getType();

    @SuppressWarnings("serial")
	public static final Type LIST_ENTITY_TYPE = new TypeToken<ArrayList<School>>(){}
            .getType();

    private Long id;
	private String adresse;
	private String code;
	private String codeMassar;
	private String codePostal;
	private String email;
	private String nom;
	private String siteWeb;
	private String tel;
	private String tel2;
	private VilleBean ville;
	private PaysBean pays;
	
	private List<CycleBean> cycles;

}
