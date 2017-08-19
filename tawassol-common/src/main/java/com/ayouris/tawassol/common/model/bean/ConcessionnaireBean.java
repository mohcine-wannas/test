package com.ayouris.tawassol.common.model.bean;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonView;
import com.google.common.reflect.TypeToken;

import lombok.Getter;
import lombok.Setter;
import com.ayouris.tawassol.common.model.entity.Concessionnaire;
import com.ayouris.tawassol.common.model.entity.ConditionCommerciale;
import com.ayouris.tawassol.common.view.CommonView;
import com.ayouris.tawassol.common.view.EditionView;

/**
 * 
 * @author m.wannas
 *
 */

@Setter
@Getter
public class ConcessionnaireBean implements Serializable{

    private static final long serialVersionUID = -5029569008726313519L;

    @SuppressWarnings("serial")
	public static final Type LIST_BEAN_TYPE = new TypeToken<ArrayList<ConcessionnaireBean>>(){}
            .getType();

    @SuppressWarnings("serial")
	public static final Type LIST_ENTITY_TYPE = new TypeToken<ArrayList<Concessionnaire>>(){}
            .getType();
    
    @JsonView(CommonView.class)
    private Long id;
    @JsonView(CommonView.class)
    private String libelle;
    @JsonView(CommonView.class)
    private String abreviation;
    @JsonView(CommonView.class)
	private String code;
    @JsonView(CommonView.class)
    private Boolean active;
    @JsonView(CommonView.class)
    private String adresse;
    @JsonView(CommonView.class)
    private String mail;
    @JsonView(CommonView.class)
    private String ville;
    @JsonView(CommonView.class)
    private String tel;
    @JsonView(CommonView.class)
    private String fax;
    @JsonView(CommonView.class)
    private String interlocuteur;
    @JsonView(CommonView.class)
    private String mobile;
    @JsonView(CommonView.class)
    private String registreCommerce;
    @JsonView(CommonView.class)
    private String idFiscal;
    @JsonView(CommonView.class)
    private String patente;



//  private List<SignataireBean> signataires;
//	private List<AffectationBean> affectations;

    @JsonView(CommonView.class)
    private ConditionCommercialeBean conditionCommerciale;
    
    @JsonView(CommonView.class)
    private String conditionCommercialePieceReglementLibelle;

    @JsonView(EditionView.class)
	public ConditionCommercialeBean getConditionCommerciale() {
		return conditionCommerciale;
	}
    
//    public List<SignataireBean> getSignataires() {
//		return signataires;
//    }
//
//    public List<AffectationBean> getAffectations() {
//		return affectations;
//    }
//
	public void setCode(String code) {
		if(code != null) {
			this.code = code.toUpperCase();
		}
	}
	
    public boolean isActive() {
        return active;
    }

    public void setConditionCommerciale(ConditionCommercialeBean conditionCommerciale) {
        this.conditionCommerciale = conditionCommerciale;
    }
}
