package com.ayouris.tawassol.common.model.bean;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonView;
import com.google.common.reflect.TypeToken;

import lombok.Getter;
import lombok.Setter;
import com.ayouris.tawassol.common.model.entity.Produit;
import com.ayouris.tawassol.common.model.enums.NatureProduit;
import com.ayouris.tawassol.common.model.enums.TypeChargement;
import com.ayouris.tawassol.common.view.CommonView;

/**
 * 
 * @author m.wannas
 *
 */

@Setter
@Getter
public class ProduitBean implements Serializable{

    private static final long serialVersionUID = -5029569008726313519L;

    @SuppressWarnings("serial")
	public static final Type LIST_BEAN_TYPE = new TypeToken<ArrayList<ProduitBean>>(){}
            .getType();

    @SuppressWarnings("serial")
	public static final Type LIST_ENTITY_TYPE = new TypeToken<ArrayList<Produit>>(){}
            .getType();
    @JsonView(CommonView.class)
    private Long id;
    @JsonView(CommonView.class)
	private String libelle;
    @JsonView(CommonView.class)
	private String code;
    @JsonView(CommonView.class)
    private Boolean active;
    @JsonView(CommonView.class)
	private Double poids;
    @JsonView(CommonView.class)
	private NatureProduit natureProduit;
    @JsonView(CommonView.class)
	private TypeChargement typeChargement;
    @JsonView(CommonView.class)
	private String unite;
    
	public void setCode(String code) {
		if(code != null) {
			this.code = code.toUpperCase();
		}
	}
    public boolean isActive() {
        return active;
    }

}
