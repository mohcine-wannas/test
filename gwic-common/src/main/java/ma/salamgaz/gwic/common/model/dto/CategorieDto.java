package ma.salamgaz.gwic.common.model.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class CategorieDto implements Serializable {

    /** la cle */
    private Long id;

    /** Libellé */
    private String libelle;

    /** Description */
    private String description;
}