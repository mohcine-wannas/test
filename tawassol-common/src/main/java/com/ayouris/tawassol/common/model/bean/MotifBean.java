package com.ayouris.tawassol.common.model.bean;

import com.fasterxml.jackson.annotation.JsonView;
import com.google.common.reflect.TypeToken;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;

import com.ayouris.tawassol.common.model.entity.Motif;
import com.ayouris.tawassol.common.view.CommonView;

/**
 * Created by Issmahane EL BAZ on 21/06/2017.
 */

@Setter
@Getter
public class MotifBean implements Serializable {

    private static final long serialVersionUID = -5029569008726313519L;

    @SuppressWarnings("serial")
    public static final Type LIST_BEAN_TYPE = new TypeToken<ArrayList<MotifBean>>(){}
            .getType();

    @SuppressWarnings("serial")
    public static final Type LIST_ENTITY_TYPE = new TypeToken<ArrayList<Motif>>(){}
            .getType();

    @JsonView(CommonView.class)
    private Long id;
    @JsonView(CommonView.class)
    private Long code;
    @JsonView(CommonView.class)
    private String libelle;

}
