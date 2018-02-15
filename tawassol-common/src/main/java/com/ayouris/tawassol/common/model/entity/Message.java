package com.ayouris.tawassol.common.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.ayouris.tawassol.admin.model.entity.User;
import com.ayouris.tawassol.common.model.entity.generic.BaseEntity;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Setter
@Entity
@Table(name = "message", schema = "tawassol")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "message_type", discriminatorType = DiscriminatorType.STRING)
public abstract class Message extends BaseEntity {

    private static final long serialVersionUID = 6589657722921185470L;

    private User user;
    private Date date;
    private String comment;
    private String author;
    private String type;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = User.class)
    @JoinColumn(name = "user_id", nullable = false)
    public User getUser() {
        return user;
    }

    @Column(name = "message_date", nullable = false)
    public Date getDate() {
        return date;
    }

    @Column(name = "description", nullable = false)
    public String getComment() {
        return comment;
    }

    @Transient
    public String getAuthor() {
        return author;
    }

    @Transient
    @JsonIgnore
    public String getType() {
        return type;
    }

}
