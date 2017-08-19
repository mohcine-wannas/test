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
@Table(name = "controle_camion_conformity", schema = "tawassol")
public class ControleCamionConformity extends AuditEntity {



	/**
	 * 
	 */
	private static final long serialVersionUID = 4733079654003832685L;
	
	
	private ControleCamion controleCamion;
	
	private FileAttente fileAttente;
	
    private Boolean conforme = false;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "controlecamion_id", nullable = false, foreignKey = @ForeignKey(name = "FK_CCC_CONTROLECAMION_ID"))
	public ControleCamion getControleCamion() {
		return controleCamion;
	}

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fileattente_id", nullable = true, foreignKey = @ForeignKey(name = "FK_CCC_FILEATTENTE_ID"))
	public FileAttente getFileAttente() {
		return fileAttente;
	}

	public Boolean getConforme() {
		return conforme;
	}
    
}
