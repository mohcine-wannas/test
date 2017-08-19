package com.ayouris.tawassol.common.model.bean;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonView;
import com.google.common.reflect.TypeToken;

import lombok.Getter;
import lombok.Setter;
import com.ayouris.tawassol.common.model.entity.Periode;
import com.ayouris.tawassol.common.view.CommonView;

/**
 * 
 * @author m.wannas
 *
 */

@Setter
@Getter
public class PeriodeBean implements Serializable{

    private static final long serialVersionUID = -5029569008726313519L;

    @SuppressWarnings("serial")
	public static final Type LIST_BEAN_TYPE = new TypeToken<ArrayList<PeriodeBean>>(){}
            .getType();

    @SuppressWarnings("serial")
	public static final Type LIST_ENTITY_TYPE = new TypeToken<ArrayList<Periode>>(){}
            .getType();

    @JsonView(CommonView.class)
    private Long id;
    @JsonView(CommonView.class)
	private Integer start;
    @JsonView(CommonView.class)
	private Integer day;
    @JsonView(CommonView.class)
	private Integer end;
    @JsonView(CommonView.class)
	private Date from;
    @JsonView(CommonView.class)
	private Integer tolerance;
    @JsonView(CommonView.class)
	private Integer daysNumber;
    
    private Boolean active;

    public boolean isActive() {
        return active;
    }

}
