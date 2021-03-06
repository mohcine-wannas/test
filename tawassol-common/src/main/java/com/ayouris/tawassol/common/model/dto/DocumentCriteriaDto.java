package com.ayouris.tawassol.common.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;
import com.ayouris.tawassol.common.model.entity.Adherent;
import com.ayouris.tawassol.common.model.entity.CategorieDocument;
import com.ayouris.tawassol.common.model.helper.bean.EntityIdResolver;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", resolver = EntityIdResolver.class, scope = DocumentCriteriaDto.class)
public class DocumentCriteriaDto implements Serializable {

    private Long id;

    /**
     * categorieDocument
     */
    @JsonIdentityReference(alwaysAsId = true)
    private Adherent adherent;

    /**
     * categorieDocument
     */
    @JsonIdentityReference(alwaysAsId = true)
    private CategorieDocument categorieDocument;

    /**
     * Description
     */
    private String description;

    /**
     * Date
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateDebut;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateFin;

}