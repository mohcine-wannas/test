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
@Table(name = "arretEmplissage", schema = "tawassol")
public class ArretEmplissage extends AuditEntity {

	private static final long serialVersionUID = 1L;

	@NotNull
	private Date heureDebut;

	@NotNull
	private Date heureFin;

	@NotNull
	private CauseArretEmplissage causeArret;

	@NotNull
	private FicheMarche ficheMarche;

	public Date getHeureDebut() {
		return heureDebut;
	}

	public Date getHeureFin() {
		return heureFin;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "causeArret_id", nullable = true, foreignKey = @ForeignKey(name = "FK_ARRET_CAUSEARR_ID"))
	public CauseArretEmplissage getCauseArret() {
		return causeArret;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ficheMarche_id", foreignKey = @ForeignKey(name = "FK_ARRETEMP_FMARCHE_ID"))
	public FicheMarche getFicheMarche() {
		return ficheMarche;
	}

}
