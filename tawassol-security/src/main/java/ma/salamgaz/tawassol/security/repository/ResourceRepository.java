package ma.salamgaz.tawassol.security.repository;

import ma.salamgaz.tawassol.security.repository.CustomResourceRepository;

import org.springframework.stereotype.Repository;

import ma.salamgaz.tawassol.admin.model.entity.Resource;
import ma.salamgaz.tawassol.common.repository.CommonRepository;

@Repository
public interface ResourceRepository extends CommonRepository<Resource>, CustomResourceRepository {

}
