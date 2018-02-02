package ma.salamgaz.tawassol.common.model.bean;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.common.reflect.TypeToken;

import lombok.Getter;
import lombok.Setter;
import ma.salamgaz.tawassol.common.model.entity.Marque;

/**
 * 
 * @author m.wannas
 *
 */

@Setter
@Getter
public class MarqueBean implements Serializable{

    private static final long serialVersionUID = -5029569008726313519L;

    @SuppressWarnings("serial")
	public static final Type LIST_BEAN_TYPE = new TypeToken<ArrayList<MarqueBean>>(){}
            .getType();

    @SuppressWarnings("serial")
	public static final Type LIST_ENTITY_TYPE = new TypeToken<ArrayList<Marque>>(){}
            .getType();

    private Long id;
	private String libelle;
    private Boolean active;


    
    public boolean isActive() {
        return active;
    }

}
