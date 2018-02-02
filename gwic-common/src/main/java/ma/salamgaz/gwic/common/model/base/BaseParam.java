package ma.salamgaz.gwic.common.model.base;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * Classe des objets parametrables qui permet de facilement gerer l'activite d'un ensemble d'objets ainsi que leur ordre d'affichage)
 *
 * @author chamakh
 * @version 1.1
 */

@Getter
@Setter
@MappedSuperclass
public abstract class BaseParam extends BaseEntity {
    private static final long serialVersionUID = -3904774061416225333L;

    // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
    // ||||||||||||||||||||||||||||||||||||| Attributs a persister dans base de donnees ||||||||||||||||||||||||||||||||||||||||//
    // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

    /**
     * Indique si l'objet est actif ou non
     */
    @Column(nullable = false)
    protected Boolean actif;

    /**
     * L'ordre d'affichage personnalise
     */
    @Column(nullable = false)
    protected Integer ordre;

}