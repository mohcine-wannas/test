package com.ayouris.tawassol.common.model.bean;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.common.reflect.TypeToken;

import lombok.Getter;
import lombok.Setter;
import com.ayouris.tawassol.admin.model.beans.SiteBean;
import com.ayouris.tawassol.common.model.entity.AffectationProduitOperateur;

/**
 * 
 * @author m.wannas
 *
 */

@Setter
@Getter
public class AffectationProduitOperateurBean implements Serializable{

    private static final long serialVersionUID = -5029569008726313519L;

    @SuppressWarnings("serial")
	public static final Type LIST_BEAN_TYPE = new TypeToken<ArrayList<AffectationProduitOperateurBean>>(){}
            .getType();

    @SuppressWarnings("serial")
	public static final Type LIST_ENTITY_TYPE = new TypeToken<ArrayList<AffectationProduitOperateur>>(){}
            .getType();

    private Long id;
	private SiteBean site;
	private UserDetailsBean user; //TODO
	private ProduitBean produit;
    private Boolean active;
    
    private String siteLibelle;
    private String userUsername;
    private String produitName;
    private List<ProduitBean> produits;

    public boolean isActive() {
        return active;
    }

}
