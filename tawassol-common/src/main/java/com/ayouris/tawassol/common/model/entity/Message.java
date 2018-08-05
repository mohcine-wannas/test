package com.ayouris.tawassol.common.model.entity;

import com.ayouris.tawassol.admin.model.entity.User;
import com.ayouris.tawassol.common.enums.MessageDestinationType;
import com.ayouris.tawassol.common.model.entity.generic.RefEntity;
import com.ayouris.tawassol.common.model.enums.MessageStatus;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Setter
@Entity
@Table(name = "messages", schema = "tawassol")
public class Message extends RefEntity {

    private static final long serialVersionUID = 6589657722921185470L;

    private User sender;
    private List<AffectationMessageUser> recipients;
    private List<AffectationMessageClasse> classes;
    private List<AffectationMessageNiveau> niveaux;
    private List<AffectationMessageUnite> unites;
    private Unite unite;

    private String message;
    private String attachment;
    @Deprecated //use messageStatus instead
    private Boolean validated;
    private LocalDate forDate;
    private MessageDestinationType messageDestinationType;
    private MessageStatus messageStatus;

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

    @ManyToOne
    @JoinColumn(name = "unite_id", foreignKey = @ForeignKey(name = "FK_MESS_UNITE_ID"))
    public Unite getUnite() {
        return unite;
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

    @Enumerated(EnumType.STRING)
    public MessageDestinationType getMessageDestinationType() {
        return messageDestinationType;
    }
    @Enumerated(EnumType.STRING)
    public MessageStatus getMessageStatus() {
        return messageStatus;
    }
}
