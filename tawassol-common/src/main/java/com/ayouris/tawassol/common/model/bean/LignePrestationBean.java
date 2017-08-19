package com.ayouris.tawassol.common.model.bean;

import com.fasterxml.jackson.annotation.JsonView;
import com.google.common.reflect.TypeToken;
import lombok.Getter;
import lombok.Setter;
import com.ayouris.tawassol.common.model.entity.LignePrestation;
import com.ayouris.tawassol.common.view.CommonView;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by Issmahane EL BAZ on 21/06/2017.
 */

@Setter
@Getter
public class LignePrestationBean implements Serializable {

    private static final long serialVersionUID = 147777822L;


    public static final Type LIST_BEAN_TYPE = new TypeToken<ArrayList<LignePrestationBean>>() {
    }.getType();

    public static final Type LIST_ENTITY_TYPE = new TypeToken<ArrayList<LignePrestation>>() {
    }.getType();

    @JsonView(CommonView.class)
    private Integer qte;

    @JsonView(CommonView.class)
    private PrestationBean prestation;


}
