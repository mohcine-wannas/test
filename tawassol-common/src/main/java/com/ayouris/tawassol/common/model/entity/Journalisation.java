package com.ayouris.tawassol.common.model.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Setter;
import com.ayouris.tawassol.admin.model.entity.User;
import com.ayouris.tawassol.common.model.entity.generic.AuditEntity;
import com.ayouris.tawassol.common.model.enums.TypeOperation;

/**
 * 
 * @author m.wannas
 *
 */
@Setter
@Entity
@Table(name = "journale", schema = "tawassol")
public class Journalisation extends AuditEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6410052310321795436L;
	
	Date dateLog;
	String typeName;
	User membre;
	Site site;
	Long referenceId;
	TypeOperation typeOperation;
	String motif;
	List<LogDetail> logDetails;
	
	public Date getDateLog() {
		return dateLog;
	}
	public String getTypeName() {
		return typeName;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	public Site getSite() {
		return site;
	}
	public String getMotif() {
		return motif;
	}
	
	@Enumerated(EnumType.STRING)
	public TypeOperation getTypeOperation() {
		return typeOperation;
	}
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "journale", cascade = { CascadeType.ALL })
	public List<LogDetail> getLogDetails() {
		return logDetails;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	public User getMembre() {
		return membre;
	}
	public Long getReferenceId() {
		return referenceId;
	}
	

    
}
