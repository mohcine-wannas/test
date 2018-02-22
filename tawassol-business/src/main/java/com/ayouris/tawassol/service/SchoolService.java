package com.ayouris.tawassol.service;

import com.ayouris.tawassol.common.model.bean.SchoolBean;
import com.ayouris.tawassol.common.model.entity.School;

/**
 * 
 * @author m.wannas
 *
 */

public interface SchoolService extends GenericService<School, Long> {

	Long update(SchoolBean school);


}
