package com.ayouris.tawassol.common.model.entity.generic;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import lombok.Setter;

@Setter
@MappedSuperclass
public abstract class RefEntity extends AuditEntity {


	private Boolean active;
	private Integer order;

	public Boolean getActive() {
		return active;
	}

	@Column(name="ordre")
	public Integer getOrder() {
		return order;
	}

}
