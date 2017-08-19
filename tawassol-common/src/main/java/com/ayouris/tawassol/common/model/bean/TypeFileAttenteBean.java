package com.ayouris.tawassol.common.model.bean;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonView;
import com.google.common.reflect.TypeToken;

import lombok.Getter;
import lombok.Setter;
import com.ayouris.tawassol.common.model.entity.TypeFileAttente;
import com.ayouris.tawassol.common.model.enums.CategorieTypeFileAttente;
import com.ayouris.tawassol.common.view.CommonView;

/**
 * 
 * @author m.wannas
 *
 */

@Setter
@Getter
public class TypeFileAttenteBean implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 7048629517393233469L;

	@SuppressWarnings("serial")
	public static final Type LIST_BEAN_TYPE = new TypeToken<ArrayList<TypeFileAttenteBean>>(){}
            .getType();

    @SuppressWarnings("serial")
	public static final Type LIST_ENTITY_TYPE = new TypeToken<ArrayList<TypeFileAttente>>(){}
            .getType();
    
    @JsonView(CommonView.class)        
    private Long id;
    @JsonView(CommonView.class)        
	private String type;
    @JsonView(CommonView.class)        
	private CategorieTypeFileAttente categorie;
    @JsonView(CommonView.class)        
	private Boolean typePardefaut;
    @JsonView(CommonView.class)        
    private Boolean active;

    public boolean isActive() {
        return active;
    }
    
	public void setType(String type) {
		if(type != null) {
			this.type = type.toUpperCase();
		}
	}
}
