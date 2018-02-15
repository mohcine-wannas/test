package ma.salamgaz.tawassol.common.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Setter;
import ma.salamgaz.tawassol.common.model.entity.generic.RefEntity;

/**
 * 
 * @author m.wannas
 *
 */
@Setter
@Entity
@Table(name = "cycle", schema = "tawassol")
public class Cycle extends RefEntity {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7795867047149834497L;
	
	@Column(unique=true)
	private String libelle;
	private Integer nombreDesNiveaux;
	
	public String getLibelle() {
		return libelle;
	}
	
	

	public Integer getNombreDesNiveaux() {
		return nombreDesNiveaux;
	}

 
}
