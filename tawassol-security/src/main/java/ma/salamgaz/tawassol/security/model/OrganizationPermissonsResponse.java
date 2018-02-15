package ma.salamgaz.tawassol.security.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class OrganizationPermissonsResponse implements Serializable {

    private List<ResourceReponse> resources = new ArrayList<ResourceReponse>(0);

    public void addResource(ResourceReponse resource) {
        resources.add(resource);
    }

    public List<ResourceReponse> getResources() {
        return resources;
    }

    public void setResources(List<ResourceReponse> resources) {
        this.resources = resources;
    }

    @Override
    public String toString() {
        return "OrganizationPermissonsResponse [resources=" + resources + "]";
    }

}
