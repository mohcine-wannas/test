package ma.salamgaz.tawassol.common.mapper;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module.Feature;

/**
 * Prevent Hibernate's lazy-loaded properties from Jackson serialization
 *
 */
@Component("customJsonObjectMapper")
public class CustomJsonObjectMapper extends ObjectMapper {

    private static final long serialVersionUID = -1835901329187051723L;

    public CustomJsonObjectMapper() {
        Hibernate4Module hm = new Hibernate4Module();
        hm.disable(Feature.USE_TRANSIENT_ANNOTATION);
        registerModule(hm);
        this.configure(MapperFeature.DEFAULT_VIEW_INCLUSION, false);
    }

}
