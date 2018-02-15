package com.ayouris.tawassol.common.model.bean;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.common.reflect.TypeToken;

import lombok.Getter;
import lombok.Setter;
import com.ayouris.tawassol.common.model.entity.Transporteur;

/**
 * 
 * @author m.wannas
 *
 */

@Setter
@Getter
public class TransporteurBean implements Serializable{


    /**
	 * 
	 */
	private static final long serialVersionUID = -8554299817112491219L;

	@SuppressWarnings("serial")
	public static final Type LIST_BEAN_TYPE = new TypeToken<ArrayList<TransporteurBean>>(){}
            .getType();

    @SuppressWarnings("serial")
	public static final Type LIST_ENTITY_TYPE = new TypeToken<ArrayList<Transporteur>>(){}
            .getType();

    private Long id;
	private String libelle;
	private String abreviation;
    private Boolean active;

    public boolean isActive() {
        return active;
    }

}
