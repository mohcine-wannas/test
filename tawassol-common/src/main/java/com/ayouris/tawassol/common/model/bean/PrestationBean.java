package com.ayouris.tawassol.common.model.bean;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonView;
import com.google.common.reflect.TypeToken;

import lombok.Getter;
import lombok.Setter;
import com.ayouris.tawassol.common.model.entity.Prestation;
import com.ayouris.tawassol.common.view.CommonView;

/**
 * 
 * @author m.wannas
 *
 */

@Setter
@Getter
public class PrestationBean implements Serializable{

    private static final long serialVersionUID = -5029569008726313519L;

    @SuppressWarnings("serial")
	public static final Type LIST_BEAN_TYPE = new TypeToken<ArrayList<PrestationBean>>(){}
            .getType();

    @SuppressWarnings("serial")
	public static final Type LIST_ENTITY_TYPE = new TypeToken<ArrayList<Prestation>>(){}
            .getType();
	@JsonView(CommonView.class)
    private Long id;

	@JsonView(CommonView.class)
	private String libelle;

	@JsonView(CommonView.class)
	private String libelleLivraison;

	@JsonView(CommonView.class)
	private Boolean livree;

	@JsonView(CommonView.class)
	private Boolean controlee;

	@JsonView(CommonView.class)
    private Boolean active;
}
