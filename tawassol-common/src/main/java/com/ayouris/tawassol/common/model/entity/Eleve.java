package com.ayouris.tawassol.common.model.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.*;

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

    private String etatSante;
    private String remarque;

	public String getCodeMassar() {
		return codeMassar;
	}

	public LocalDate getDateNaissance() {
		return dateNaissance;
	}

	public String getObservation() {
		return observation;
	}

	private List<AffectationParentEleve> affectationParents;

	@OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY, orphanRemoval = true,
			mappedBy = "eleve")
	public List<AffectationParentEleve> getAffectationParents() {
		return affectationParents;
    }

    public String getEtatSante() {
        return etatSante;
    }

    public String getRemarque() {
        return remarque;
    }

    //	public Long getUser_id() {
//		return user_id;
//	}



}
