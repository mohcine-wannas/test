package ma.salamgaz.gwic.common.model.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Setter;
import ma.salamgaz.gwic.common.model.entity.generic.RefEntity;

/**
 * 
 * @author m.wannas
 *
 */

@Setter
@Entity
@Table(name = "groupe", schema = "gwic")
public class Group extends RefEntity {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -4866657736663345867L;
	
	private String libelle;

	public String getLibelle() {
		return libelle;
	}

}
