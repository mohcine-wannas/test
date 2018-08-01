package com.ayouris.tawassol.common.model.bean;

import com.ayouris.tawassol.common.model.entity.CategorieProf;
import com.google.common.reflect.TypeToken;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;

@Setter
@Getter
public class CategorieProfBean implements Serializable {

    private static final long serialVersionUID = -5029569008726313519L;

    @SuppressWarnings("serial")
    public static final Type LIST_BEAN_TYPE = new TypeToken<ArrayList<CategorieProfBean>>() {
    }
            .getType();

    @SuppressWarnings("serial")
    public static final Type LIST_ENTITY_TYPE = new TypeToken<ArrayList<CategorieProf>>() {
    }
            .getType();

    private String libelle;
    private Long id;
}
