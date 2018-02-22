package com.ayouris.tawassol.common.model.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Setter;
import com.ayouris.tawassol.common.model.entity.generic.RefEntity;

/**
 * 
 * @author m.wannas
 *
 */
@Setter
@Entity
@Table(name = "citerne", schema = "tawassol")
public class Citerne extends RefEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6176024974860812684L;
	
	@NotNull
	private Transporteur transporteur;
	@NotNull
	private String numFabrication;
	@NotNull
	private Fabriquant fabriquant;
	@NotNull
	private Date dateFabrication;
	@NotNull
	private Date dateReepreuve;
	@NotNull
	private Integer volume;
	@NotNull
	private Boolean acceptePropane;
	@NotNull
	private Boolean accepteButane;
	
	private Double capacitePropane;
	
	private Double capaciteButane;
	@NotNull
	private String pressionService;


	@ManyToOne
	@JoinColumn(name = "concessionnaire_id", nullable = false, foreignKey = @ForeignKey(name = "FK_FABRIQUANT_CONCESS_ID"))  
	public Fabriquant getFabriquant() {
		return fabriquant;
	}
	
	@ManyToOne
	@JoinColumn(name = "transporteur_id", nullable = false, foreignKey = @ForeignKey(name = "FK_TRANSPORTEUR_CONCESS_ID"))  
	public Transporteur getTransporteur() {
		return transporteur;
	}

	public String getNumFabrication() {
		return numFabrication;
	}


	public Date getDateFabrication() {
		return dateFabrication;
	}


	public Date getDateReepreuve() {
		return dateReepreuve;
	}


	public Integer getVolume() {
		return volume;
	}


	public Boolean getAcceptePropane() {
		return acceptePropane;
	}


	public Boolean getAccepteButane() {
		return accepteButane;
	}


	public Double getCapacitePropane() {
		return capacitePropane;
	}


	public Double getCapaciteButane() {
		return capaciteButane;
	}


	public String getPressionService() {
		return pressionService;
	}




    
}
