package com.ayouris.tawassol.common.model.bean;

import java.io.Serializable;

import com.ayouris.tawassol.common.view.CommonView;

import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonView;

@Setter
@Getter
public class ReferenceDataBean implements Serializable {
    
	private static final long serialVersionUID = 137263991965312012L;

    @JsonView(CommonView.class)
    private Long id;
    
    @JsonView(CommonView.class)
    private String label;
    
    @JsonView(CommonView.class)
    private String code;

}
