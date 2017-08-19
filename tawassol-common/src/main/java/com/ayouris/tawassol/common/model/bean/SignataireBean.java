package com.ayouris.tawassol.common.model.bean;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonView;
import com.google.common.reflect.TypeToken;

import lombok.Getter;
import lombok.Setter;
import com.ayouris.tawassol.common.model.entity.Signataire;
import com.ayouris.tawassol.common.view.CommonView;

/**
 * 
 * @author m.wannas
 *
 */

@Setter
@Getter
public class SignataireBean implements Serializable{

    private static final long serialVersionUID = -5029569008726313519L;

    @SuppressWarnings("serial")
	public static final Type LIST_BEAN_TYPE = new TypeToken<ArrayList<SignataireBean>>(){}
            .getType();

    @SuppressWarnings("serial")
	public static final Type LIST_ENTITY_TYPE = new TypeToken<ArrayList<Signataire>>(){}
            .getType();
    
    @JsonView(CommonView.class)
    private Long id;
    @JsonView(CommonView.class)
	private String libelle;
    @JsonView(CommonView.class)
	private String abreviation;
    @JsonView(CommonView.class)
	private String nom;
    @JsonView(CommonView.class)
	private String prenom;
    @JsonView(CommonView.class)
	private String fonction;
    @JsonView(CommonView.class)
	private String tel;
    @JsonView(CommonView.class)
	private String signature;
    @JsonView(CommonView.class)
	private ConcessionnaireBean concessionnaire;
    @JsonView(CommonView.class)
	private String code;
    @JsonView(CommonView.class)
    private Boolean active;
    @JsonView(CommonView.class)
    private Long concessionnaireId;

    
    public boolean isActive() {
        return active;
    }

}
