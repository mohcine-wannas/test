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
@Table(name = "fournisseur", schema = "gwic")
public class Fournisseur extends RefEntity {


	/**
	 * 
	 */
	private static final long serialVersionUID = -3186647880782092612L;
	
	public String getLibelle() {
		return libelle;
	}
	public String getAbreviation() {
		return abreviation;
	}
	@NotNull
	private String libelle;
	@NotNull
	private String abreviation;
    

    
}
