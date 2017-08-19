package com.ayouris.tawassol.common.model.bean;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonView;
import com.google.common.reflect.TypeToken;

import lombok.Getter;
import lombok.Setter;
import com.ayouris.tawassol.admin.model.beans.SiteBean;
import com.ayouris.tawassol.common.model.entity.Affectation;
import com.ayouris.tawassol.common.view.CommonView;
import com.ayouris.tawassol.common.view.EditionView;
import com.ayouris.tawassol.common.view.ListView;
import com.ayouris.tawassol.common.view.Views.FileAttenteView;

/**
 * 
 * @author m.wannas
 *
 */

@Setter
@Getter
public class AffectationBean implements Serializable{

    private static final long serialVersionUID = -5029569008726313519L;

    @SuppressWarnings("serial")
	public static final Type LIST_BEAN_TYPE = new TypeToken<ArrayList<AffectationBean>>(){}
            .getType();

    @SuppressWarnings("serial")
	public static final Type LIST_ENTITY_TYPE = new TypeToken<ArrayList<Affectation>>(){}
            .getType();
            
    @JsonView(CommonView.class)
    private Long id;
    @JsonView({EditionView.class,ListView.class})
    private Boolean active;
    @JsonView({EditionView.class})
	private ClientBean client;
    @JsonView({EditionView.class})
	private SiteBean site;
    @JsonView({EditionView.class,FileAttenteView.class})
	private ProduitBean produit;
    @JsonView(CommonView.class)
	private Boolean droitAvoir;
    @JsonView(CommonView.class)
	private Boolean droitBCRG;
    @JsonView(CommonView.class)
	private Boolean limiter;
    @JsonView(CommonView.class)
	private Date dateDebut;
    @JsonView(CommonView.class)
	private Date dateFin;
    @JsonView({EditionView.class})
	private ConcessionnaireBean concessionnaire;
    @JsonView({ListView.class})
	private String clientLibelle;
    @JsonView({ListView.class})
	private String siteLibelle;
    @JsonView({ListView.class})
	private String produitLibelle;
    @JsonView({ListView.class})
	private Long concessionnaireId;

    public boolean isActive() {
        return active;
    }

}
