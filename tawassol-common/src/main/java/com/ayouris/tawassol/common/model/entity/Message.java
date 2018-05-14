package com.ayouris.tawassol.common.model.entity;

import com.ayouris.tawassol.admin.model.entity.User;
import com.ayouris.tawassol.common.model.entity.generic.BaseEntity;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Setter
@Entity
@Table(name = "messages", schema = "tawassol")
public class Message extends BaseEntity {

    private static final long serialVersionUID = 6589657722921185470L;

    private User sender;
    private List<AffectationMessageUser> recipients;
    private List<AffectationMessageClasse> classes;
    private List<AffectationMessageNiveau> niveaux;
    private List<AffectationMessageUnite> unites;

    private String message;
    private String attachment;
    private Boolean validated;
    private LocalDate forDate;

    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false, foreignKey = @ForeignKey(name = "FK_MESSAGES_USER_ID"))
    public User getSender() {
        return sender;
    }


    @OneToMany(cascade = {CascadeType.MERGE,CascadeType.REMOVE}, fetch = FetchType.LAZY, orphanRemoval = true,
            mappedBy = "message")
    public List<AffectationMessageUser> getRecipients() {
        return recipients;

    }

    @OneToMany(cascade = {CascadeType.MERGE,CascadeType.REMOVE}, fetch = FetchType.LAZY, orphanRemoval = true,
            mappedBy = "message")
    public List<AffectationMessageClasse> getClasses() {
        return classes;
    }

    @OneToMany(cascade = {CascadeType.MERGE,CascadeType.REMOVE}, fetch = FetchType.LAZY, orphanRemoval = true,
            mappedBy = "message")
    public List<AffectationMessageNiveau> getNiveaux() {
        return niveaux;
    }

    @OneToMany(cascade = {CascadeType.MERGE,CascadeType.REMOVE}, fetch = FetchType.LAZY, orphanRemoval = true,
            mappedBy = "message")
    public List<AffectationMessageUnite> getUnites() {
        return unites;
    }

    public LocalDate getForDate() {
        return forDate;
    }

    public String getMessage() {
        return message;
    }


    public String getAttachment() {
        return attachment;
    }

    public Boolean getValidated() {
        return validated;
    }
}
