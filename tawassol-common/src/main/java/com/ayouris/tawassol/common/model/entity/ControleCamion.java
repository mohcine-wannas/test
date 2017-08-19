package com.ayouris.tawassol.common.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Setter;
import com.ayouris.tawassol.common.model.entity.generic.RefEntity;
import com.ayouris.tawassol.common.model.enums.TypeChargement;

/**
 * 
 * @author m.wannas	
 *
 */
@Setter
@Entity
@Table(name = "controle_camion", schema = "tawassol")
public class ControleCamion extends RefEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6176024974860812684L;

	@NotNull
	private TypeChargement typeChargement;
	@NotNull
    private String Controle;

	@Column(unique = true)
	public String getControle(){
		return Controle;
	}
	
	@Enumerated(EnumType.STRING)
	public TypeChargement getTypeChargement() {
		return typeChargement;
	}

    
}
