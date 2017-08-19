package com.ayouris.tawassol.common.model.bean;

import com.fasterxml.jackson.annotation.JsonView;
import com.google.common.reflect.TypeToken;
import lombok.Getter;
import lombok.Setter;

import com.ayouris.tawassol.common.model.entity.Profil;
import com.ayouris.tawassol.common.view.CommonView;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by Issmahane EL BAZ on 17/07/2017.
 */
@Setter
@Getter
public class ProfilBean implements Serializable {

    private static final long serialVersionUID = -5029569008726313519L;

    @SuppressWarnings("serial")
    public static final Type LIST_BEAN_TYPE = new TypeToken<ArrayList<ProfilBean>>(){}
            .getType();

    @SuppressWarnings("serial")
    public static final Type LIST_ENTITY_TYPE = new TypeToken<ArrayList<Profil>>(){}
            .getType();

    @JsonView(CommonView.class)
    private Long id;
    @JsonView(CommonView.class)
    private Long code;
    @JsonView(CommonView.class)
    private String libelle;

    public void setId(Long id) {
        this.id = id;
    }
}

