package com.ayouris.tawassol.common.model.bean;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;
import com.ayouris.tawassol.admin.model.beans.SiteBean;
import com.ayouris.tawassol.admin.model.entity.User;
import com.ayouris.tawassol.common.model.entity.CircuitDerogation;

import java.io.Serializable;
import java.util.ArrayList;
import java.lang.reflect.Type;
import com.google.common.reflect.TypeToken;
import com.ayouris.tawassol.common.model.entity.Site;
import com.ayouris.tawassol.common.model.enums.Objet;
import com.ayouris.tawassol.common.view.CommonView;
import com.ayouris.tawassol.common.view.ListView;

/**
 * Created by Issmahane EL BAZ on 11/07/2017.
 */
@Setter
@Getter
public class CircuitDerogationBean implements Serializable {

    private static final long serialVersionUID = -502956987426313519L;

    @SuppressWarnings("serial")
    public static final Type LIST_BEAN_TYPE = new TypeToken<ArrayList<CircuitDerogationBean>>(){}
            .getType();

    @SuppressWarnings("serial")
    public static final Type LIST_ENTITY_TYPE = new TypeToken<ArrayList<CircuitDerogation>>(){}
            .getType();

    @JsonView(CommonView.class)
    private Long id;

    @JsonView(CommonView.class)
    private SiteBean site;

    @JsonView(CommonView.class)
    private Objet objet ;

    @JsonView(CommonView.class)
    private UserDetailsBean user;

    @JsonView(CommonView.class)
    private Boolean active;

    @JsonView(ListView.class)
    private String siteLibelle;

    @JsonView(ListView.class)
    private String userUsername;
}
