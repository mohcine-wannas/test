package ma.salamgaz.tawassol.common.model.bean;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;

import com.google.common.reflect.TypeToken;

import lombok.Getter;
import lombok.Setter;
import ma.salamgaz.tawassol.common.model.entity.Citerne;

/**
 * 
 * @author m.wannas
 *
 */

@Setter
@Getter
public class CiterneBean implements Serializable {

	private static final long serialVersionUID = -5029569008726313519L;

	@SuppressWarnings("serial")
	public static final Type LIST_BEAN_TYPE=new TypeToken<ArrayList<CiterneBean>>(){}.getType();

	@SuppressWarnings("serial")
	public static final Type LIST_ENTITY_TYPE=new TypeToken<ArrayList<Citerne>>(){}.getType();

	private Long id;
	private String code;
	private Boolean active;

	private TransporteurBean transporteur;
	private String numFabrication;
	private FabriquantBean fabriquant;
	private Date dateFabrication;
	private Date dateReepreuve;
	private Integer volume;
	private Boolean acceptePropane;
	private Boolean accepteButane;
	private Double capacitePropane;
	private Double capaciteButane;
	private String pressionService;

	private String fabriquantLibelle;
	private String transporteurLibelle;

	public void setCode(String code) {
		this.code = code.toUpperCase();
	}
	
	public boolean isActive() {
		return active;
	}

}
