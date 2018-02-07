package ma.salamgaz.tawassol.common.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import ma.salamgaz.tawassol.admin.model.enums.SiteType;
import ma.salamgaz.tawassol.common.model.entity.generic.RefEntity;


@Entity
@Table(name = "site", schema = "tawassol")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Site extends RefEntity {
	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getAbreviation() {
		return abreviation;
	}

	public void setAbreviation(String abreviation) {
		this.abreviation = abreviation;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public String getTauxTransport() {
		return tauxTransport;
	}

	public void setTauxTransport(String tauxTransport) {
		this.tauxTransport = tauxTransport;
	}

	public void setSiteType(SiteType siteType) {
		this.siteType = siteType;
	}

	/**
		 * 
		 */
	private static final long serialVersionUID = -289624990658556167L;

	private SiteType siteType;
	private String libelle;
	private String abreviation;
	private String ville;
	private String tauxTransport;

	@Enumerated(EnumType.STRING)
	@Column(name = "site_type")
	public SiteType getSiteType() {
		return siteType;
	}

}
