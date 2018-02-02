package ma.salamgaz.gwic.admin.model.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import ma.salamgaz.gwic.common.mapper.NotNullable;
import ma.salamgaz.gwic.common.model.entity.NameDescriptionData;


@Entity
@Table(name = "resources", schema = "gwic")
@NotNullable
public class Resource extends NameDescriptionData {

    private static final long serialVersionUID = -3146982904254663129L;
}
