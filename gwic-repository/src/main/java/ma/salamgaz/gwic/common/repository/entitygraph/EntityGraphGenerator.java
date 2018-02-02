package ma.salamgaz.gwic.common.repository.entitygraph;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;

import org.springframework.stereotype.Component;

@Component
public final class EntityGraphGenerator {

    public <T> EntityGraph<T> graphWithRequests(EntityManager em, Class<T> classe, String... fields) {
        EntityGraph<T> graph = em.createEntityGraph(classe);
        if (fields != null && fields.length > 0) {
            for (String fieldName : fields) {
                try {
                    graph.addAttributeNodes(fieldName);
                } catch (IllegalArgumentException e) {
                    // no need to log
                }
            }
        }
        return graph;
    }

}
