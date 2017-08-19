package com.ayouris.tawassol.common.model.bean;

import com.fasterxml.jackson.annotation.JsonView;
import com.google.common.reflect.TypeToken;
import lombok.Getter;
import lombok.Setter;
import com.ayouris.tawassol.common.model.entity.Delegation;
import com.ayouris.tawassol.common.model.enums.Objet;
import com.ayouris.tawassol.common.view.CommonView;
import com.ayouris.tawassol.common.view.ListView;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Issmahane EL BAZ on 13/07/2017.
 */
@Setter
@Getter
public class DelegationBean implements Serializable {

    private static final long serialVersionUID = -5029569008476313519L;

    @SuppressWarnings("serial")
    public static final Type LIST_BEAN_TYPE = new TypeToken<ArrayList<DelegationBean>>(){}
            .getType();

    @SuppressWarnings("serial")
    public static final Type LIST_ENTITY_TYPE = new TypeToken<ArrayList<Delegation>>(){}
            .getType();
    @JsonView(CommonView.class)
    private Long id;

    @JsonView(CommonView.class)
    private Objet objet;

    @JsonView(CommonView.class)
    private UserDetailsBean user;

    @JsonView(CommonView.class)
    private UserDetailsBean delegator;
    @JsonView(CommonView.class)
    private Date du;

    @JsonView(CommonView.class)
    private Date au;

    @JsonView(ListView.class)
    private String userUsername;

}