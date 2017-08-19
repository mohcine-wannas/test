package com.ayouris.tawassol.common.model.bean;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonView;
import com.google.common.reflect.TypeToken;

import lombok.Getter;
import lombok.Setter;
import com.ayouris.tawassol.common.model.entity.ControleCamion;
import com.ayouris.tawassol.common.model.enums.TypeChargement;
import com.ayouris.tawassol.common.view.CommonView;

/**
 * 
 * @author m.wannas
 *
 */

@Setter
@Getter
public class ControleCamionBean implements Serializable{

    private static final long serialVersionUID = -5029569008726313519L;

    @SuppressWarnings("serial")
	public static final Type LIST_BEAN_TYPE = new TypeToken<ArrayList<ControleCamionBean>>(){}
            .getType();

    @SuppressWarnings("serial")
	public static final Type LIST_ENTITY_TYPE = new TypeToken<ArrayList<ControleCamion>>(){}
            .getType();

    @JsonView(CommonView.class)
    private Long id;
	@JsonView(CommonView.class)
    private TypeChargement typeChargement;
	@JsonView(CommonView.class)
    private String controle;
	@JsonView(CommonView.class)
    private Boolean active;


    
    public boolean isActive() {
        return active;
    }

	public void setControle(String controle) {
		if(controle != null) {
			this.controle = controle.toUpperCase();
		}
	}
}
