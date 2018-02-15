package ma.salamgaz.tawassol.common.model.helper.bean;

import java.util.Date;

/**
 * Interface de l'audit des entités
 */
public interface Auditable {

	/**
	 * Date de création
	 * 
	 * @return Date
	 */
	public Date getCreatedOn();

	/**
	 * Date de création
	 * 
	 * @param createOn
	 */
	public void setCreatedOn(Date createOn);

	/**
	 * Date de modification
	 * 
	 * @return Date
	 */
	public Date getUpdatedOn();

	/**
	 * Date de modification
	 * 
	 * @param updatedOn
	 */
	public void setUpdatedOn(Date updatedOn);

	/**
	 * Utilisateur de création
	 * 
	 * @return String
	 */
	public String getCreatedBy();

	/**
	 * Utilisateur de création
	 * 
	 * @param createdBy
	 */
	public void setCreatedBy(String createdBy);

	/**
	 * Utilisateur de modification
	 * 
	 * @return String
	 */
	public String getUpdatedBy();

	/**
	 * Utilisateur de modification
	 * 
	 * @param UpdatedBy
	 */
	public void setUpdatedBy(String UpdatedBy);

}