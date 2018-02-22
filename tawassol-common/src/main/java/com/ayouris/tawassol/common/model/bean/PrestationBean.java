package com.ayouris.tawassol.common.model.bean;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.common.reflect.TypeToken;

import lombok.Getter;
import lombok.Setter;
import com.ayouris.tawassol.common.model.entity.Prestation;

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

    private Long id;
	private String libelle;
	private String libelleLivraison;
	
	private Boolean livree;
	private Boolean controlee;
    private Boolean active;

    public boolean isActive() {
        return active;
    }

}
