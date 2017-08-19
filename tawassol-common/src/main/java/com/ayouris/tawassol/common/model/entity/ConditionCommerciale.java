package com.ayouris.tawassol.common.model.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cascade;

import lombok.Setter;
import com.ayouris.tawassol.common.model.entity.generic.AuditEntity;
import com.ayouris.tawassol.common.model.enums.Periodicite;

/**
 * 
 * @author m.wannas
 *
 */

@Setter
@Entity
@Table(name = "conditionCommerciale", schema = "tawassol")
public class ConditionCommerciale extends AuditEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 314500242883077404L;
	
	@NotNull
    private TypePieceReglement pieceReglement;
	@NotNull
    private Periodicite periodicite;
    private Set<Periode> periodes;
    private Boolean noPayment;

	public Boolean getNoPayment(){
		return noPayment;
	}

    @ManyToOne
	@JoinColumn(name = "piece_reglement_id", nullable = false, foreignKey = @ForeignKey(name = "FK_PIECE_REGLEMENT_PERIODE_ID")) 
	public TypePieceReglement getPieceReglement() {
		return pieceReglement;
	}
    
	@Enumerated(EnumType.STRING)
	public Periodicite getPeriodicite() {
		return periodicite;
	}
	
    @OneToMany(fetch = FetchType.EAGER,mappedBy = "conditionCommerciale")
    @Cascade(value={org.hibernate.annotations.CascadeType.ALL})
	public Set<Periode> getPeriodes() {
		return periodes;
	}
    
}
