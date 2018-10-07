package com.ayouris.tawassol.service;

import com.ayouris.tawassol.common.model.bean.SchoolBean;
import com.ayouris.tawassol.common.model.entity.School;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author m.wannas
 */

public interface SchoolService extends GenericService<School, Long> {

    Long update(SchoolBean school);

    School findByCode(String codeSchool);

    void updateLogo(InputStream is) throws IOException;
}
