package ma.salamgaz.tawassol.common.model.bean;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FieldBean {

    private String field;

    private List<String> relations;

}
