package com.ayouris.tawassol.common.model.bean;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonView;
import com.google.common.reflect.TypeToken;

import lombok.Getter;
import lombok.Setter;
import com.ayouris.tawassol.common.model.entity.Chauffeur;
import com.ayouris.tawassol.common.view.CommonView;
import com.ayouris.tawassol.common.view.RefView.ChauffeurListView;

/**
 * 
 * @author m.wannas
 *
 */

@Setter
@Getter
public class ChauffeurBean implements Serializable{

    private static final long serialVersionUID = -5029569008726313519L;

    @SuppressWarnings("serial")
	public static final Type LIST_BEAN_TYPE = new TypeToken<ArrayList<ChauffeurBean>>(){}
            .getType();

    @SuppressWarnings("serial")
	public static final Type LIST_ENTITY_TYPE = new TypeToken<ArrayList<Chauffeur>>(){}
            .getType();

    @JsonView(CommonView.class)        
    private Long id;
    @JsonView(CommonView.class)
    private String adresse;
    
    @JsonView(CommonView.class)
	private ConcessionnaireBean concessionnaire;
    @JsonView(CommonView.class)
	private String cin;
    @JsonView(CommonView.class)
	private String permis;
    @JsonView(CommonView.class)
	private String nom;
    @JsonView(CommonView.class)
	private String prenom;
    @JsonView(CommonView.class)
    private String  ville;
    @JsonView(CommonView.class)
    private String  tel;
    @JsonView(CommonView.class)
    private Boolean active;
    
    @JsonView(ChauffeurListView.class)
    private String concessionnaireLibelle;

    public boolean isActive() {
        return active;
    }
	public void setCin(String cin) {
		if(cin != null) {
			this.cin = cin.toUpperCase();
		}
	}
}
