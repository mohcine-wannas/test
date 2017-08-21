package com.ayouris.tawassol.common.model.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;
import com.ayouris.tawassol.common.model.base.BusinessObject;
import com.ayouris.tawassol.common.model.helper.bean.EntityIdResolver;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;

/**
 * club
 *
 * @author JAF
 * @version 1.2
 */

@Entity
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", resolver = EntityIdResolver.class, scope = Club.class)
@SequenceGenerator(initialValue = 1, name = "default_gen", sequenceName = "club_seq")
public class Club extends BusinessObject {

    /**
     * Fields.
     */

    /**
     * Nom
     */
    @Column(length = 255)
    private String libelle;

    /**
     * Téléphone
     */
    @Column(length = 255)
    private String telephone;

    /**
     * Email
     */
    @Column(length = 255)
    private String email;

    /**
     * Fax
     */
    @Column(length = 255)
    private String fax;

    /**
     * Site web
     */
    @Column(length = 255)
    private String siteweb;

    /**
     * Affilier
     */
    @Column(columnDefinition = "boolean default true")
    @Type(type = "boolean")
    private boolean affilier = true;

    /**
     * Nom agent
     */
    @Column(length = 255)
    private String nomgerant;

    /**
     * Prénom agent
     */
    @Column(length = 255)
    private String prenomgerant;

    /**
     * Adresse
     */
    @Type(type = "text")
    private String adresse;

    /**
     * club
     */
    @OneToMany(mappedBy = "club", cascade = {CascadeType.REMOVE, CascadeType.PERSIST}, fetch = FetchType.LAZY)
    @JsonIdentityReference(alwaysAsId = false)
    private List<Cotisation> cotisationList;
}