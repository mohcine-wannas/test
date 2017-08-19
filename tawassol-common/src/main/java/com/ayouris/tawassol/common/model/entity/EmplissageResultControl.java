package com.ayouris.tawassol.common.model.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Setter;
import com.ayouris.tawassol.common.model.entity.generic.AuditEntity;


@Setter
@Entity
@Table(name = "emplissage_result_control", schema = "tawassol")
public class EmplissageResultControl extends AuditEntity {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 4126128729820727632L;
	
	private Long resultat;
	
	@NotNull
	private Emplissage emplissage;

	@NotNull
	private EmplissageControlLigne emplissageControlLigne;
	
	@NotNull
	private EmplissageControlColonne emplissageControlColonne;
	
	public Long getResultat() {
		return resultat;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "emplissage_id", nullable = false, foreignKey = @ForeignKey(name = "FK_RESULT_EMP_ID"))
	public Emplissage getEmplissage() {
		return emplissage;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Controlligne_id", nullable = false, foreignKey = @ForeignKey(name = "FK_RESULt_COLUMN_ID"))
	public EmplissageControlLigne getEmplissageControlLigne() {
		return emplissageControlLigne;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Controlcolonne_id", nullable = false, foreignKey = @ForeignKey(name = "FK_RESULt_LINE_ID"))
	public EmplissageControlColonne getEmplissageControlColonne() {
		return emplissageControlColonne;
	}

}
