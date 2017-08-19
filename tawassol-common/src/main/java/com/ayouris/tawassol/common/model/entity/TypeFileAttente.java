package com.ayouris.tawassol.common.model.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import lombok.Setter;
import com.ayouris.tawassol.common.model.entity.generic.RefEntity;
import com.ayouris.tawassol.common.model.enums.CategorieTypeFileAttente;

/**
 * 
 * @author m.wannas
 *
 */
@Setter
@Entity
@Table(name = "type_file_attente", schema = "tawassol",  uniqueConstraints=
@UniqueConstraint(columnNames={"type", "categorie"}))
public class TypeFileAttente extends RefEntity {


	/**
	 * 
	 */
	private static final long serialVersionUID = 8022486292416902877L;
	@NotNull
	private String type;
	@NotNull
	private CategorieTypeFileAttente categorie;
	private Boolean typePardefaut;
	
	@Enumerated(EnumType.STRING)
	public CategorieTypeFileAttente getCategorie() {
		return categorie;
	}
	
	public String getType() {
		return type;
	}

	public Boolean getTypePardefaut() {
		return typePardefaut;
	}
    
}
