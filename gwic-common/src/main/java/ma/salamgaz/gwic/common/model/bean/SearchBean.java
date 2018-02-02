package ma.salamgaz.gwic.common.model.bean;

import java.io.Serializable;
import java.util.List;

import javax.ws.rs.QueryParam;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SearchBean implements Serializable {

    private static final long serialVersionUID = 6879180975463748645L;

    @QueryParam("input")
    private String input;

    @QueryParam("type")
    private String type;
    
    private List<FieldBean> fields;

    public SearchBean() {
        super();
    }

    public SearchBean(String input) {
        super();
        this.input = input;
    }

}
