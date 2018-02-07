package ma.salamgaz.tawassol.admin.model.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import ma.salamgaz.tawassol.common.mapper.NotNullable;
import ma.salamgaz.tawassol.common.model.entity.NameDescriptionData;


@Entity
@Table(name = "resources", schema = "tawassol")
@NotNullable
public class Resource extends NameDescriptionData {

    private static final long serialVersionUID = -3146982904254663129L;
}
