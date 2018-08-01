package com.ayouris.tawassol.common.model.bean;

import com.ayouris.tawassol.common.model.entity.Professeur;
import com.google.common.reflect.TypeToken;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class ProfesseurBean implements Serializable {

    private static final long serialVersionUID = -5029569008726313519L;

    @SuppressWarnings("serial")
    public static final Type LIST_BEAN_TYPE = new TypeToken<ArrayList<ProfesseurBean>>() {
    }
            .getType();

    @SuppressWarnings("serial")
    public static final Type LIST_ENTITY_TYPE = new TypeToken<ArrayList<Professeur>>() {
    }
            .getType();

    private Long id;
    private String schoolCode;
    private String lastname;
    private String firstname;
    private String phoneNumber;
    private String password;
    private String passwordConfirm;
    private Boolean enabled;
    private Boolean autoSendMessage;

    private CycleBean cycle;

    private List<AffectationUniteProfBean> affectationsUniteProf;

    private List<AffectationNiveauClasseProfBean> affectationsNiveauClasseProf;
}
