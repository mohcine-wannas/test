package ma.salamgaz.gwic.admin.model.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import ma.salamgaz.gwic.common.model.entity.NameDescriptionData;

@Entity
@Table(name = "operation", schema = "gwic")
public class Operation extends NameDescriptionData {

    private static final long serialVersionUID = 6180862003399603896L;

}
