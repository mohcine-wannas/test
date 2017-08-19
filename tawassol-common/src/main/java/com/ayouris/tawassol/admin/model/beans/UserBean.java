package com.ayouris.tawassol.admin.model.beans;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonView;
import com.google.common.reflect.TypeToken;

import lombok.Getter;
import lombok.Setter;
import com.ayouris.tawassol.admin.model.entity.User;
import com.ayouris.tawassol.common.model.bean.ProfilBean;
import com.ayouris.tawassol.common.model.entity.Site;
import com.ayouris.tawassol.common.model.enumeration.ProfilType;
import com.ayouris.tawassol.common.view.CommonView;

/**
 * Created by Issmahane EL BAZ on 17/07/2017.
 */
@Setter
@Getter
public class UserBean implements Serializable {
    private static final long serialVersionUID = -7419089138964718133L;

    @SuppressWarnings("serial")
    public static final Type LIST_BEAN_TYPE = new TypeToken<ArrayList<UserBean>>() {
    }.getType();

    @SuppressWarnings("serial")
    public static final Type LIST_ENTITY_TYPE = new TypeToken<ArrayList<User>>() {
    }.getType();

    @JsonView(CommonView.class)
    private Long id;
    @JsonView(CommonView.class)
    private String matricule;
    @JsonView(CommonView.class)
    private Boolean enabled;
    @JsonView(CommonView.class)
    private Boolean active;
    @JsonView(CommonView.class)
    private String username;
    @JsonView(CommonView.class)
    private String firstname;
    @JsonView(CommonView.class)
    private String lastname;
    @JsonView(CommonView.class)
    private String email;
    @JsonView(CommonView.class)
    private Site site;
    @JsonView(CommonView.class)
    private ProfilType profilType;
    @JsonView(CommonView.class)
    private ProfilBean profil;
    @JsonView(CommonView.class)
    private String profilLibelle;
    @JsonView(CommonView.class)
    private String siteLibelle;
    @JsonView(CommonView.class)
    private String password;


    public void setProfil(ProfilBean profil) {
        this.profil = profil;
    }
}
