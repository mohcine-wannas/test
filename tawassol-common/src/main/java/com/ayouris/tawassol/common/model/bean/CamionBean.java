package com.ayouris.tawassol.common.model.bean;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonView;
import com.google.common.reflect.TypeToken;

import lombok.Getter;
import lombok.Setter;
import com.ayouris.tawassol.common.model.entity.Camion;
import com.ayouris.tawassol.common.model.enums.TypeCamion;
import com.ayouris.tawassol.common.view.CommonView;

/**
 * 
 * @author m.wannas
 *
 */

@Setter
@Getter
public class CamionBean implements Serializable{

    private static final long serialVersionUID = -5029569008726313519L;

    @SuppressWarnings("serial")
	public static final Type LIST_BEAN_TYPE = new TypeToken<ArrayList<CamionBean>>(){}
            .getType();

    @SuppressWarnings("serial")
	public static final Type LIST_ENTITY_TYPE = new TypeToken<ArrayList<Camion>>(){}
            .getType();
    
    @JsonView(CommonView.class)        
    private Long id;
    @JsonView(CommonView.class)
    private Boolean active;
    @JsonView(CommonView.class)
	private String matricule;
    @JsonView(CommonView.class)
	private ConcessionnaireBean concessionnaire;
    @JsonView(CommonView.class)
	private TypeCamion typeCamion;
    @JsonView(CommonView.class)
	private MarqueBean marque;
    @JsonView(CommonView.class)
	private String concessionnaireLibelle;
    @JsonView(CommonView.class)
	private String marqueLibelle;
	
    public boolean isActive() {
        return active;
    }
    
	public void setMatricule(String matricule) {
		if(matricule != null) {
			this.matricule = matricule.toUpperCase();
		}
	}

}
