package ma.salamgaz.gwic.common.model.entity;

import java.util.List;

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

import lombok.Setter;
import ma.salamgaz.gwic.common.model.entity.generic.RefEntity;
import ma.salamgaz.gwic.common.model.enums.Periodicite;

/**
 * 
 * @author m.wannas
 *
 */

@Setter
@Entity
@Table(name = "conditionCommerciale", schema = "gwic")
public class ConditionCommerciale extends RefEntity {



	/**
	 * 
	 */
	private static final long serialVersionUID = 314500242883077404L;
	
	@NotNull
    private PieceReglement pieceReglement;
	@NotNull
    private Periodicite periodicite;
    private List<Periode> periodes;

    @ManyToOne
	@JoinColumn(name = "piece_reglement_id", nullable = false, foreignKey = @ForeignKey(name = "FK_PIECE_REGLEMENT_PERIODE_ID")) 
	public PieceReglement getPieceReglement() {
		return pieceReglement;
	}
    
	@Enumerated(EnumType.STRING)
	public Periodicite getPeriodicite() {
		return periodicite;
	}
	
    @OneToMany(fetch = FetchType.LAZY,
    	    mappedBy = "conditionCommerciale")
	public List<Periode> getPeriodes() {
		return periodes;
	}
    
}
