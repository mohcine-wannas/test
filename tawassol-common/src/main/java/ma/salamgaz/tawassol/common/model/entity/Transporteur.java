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
@Table(name = "transporteur", schema = "tawassol")
public class Transporteur extends RefEntity {



	/**
	 * 
	 */
	private static final long serialVersionUID = -337095945802675989L;

	@NotNull
	private String libelle;
	@NotNull
	private String abreviation;

	public String getLibelle() {
		return libelle;
	}
	public String getAbreviation() {
		return abreviation;
	}
}
