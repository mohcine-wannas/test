package com.ayouris.tawassol.common.model.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import com.ayouris.tawassol.admin.model.entity.User;

import lombok.Setter;

/**
 * 
 * @author m.wannas
 *
 */
@Setter
@Entity
@Table(name = "eleve")
@PrimaryKeyJoinColumn(name="user_id")  
public class Eleve  extends User{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2277658097503465662L;
	
	private LocalDate dateNaissance;
	private String codeMassar;
	private String observation;
	
	public String getCodeMassar() {
		return codeMassar;
	}

	public LocalDate getDateNaissance() {
		return dateNaissance;
	}

	public String getObservation() {
		return observation;
	}

//	public Long getUser_id() {
//		return user_id;
//	}
 
	
	
}
