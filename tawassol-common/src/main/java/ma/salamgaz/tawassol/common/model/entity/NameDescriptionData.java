package ma.salamgaz.tawassol.common.model.entity;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import ma.salamgaz.tawassol.common.model.entity.generic.BaseEntity;

import lombok.Setter;

@Setter
@MappedSuperclass
public class NameDescriptionData extends BaseEntity {

    private static final long serialVersionUID = 2368273156988266621L;

    private String name;
    private String description;

    @Column(name = "name", length = 40)
    public String getName() {
        return this.name;
    }

    @Column(name = "description")
    public String getDescription() {
        return this.description;
    }

}
