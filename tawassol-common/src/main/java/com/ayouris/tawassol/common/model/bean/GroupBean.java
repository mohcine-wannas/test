package com.ayouris.tawassol.common.model.bean;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.common.reflect.TypeToken;

import lombok.Getter;
import lombok.Setter;
import com.ayouris.tawassol.common.model.entity.Group;

@Getter
@Setter
public class GroupBean implements Serializable {
	

    /**
	 * 
	 */
	private static final long serialVersionUID = 8551503426280504659L;

	@SuppressWarnings("serial")
	public static final Type LIST_BEAN_TYPE = new TypeToken<ArrayList<GroupBean>>(){}
            .getType();

    @SuppressWarnings("serial")
	public static final Type LIST_ENTITY_TYPE = new TypeToken<ArrayList<Group>>(){}
            .getType();

    private Long id;
    private String libelle;
}
