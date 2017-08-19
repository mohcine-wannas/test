package com.ayouris.tawassol.common.model.bean;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonView;
import com.google.common.reflect.TypeToken;

import lombok.Getter;
import lombok.Setter;
import com.ayouris.tawassol.common.model.entity.ConditionCommerciale;
import com.ayouris.tawassol.common.model.enums.Periodicite;
import com.ayouris.tawassol.common.view.CommonView;
import com.ayouris.tawassol.common.view.EditionView;

/**
 * 
 * @author m.wannas
 *
 */

@Setter
@Getter
public class ConditionCommercialeBean implements Serializable{

    private static final long serialVersionUID = -5029569008726313519L;

    @SuppressWarnings("serial")
	public static final Type LIST_BEAN_TYPE = new TypeToken<ArrayList<ConditionCommercialeBean>>(){}
            .getType();

    @SuppressWarnings("serial")
	public static final Type LIST_ENTITY_TYPE = new TypeToken<ArrayList<ConditionCommerciale>>(){}
            .getType();

    @JsonView(CommonView.class)
    private Long id;
	@JsonView(CommonView.class)
    private TypePieceReglementBean pieceReglement;
	@JsonView(CommonView.class)
    private Periodicite periodicite;

    @JsonView(CommonView.class)
    private boolean noPayment;
    
    @JsonView(EditionView.class)
    private List<PeriodeBean> periodes;
    
    private Boolean active;

    public boolean isActive() {
        return active;
    }

    public boolean getNoPayment() {
        return noPayment;
    }

    public void setPieceReglement(TypePieceReglementBean pieceReglement) {
        this.pieceReglement = pieceReglement;
    }

    public void setPeriodicite(Periodicite periodicite) {
        this.periodicite = periodicite;
    }

}
