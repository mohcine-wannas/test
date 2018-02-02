package ma.salamgaz.gwic.security.repository;

import ma.salamgaz.gwic.security.repository.CustomResourceRepository;

import org.springframework.stereotype.Repository;

import ma.salamgaz.gwic.admin.model.entity.Resource;
import ma.salamgaz.gwic.common.repository.CommonRepository;

@Repository
public interface ResourceRepository extends CommonRepository<Resource>, CustomResourceRepository {

}
