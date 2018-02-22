package com.ayouris.tawassol.common.model.bean;

import com.google.common.reflect.TypeToken;
import lombok.Getter;
import lombok.Setter;
import com.ayouris.tawassol.common.model.entity.Banque;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by YounesM on 05/05/2017.
 */

@Setter
@Getter
public class BanqueBean implements Serializable{

    private static final long serialVersionUID = -5029569008726313519L;

    public static final Type LIST_BEAN_TYPE = new TypeToken<ArrayList<BanqueBean>>(){}
            .getType();

    public static final Type LIST_ENTITY_TYPE = new TypeToken<ArrayList<Banque>>(){}
            .getType();

    private Long id;
    private String libelle;
    private String abreviation;
    private String adresse;
    private Boolean active;

    public boolean isActive() {
        return active;
    }

    public String getLibelle() {
        return libelle;
    }

    public String getAbreviation() {
        return abreviation;
    }
}
