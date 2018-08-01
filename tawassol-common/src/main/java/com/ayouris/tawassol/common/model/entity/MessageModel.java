package com.ayouris.tawassol.common.model.entity;

import com.ayouris.tawassol.admin.model.entity.User;
import com.ayouris.tawassol.common.model.entity.generic.RefEntity;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Entity
@Table(name = "message_model", schema = "tawassol")
public class MessageModel extends RefEntity {

    private static final long serialVersionUID = 6589657722921185470L;

    private School school;
    private User user;
    private CategorieAdmin categorieAdmin;
    private CategorieProf categorieProf;

    private String titre;
    private Boolean fige;

    private String message;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = true, foreignKey = @ForeignKey(name = "FK_MESSAGE_MOD_USER_ID"))
    public User getUser() {
        return user;
    }

    @ManyToOne
    @JoinColumn(name = "school_id", nullable = true, foreignKey = @ForeignKey(name = "FK_MESSAGE_SCHOOL_ID"))
    public School getSchool() {
        return school;
    }

    @ManyToOne
    @JoinColumn(name = "categorie_admin_id", nullable = true, foreignKey = @ForeignKey(name = "FK_MSG_CATEGORY_ADMIN_ID"))
    public CategorieAdmin getCategorieAdmin() {
        return categorieAdmin;
    }

    @ManyToOne
    @JoinColumn(name = "categorie_prof_id", nullable = true, foreignKey = @ForeignKey(name = "FK_MSG_CATEGORY_PROF_ID"))
    public CategorieProf getCategorieProf() {
        return categorieProf;
    }

    public String getTitre() {
        return titre;
    }

    @Column(length = 10000)
    public String getMessage() {
        return message;
    }

    public Boolean getFige() {
        return fige;
    }
}
