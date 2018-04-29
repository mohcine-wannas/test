package com.ayouris.tawassol.common.model.entity;

import com.ayouris.tawassol.admin.model.entity.User;
import com.ayouris.tawassol.common.model.entity.generic.AuditEntity;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Setter
@Entity
@Table(name = "notification", schema = "tawassol")
public class Notification extends AuditEntity {

    private String title;
    private String body;
    private String target;
    private Boolean isSeen = false;
    @NotNull
    private User user;

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public String getTarget() {
        return target;
    }

    public Boolean getIsSeen() {
        return isSeen;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "FK_NOTIF_USER_ID"))
    public User getUser() {
        return user;
    }
}
