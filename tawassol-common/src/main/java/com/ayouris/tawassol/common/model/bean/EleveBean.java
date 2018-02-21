package com.ayouris.tawassol.common.model.bean;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;

import com.ayouris.tawassol.common.model.entity.Eleve;
import com.google.common.reflect.TypeToken;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @author m.wannas
 *
 */

@Setter
@Getter
public class EleveBean implements Serializable{

    private static final long serialVersionUID = -5029569008726313519L;

    @SuppressWarnings("serial")
	public static final Type LIST_BEAN_TYPE = new TypeToken<ArrayList<EleveBean>>(){}
            .getType();

    @SuppressWarnings("serial")
	public static final Type LIST_ENTITY_TYPE = new TypeToken<ArrayList<Eleve>>(){}
            .getType();

    private Long id;
    private String lastname;
    private String firstname;
    private String phoneNumber = "";
    private String email = "";
    private String sexe;
	private LocalDate dateNaissance;
	private String codeMassar;
	private String observation;
}
