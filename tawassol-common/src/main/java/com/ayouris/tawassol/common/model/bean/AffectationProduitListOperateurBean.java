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
public class AffectationProduitListOperateurBean implements Serializable{

    private static final long serialVersionUID = -5029569008726313519L;

    @SuppressWarnings("serial")
	public static final Type LIST_BEAN_TYPE = new TypeToken<ArrayList<AffectationProduitListOperateurBean>>(){}
            .getType();

    @SuppressWarnings("serial")
	public static final Type LIST_ENTITY_TYPE = new TypeToken<ArrayList<AffectationProduitOperateur>>(){}
            .getType();

    private String id;
	private SiteBean site;
	private UserDetailsBean user; //TODO
    
    private List<ProduitBean> produits;

    public List<AffectationProduitOperateurBean> convertToAffectationProduitOperateurBeans() {
    	List<AffectationProduitOperateurBean> affectations = new ArrayList<AffectationProduitOperateurBean>();
    	for(ProduitBean produit : produits) {
    		AffectationProduitOperateurBean affectation = new AffectationProduitOperateurBean();
    		affectation.setSite(this.site);
    		affectation.setUser(this.user);
    		affectation.setProduit(produit);
    		affectations.add(affectation);
    	}
    	
    	return affectations;
    }

}
