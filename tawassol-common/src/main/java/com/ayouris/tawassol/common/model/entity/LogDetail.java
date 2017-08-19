package com.ayouris.tawassol.common.model.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Setter;
import com.ayouris.tawassol.common.model.entity.generic.AuditEntity;

/**
 * 
 * @author m.wannas
 *
 */
@Setter
@Entity
@Table(name = "journale_details", schema = "tawassol")
public class LogDetail extends AuditEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Journalisation journale;
	String champs;
	String oldValue;
	String newValue;
	
    @ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "journale_id", foreignKey = @ForeignKey(name = "FK_DETAILs_JOURNALE_ID"))  
	public Journalisation getJournale() {
		return journale;
	}
	public String getChamps() {
		return champs;
	}
	public String getOldValue() {
		return oldValue;
	}
	public String getNewValue() {
		return newValue;
	}

}
