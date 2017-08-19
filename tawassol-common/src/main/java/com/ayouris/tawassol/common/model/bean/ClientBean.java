package com.ayouris.tawassol.common.model.bean;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonView;
import com.google.common.reflect.TypeToken;

import lombok.Getter;
import lombok.Setter;
import com.ayouris.tawassol.common.model.entity.Client;
import com.ayouris.tawassol.common.view.CommonView;

/**
 * 
 * @author m.wannas
 *
 */

@Setter
@Getter
public class ClientBean implements Serializable{

    private static final long serialVersionUID = -5029569008726313519L;

    @SuppressWarnings("serial")
	public static final Type LIST_BEAN_TYPE = new TypeToken<ArrayList<ClientBean>>(){}
            .getType();

    @SuppressWarnings("serial")
	public static final Type LIST_ENTITY_TYPE = new TypeToken<ArrayList<Client>>(){}
            .getType();
    @JsonView(CommonView.class)
    private Long id;
    @JsonView(CommonView.class)
    private String libelle;
    private String abreviation;
    private String adresse;
    
	private String code;
    private GroupBean groupe;
    
    private String interlocuteur;
    private String  ville;
    private String  tel;
    private String  mobile;
    private String  fax;
    private String  email;
    private String registreCommerce;
    private String  idFiscal;
    private String  patente;
    private Boolean  gestionSolde;

    private Boolean active;
    
	public void setCode(String code) {
		if(code != null) {
			this.code = code.toUpperCase();
		}
	}
    public boolean isActive() {
        return active;
    }

}
