package ma.salamgaz.tawassol.common.model.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.Setter;
import ma.salamgaz.tawassol.common.model.base.BusinessObject;
import ma.salamgaz.tawassol.common.model.helper.bean.EntityIdResolver;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.SequenceGenerator;

/**
 * Catégorie sport
 *
 * @author JAF
 * @version 1.2
 */


@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", resolver = EntityIdResolver.class, scope = CategorieSport.class)
@SequenceGenerator(initialValue = 1, name = "default_gen", sequenceName = "categoriesport_seq")
public class CategorieSport extends BusinessObject {

    /**
     * Libellé
     */
    private String libelle;

    /**
     * Description
     */
    @Type(type = "text")
    private String description;

}