package ma.salamgaz.tawassol.security.model;

import java.util.List;

public interface UserContext {

    long getId();

    List<String> getRoles();

    String getUsername();
}
