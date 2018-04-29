package com.ayouris.tawassol.common.model.bean;

import com.ayouris.tawassol.common.model.entity.AffectationUnite;
import com.google.common.reflect.TypeToken;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;

@Setter
@Getter
public class AffectationUniteBean implements Serializable {

    private static final long serialVersionUID = 5640825597922503307L;

    @SuppressWarnings("serial")
    public static final Type LIST_BEAN_TYPE = new TypeToken<ArrayList<AffectationUniteBean>>() {
    }.getType();

    @SuppressWarnings("serial")
    public static final Type LIST_ENTITY_TYPE = new TypeToken<ArrayList<AffectationUnite>>() {
    }.getType();

    private Long id;
    private UniteBean unite;
    private Boolean enabled;

}