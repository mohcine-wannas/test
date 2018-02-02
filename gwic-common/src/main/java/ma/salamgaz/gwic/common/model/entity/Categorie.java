package ma.salamgaz.gwic.common.model.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;
import ma.salamgaz.gwic.common.model.base.BusinessObject;
import ma.salamgaz.gwic.common.model.helper.bean.EntityIdResolver;

import javax.persistence.*;


@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "id", resolver = EntityIdResolver.class, scope = Categorie.class)
@SequenceGenerator(initialValue = 1, name = "default_gen", sequenceName = "categorie_seq",allocationSize = 1)
public class Categorie extends BusinessObject{

    /** Libellé */
    private String libelle;

    /** Description */
    private String description;

}