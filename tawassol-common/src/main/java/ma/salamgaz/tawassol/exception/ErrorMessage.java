package ma.salamgaz.tawassol.exception;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@SuppressWarnings("unused")
public class ErrorMessage implements Serializable {

	private static final long serialVersionUID = 134029623684047335L;
	
	private int errorCode;
	private int httpStatus;
	private String errorId;
	private String userMessage;
	private String debugMessage;

}
