package com.ayouris.tawassol.common.model.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;
import com.ayouris.tawassol.common.model.base.BusinessObject;
import com.ayouris.tawassol.common.model.helper.bean.EntityIdResolver;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;

/**
 * categorieDocument
 *
 * @author JAF
 * @version 1.2
 */

@Entity
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", resolver = EntityIdResolver.class, scope = CategorieDocument.class)
@SequenceGenerator(initialValue = 1, name = "default_gen", sequenceName = "categoriedocument_seq")
public class CategorieDocument extends BusinessObject {

    /**
     * Fields.
     */

    /**
     * Libellé
     */
    @Column(length = 255)
    private String libelle;

    /**
     * Description
     */
    @Type(type = "text")
    private String description;

}