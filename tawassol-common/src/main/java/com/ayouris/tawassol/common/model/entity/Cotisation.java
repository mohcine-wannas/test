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
import java.util.Date;

/**
 * Cotisation
 *
 * @author JAF
 * @version 1.2
 */

@Entity
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", resolver = EntityIdResolver.class, scope = Cotisation.class)
@SequenceGenerator(initialValue = 1, name = "default_gen", sequenceName = "cotisation_seq")
public class Cotisation extends BusinessObject {

    /**
     * Fields.
     */

    /**
     * Date
     */
    @Type(type = "timestamp")
    private Date date;

    /**
     * Année
     */
    @Column(length = 255)
    private String annee;

    /**
     * Montant de cotiastion
     */
    @Type(type = "double")
    private Double montant;

    /**
     * Club
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIdentityReference(alwaysAsId = true)
    private Club club;
}