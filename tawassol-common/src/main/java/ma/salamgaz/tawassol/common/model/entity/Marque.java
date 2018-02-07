package ma.salamgaz.tawassol.common.model.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Setter;
import ma.salamgaz.tawassol.common.model.entity.generic.RefEntity;

/**
 * 
 * @author m.wannas
 *
 */
@Setter
@Entity
@Table(name = "marque", schema = "tawassol")
public class Marque extends RefEntity {



	/**
	 * 
	 */
	private static final long serialVersionUID = 2024894095679876415L;
	
	@NotNull
	private String libelle;

	public String getLibelle() {
		return libelle;
	}
}
