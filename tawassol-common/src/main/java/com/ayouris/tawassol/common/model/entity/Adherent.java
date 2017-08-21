package com.ayouris.tawassol.common.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;
import com.ayouris.tawassol.common.model.base.BusinessObject;
import com.ayouris.tawassol.common.model.enumeration.ENUM_SEXE;
import com.ayouris.tawassol.common.model.enumeration.ENUM_SITUATION_FAMILIALE;
import com.ayouris.tawassol.common.model.helper.bean.EntityIdResolver;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", resolver = EntityIdResolver.class, scope = Adherent.class)
@SequenceGenerator(name = "default_gen", sequenceName = "adherent_seq")
public class Adherent extends BusinessObject {

     /**
     * Nom d'adhérent
     */
    private String nom;

    /**
     * Prénom d'adhérent
     */
    private String prenom;

    /**
     * CIN
     */
    private String cin;

    /**
     * Date de naissance
     */
    @Type(type = "date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateNaissance;

    /**
     * Sexe
     */
    @Enumerated(EnumType.ORDINAL)
    @Column(columnDefinition = "integer default 0")
    private ENUM_SEXE sexe;

    /**
     * Téléphone
     */
    private String tel;

    /**
     * Email
     */
    private String email;

    /**
     * Adresse
     */
    @Type(type = "text")
    private String adresse;

    /**
     * categorie
     */
    @ManyToOne
    @JsonIdentityReference(alwaysAsId = false)
    private Categorie categorie;
    /**
     * Nationalité
     */
    private String nationalite;

    /**
     * Situation familiale
     */
    @Enumerated(EnumType.ORDINAL)
    @Column(columnDefinition = "integer default 0")
    private ENUM_SITUATION_FAMILIALE situationFamiliale;

    /**
     * categorieSports
     */
    @ManyToMany(targetEntity = CategorieSport.class, fetch = FetchType.LAZY)
    //@JoinTable(joinColumns = {@JoinColumn(name = "adherent_id")}, inverseJoinColumns = {@JoinColumn(name = "categoriesport_id")})
    @JsonIdentityReference(alwaysAsId = true)
    private List<CategorieSport> categorieSportList;
}