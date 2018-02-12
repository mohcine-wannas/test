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
@Table(name = "pays", schema = "tawassol")
public class Pays extends RefEntity {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7795867047149834497L;
	
	@Column(unique=true)
	private String name;
	
	@Column(unique=true)
	private String code;

	public String getName() {
		return name;
	}

	public String getCode() {
		return code;
	}
	
}
