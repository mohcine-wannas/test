package ma.salamgaz.gwic.security.model;

import java.io.Serializable;

import lombok.Getter;

@Getter
@SuppressWarnings("serial")
public class ErrorMessageResponse implements Serializable {

    private final String fieldName;
    private final String message;
    private final int code;

    public ErrorMessageResponse(int code, String fieldName, String message) {
        this.code = code;
        this.fieldName = fieldName;
        this.message = message;
    }

}
