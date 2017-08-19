package com.ayouris.tawassol.common.exception;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonView;

import lombok.Getter;
import com.ayouris.tawassol.common.view.CommonView;

@Getter
@SuppressWarnings("serial")
public class ErrorMessageResponse implements Serializable {

	@JsonView(CommonView.class)
    private final String fieldName;
	@JsonView(CommonView.class)
    private final String message;
	@JsonView(CommonView.class)
    private final int code;

    public ErrorMessageResponse(int code, String fieldName, String message) {
        this.code = code;
        this.fieldName = fieldName;
        this.message = message;
    }

}
