package ma.salamgaz.gwic.common.model.base;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Classe mère abstraite de tous les Objets métier.
 *
 * @author JAF
 * @version 1.2
 */
@MappedSuperclass
public class BusinessObject implements Serializable {

    /**
     * la cle
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "default_gen")
    protected Long id;

    /**
     * champ de selection
     */
    @Transient
    protected boolean selected = false;

    /**
     * Constructeur par défaut.
     */
    public BusinessObject() {
    }

    public BusinessObject(Long id) {
        this.id = id;
    }

    /**
     * Méthode de test de l'égalité entre deux objets métier. Ils sont
     * identiques si leurs clés primaires sont identiques et qu'ils sont de la
     * mème classe (plusieurs objets peuvent avoir la mème clé primaire).
     */
    @Override
    public boolean equals(Object object) {
        if (this.id != null && object instanceof BusinessObject) {
            BusinessObject businessObject = (BusinessObject) object;
            if (this.id.equals(businessObject.getId()))
                return true;
            else
                return false;
        }
        return false;

    }

    /**
     * Méthode hashCode.
     *
     * @see Object#hashCode()
     */
    public int hashCode() {
        Serializable pk = id;
        if (pk == null) {
            return 0;
        }
        return pk.toString().hashCode();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String toString() {
        return this.getId() != null ? this.getId().toString() : null;
    }

}
