package ma.salamgaz.tawassol.common.repository.impl;

/**
 * Used by Spring Data to instantiate {@link  ma.salamgaz.tawassol.common.repository.impl.CommonRepositoryImpl}
 *
 */
import java.io.Serializable;

import javax.persistence.EntityManager;

import org.modelmapper.internal.util.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import ma.salamgaz.tawassol.common.model.entity.generic.BaseEntity;
import ma.salamgaz.tawassol.common.repository.CommonRepository;

public class CommonRepositoryFactoryBean<R extends JpaRepository<T, I>, T extends BaseEntity, I extends Serializable>
        extends JpaRepositoryFactoryBean<R, T, I> {

    public CommonRepositoryFactoryBean(Class<? extends R> repositoryInterface) {
		super(repositoryInterface);
		// TODO Auto-generated constructor stub
	}

	@Override
    protected RepositoryFactorySupport createRepositoryFactory(EntityManager entityManager) {
        return new BaseRepositoryFactory(entityManager);
    }

    private static class BaseRepositoryFactory extends JpaRepositoryFactory {
        
    	@Autowired
    	private final EntityManager entityManager;

        public BaseRepositoryFactory(EntityManager entityManager) {
            super(entityManager);
            Assert.notNull(entityManager);
            this.entityManager = entityManager;
        }

        @Override
    	@SuppressWarnings({ "rawtypes", "unchecked" })
		protected Object getTargetRepository(RepositoryInformation repositoryInformation) {

			JpaEntityInformation entityInformation = (JpaEntityInformation) getEntityInformation(
        			repositoryInformation.getDomainType());
            return new CommonRepositoryImpl(entityInformation, entityManager);
        }

        @Override
        protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
            // The RepositoryMetadata can be safely ignored, it is used by the
            // JpaRepositoryFactory
            // to check for QueryDslJpaRepository's which is out of scope.
            return CommonRepository.class;
        }
        
    }

}
