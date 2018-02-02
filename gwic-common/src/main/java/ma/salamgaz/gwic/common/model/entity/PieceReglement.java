package ma.salamgaz.gwic.common.model.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Setter;
import ma.salamgaz.gwic.common.model.entity.generic.RefEntity;

/**
 * 
 * @author m.wannas
 *
 */
@Setter
@Entity
@Table(name = "pieceReglement", schema = "gwic")
public class PieceReglement extends RefEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3864949940098942641L;
	@NotNull
	private String libelle;


	public String getLibelle() {
		return libelle;
	}
}
