package ma.salamgaz.gwic.security.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@SuppressWarnings("serial")
public class RoleResponse implements Serializable {

    private long id;

    private String name;

    private List<ResourceReponse> resources = new ArrayList<ResourceReponse>();

    @Override
    public String toString() {
        return "RoleResponse [id=" + id + ", name=" + name + ", resources=" + resources + "]";
    }

}
