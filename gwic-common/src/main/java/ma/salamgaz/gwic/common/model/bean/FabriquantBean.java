package ma.salamgaz.gwic.common.model.bean;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.common.reflect.TypeToken;

import lombok.Getter;
import lombok.Setter;
import ma.salamgaz.gwic.common.model.entity.Fabriquant;

/**
 * 
 * @author m.wannas
 *
 */

@Setter
@Getter
public class FabriquantBean implements Serializable{


    /**
	 * 
	 */
	private static final long serialVersionUID = -3713800630031264373L;

	@SuppressWarnings("serial")
	public static final Type LIST_BEAN_TYPE = new TypeToken<ArrayList<FabriquantBean>>(){}
            .getType();

    @SuppressWarnings("serial")
	public static final Type LIST_ENTITY_TYPE = new TypeToken<ArrayList<Fabriquant>>(){}
            .getType();

    private Long id;
	private String libelle;
    private Boolean active;

    public boolean isActive() {
        return active;
    }

}
