package com.ayouris.tawassol.common.model.base;

import lombok.Getter;
import lombok.Setter;
import com.ayouris.tawassol.common.model.helper.utils.EntityListener;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;


/**
 * La classe Super de toutes les entites
 *
 * @author chamakh
 * @version 1.2
 */
@Getter
@Setter
@EntityListeners(EntityListener.class)
@MappedSuperclass
public abstract class BaseEntity implements Persistable<Serializable> {
    private static final long serialVersionUID = 8454846536186396036L;

    // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
    // ||||||||||||||||||||||||||||||||||||| Attributs a persister dans base de donnees ||||||||||||||||||||||||||||||||||||||||//
    // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
    /**
     * transitoire utiliser pour identifier, de maniere unique, un objet non encore persiste dans la base de donnees
     */
    @Transient
    private final String transientUuid;
    /**
     * Cle technique generee automatiquement pour toutes les classes modeles
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;
    /**
     * transitoire utiliser dans certains traitement utile pour retenir l'objet encours
     */
    @Transient
    protected boolean selected;
    /**
     * Date de creation d'un objet
     */
    private LocalDateTime createdDate;
    /**
     * Date de la derniere modification d'un objet
     */
    private LocalDateTime lastModifiedDate;

    // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
    // ||||||||||||||||||||||||||||||||||||||||||||||| Attributs transitoires  ||||||||||||||||||||||||||||||||||||||||||||||||||//
    // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
    /**
     * Utilisateur qui a cree l'objet
     */
    private Long createdById;

    /**
     * Utilisateur dernier qui a modifie l'objet
     */
    private Long lastModifiedById;

    // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
    // ||||||||||||||||||||||||||||||||||||||||||||||||||| Constructeur ||||||||||||||||||||||||||||||||||||||||||||||||||||||||//
    // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
    public BaseEntity() {

        super();
        transientUuid = UUID.randomUUID().toString();
    }

    public BaseEntity(Long id) {

        super();
        transientUuid = UUID.randomUUID().toString();
        this.id = id;
    }

    // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
    // |||||||||||||||||||||||||||||||||||||||||||||||||| Methodes utiles  |||||||||||||||||||||||||||||||||||||||||||||||||||||//
    // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

    /**
     * Text generique a redefinir dans chaque classe fille Pour afficher le libelle de l'objet
     *
     * @return un String
     */
    @Transient
    public abstract String getDisplayText();

    /**
     * Retourne vrai si l'objet encours n'est pas encore persiste dans la base
     *
     * @return vrai ou faux
     */
    @Override
    public boolean isNew() {

        return null == getId();
    }

    /**
     * Permet de redefinir la methode toString pour afficher le libelle de l'objet en se basant sur le methode getDisplayText()
     */
    @Override
    public String toString() {

        return getDisplayText();
    }

    // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//
    // ||||||||||||||||||||||||||||||||||||||||||||| Methodes equals & hashCode ||||||||||||||||||||||||||||||||||||||||||||||||//
    // +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++//

    /**
     * Permet de generer une valeur (32bit) hachage
     */
    @Override
    public int hashCode() {

        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        return result;
    }

    /**
     * Permet de comparer la similarite de deux objets
     */
    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        BaseEntity other = (BaseEntity) obj;
        if (getId() == null) {
            if (other.getId() != null) {
                return false;
            } else {
                return (getTransientUuid().equals(other.getTransientUuid()));
            }
        } else if (!getId().equals(other.getId()))
            return false;
        return true;
    }
}