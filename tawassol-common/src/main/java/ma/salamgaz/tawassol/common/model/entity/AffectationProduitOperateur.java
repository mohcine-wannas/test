package ma.salamgaz.tawassol.common.model.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

import lombok.Setter;
import ma.salamgaz.tawassol.admin.model.entity.User;
import ma.salamgaz.tawassol.common.model.entity.generic.RefEntity;

/**
 * 
 * @author m.wannas
 *
 */

@Setter
@Entity
@Table(name = "affectation_produit_operateur", schema = "tawassol",  uniqueConstraints=
@UniqueConstraint(columnNames={"site_id", "user_id","produit_id"}))
public class AffectationProduitOperateur extends RefEntity {


	/**
	 * 
	 */
	private static final long serialVersionUID = -1008491895351199781L;
	@NotNull
	private Site site;

	@NotNull
	private User user;
	@NotNull
	private Produit produit;
	
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "site_id", nullable = false, foreignKey = @ForeignKey(name = "FK_AFFECTPRODOPER_SITE_ID"))
	public Site getSite() {
		return site;
	}
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "FK_AFFECTPRODOPER_USER_ID"))
	public User getUser() {
		return user;
	}
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "produit_id", nullable = false, foreignKey = @ForeignKey(name = "FK_AFFECTPRODOPER_PRODUIT_ID"))
	public Produit getProduit() {
		return produit;
	}
    
}
