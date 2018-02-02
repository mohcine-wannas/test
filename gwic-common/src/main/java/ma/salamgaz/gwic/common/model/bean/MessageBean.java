package ma.salamgaz.gwic.common.model.bean;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;

import com.google.common.reflect.TypeToken;
import ma.salamgaz.gwic.common.enums.MessageSubject;
import ma.salamgaz.gwic.common.enums.MessageType;

import lombok.Getter;
import lombok.Setter;

/**
 * Class contain message information displayed.
 * 
 */
@Setter
@Getter
public final class MessageBean {

    @SuppressWarnings("serial")
	public static final Type LIST_TYPE = new TypeToken<ArrayList<MessageBean>>() {
    }.getType();

    private MessageType messageType;

    private String comment;

    private String author;

    private Date date;
    
    private MessageSubject messageSubject;

}