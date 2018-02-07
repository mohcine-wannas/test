package ma.salamgaz.tawassol.common.model.entity.generic;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import lombok.Setter;

@Setter
@MappedSuperclass
public abstract class RefEntity extends AuditEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4522207066164161836L;

	private String code;
	private Boolean active;

	@Column(unique = true)
	public String getCode() {
		return code;
	}

	public Boolean getActive() {
		return active;
	}

}
