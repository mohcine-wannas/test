package ma.salamgaz.tawassol.service;

import ma.salamgaz.tawassol.common.model.bean.SchoolBean;
import ma.salamgaz.tawassol.common.model.entity.School;

/**
 * 
 * @author m.wannas
 *
 */

public interface SchoolService extends GenericService<School, Long> {

	Long update(SchoolBean school);


}
