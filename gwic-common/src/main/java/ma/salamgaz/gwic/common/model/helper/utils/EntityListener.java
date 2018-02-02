package ma.salamgaz.gwic.common.model.helper.utils;

import ma.salamgaz.gwic.common.model.base.BaseEntity;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;


public class EntityListener {
    // private static String FIRST_COMPTEUR_ID = "0000000001";
    //
    // private EntityManager entityManager;
    //
    // private User currentUser;
    //
    @PrePersist
    public void prePersist(BaseEntity baseEntity) {

        baseEntity.setCreatedDate(LocalDateTime.now());
        baseEntity.setLastModifiedDate(LocalDateTime.now());
    }

    @PreUpdate
    public void preUpdate(BaseEntity baseEntity) {
        baseEntity.setLastModifiedDate(LocalDateTime.now());
    }

    //
    // if (baseEntity instanceof BaseParam) {
    // BaseParam baseParam = (BaseParam) baseEntity;
    // baseParam.setOrdre(generateOrder(baseParam));
    // }
    // }
    //
    // @PreUpdate
    // public void preUpdate(BaseEntity baseEntity) {
    //
    // getDataSession();
    // baseEntity.setLastModifiedBy(currentUser);
    // baseEntity.setLastModifiedDate(new DateTime());
    // }
    //
    // private void getDataSession() {
    //
    // WebApplicationContext context =
    // ContextLoader.getCurrentWebApplicationContext();
    //
    // EntityManagerFactory entityManagerFactory = (EntityManagerFactory)
    // context.getBean("entityManagerFactory");
    // entityManager = entityManagerFactory.createEntityManager();
    // currentUser = (User) context.getBean("currentUser");
    // }
    //
    // private String generateId(BaseEntity baseEntity) {
    //
    // StringBuffer sb = new StringBuffer();
    //
    // // current year with format yy//
    // Calendar calendar = Calendar.getInstance();
    // SimpleDateFormat sdf = new SimpleDateFormat("yy");
    // String year = sdf.format(calendar.getTime());
    // // current year with format yy//
    //
    // sb.append(" select max(id) from " + baseEntity.getClass().getName());
    // // sb.append(" where id like :boId");
    //
    // Query query = entityManager.createQuery(sb.toString());
    // // query.setParameter("boId", currentUser.getId().concat("%"));
    //
    // String maxId = (String) query.getSingleResult();
    // if (maxId != null) {
    // int compteur = Integer.parseInt(maxId.substring(6, 15)) + 1;
    // return currentUser.getSite().getSection().substring(0, 3) + year +
    // StringUtils.leftPad("" + compteur, 10, '0');
    //
    // } else {
    // return currentUser.getSite().getSection().substring(0, 3) + year +
    // FIRST_COMPTEUR_ID;
    // }
    // }
    //
    // private Integer generateOrder(BaseParam baseParam) {
    //
    // StringBuffer sb = new StringBuffer();
    //
    // sb.append(" select max(ordre) from " + baseParam.getClass().getName());
    // // sb.append(" where id like :boId");
    //
    // Query query = entityManager.createQuery(sb.toString());
    // // query.setParameter("boId", currentUser.getId().concat("%"));
    //
    // Integer maxId = (Integer) query.getSingleResult();
    // if (maxId != null) {
    // return maxId + 1;
    // } else {
    // return 1;
    // }
    // }
}