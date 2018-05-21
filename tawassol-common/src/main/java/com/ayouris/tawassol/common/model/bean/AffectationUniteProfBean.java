package com.ayouris.tawassol.common.model.bean;

import com.ayouris.tawassol.common.model.entity.AffectationUniteProf;
import com.google.common.reflect.TypeToken;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;

@Setter
@Getter
public class AffectationUniteProfBean implements Serializable {

    private static final long serialVersionUID = 5640825597922503307L;

    @SuppressWarnings("serial")
    public static final Type LIST_BEAN_TYPE = new TypeToken<ArrayList<AffectationUniteProfBean>>() {
    }.getType();

    @SuppressWarnings("serial")
    public static final Type LIST_ENTITY_TYPE = new TypeToken<ArrayList<AffectationUniteProf>>() {
    }.getType();

    private Long id;
    private UniteBean unite;
    private Boolean enabled;

}