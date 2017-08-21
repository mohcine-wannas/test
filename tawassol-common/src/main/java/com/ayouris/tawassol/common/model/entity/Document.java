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
 * Télécharger un document
 *
 * @author JAF
 * @version 1.2
 */

@Entity
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", resolver = EntityIdResolver.class, scope = Document.class)
@SequenceGenerator(initialValue = 1, name = "default_gen", sequenceName = "document_seq")
public class Document extends BusinessObject {

    /**
     * Fichier
     */
    @Type(type = "text")
    private String fichier;

    /**
     * Description
     */
    @Type(type = "text")
    private String description;

    /**
     * Date
     */
    @Type(type = "timestamp")
    private Date date;

    /**
     * categorieDocument
     */
    @ManyToOne
    @JsonIdentityReference(alwaysAsId = true)
    private CategorieDocument categorieDocument;

    /**
     * adherent
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIdentityReference(alwaysAsId = true)
    private Adherent adherent;

}