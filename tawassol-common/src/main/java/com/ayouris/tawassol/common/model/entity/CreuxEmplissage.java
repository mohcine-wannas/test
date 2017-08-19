package com.ayouris.tawassol.common.model.entity;

import java.util.Date;

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
@Table(name = "creuxEmplissage", schema = "tawassol")
public class CreuxEmplissage extends AuditEntity {

	private static final long serialVersionUID = 1L;

	@NotNull
	private Date heureDebut;

	@NotNull
	private Date heureFin;

	@NotNull
	private FicheMarche ficheMarche;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ficheJouMarche_id", foreignKey = @ForeignKey(name = "FK_CREUXEMP_FMARCHE_ID"))
	public FicheMarche getFicheMarche() {
		return ficheMarche;
	}

	public Date getHeureDebut() {
		return heureDebut;
	}

	public Date getHeureFin() {
		return heureFin;
	}

}
