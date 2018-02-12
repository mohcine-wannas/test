package ma.salamgaz.tawassol.common.model.entity.generic;

import javax.persistence.MappedSuperclass;

import lombok.Setter;

@Setter
@MappedSuperclass
public abstract class RefEntity extends AuditEntity {


	private Boolean active;

	public Boolean getActive() {
		return active;
	}

}
