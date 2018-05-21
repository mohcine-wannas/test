package com.ayouris.tawassol.common.model.bean;

import com.ayouris.tawassol.common.model.entity.AffectationNiveauClasseProf;
import com.google.common.reflect.TypeToken;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class AffectationNiveauClasseProfBean implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -1229946509233692921L;

    @SuppressWarnings("serial")
    public static final Type LIST_BEAN_TYPE = new TypeToken<ArrayList<AffectationNiveauClasseProfBean>>() {
    }.getType();

    @SuppressWarnings("serial")
    public static final Type LIST_ENTITY_TYPE = new TypeToken<ArrayList<AffectationNiveauClasseProf>>() {
    }.getType();

    private Long id;
    private NiveauBean niveau;
    private ClasseBean classe;
    private List<ClasseBean> classes;

}
