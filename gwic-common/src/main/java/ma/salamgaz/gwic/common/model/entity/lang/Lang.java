package ma.salamgaz.gwic.common.model.entity.lang;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import ma.salamgaz.gwic.common.model.entity.generic.BaseEntity;

import lombok.Setter;

@Setter
@Entity
@Table(name = "lang", schema = "gwic")
public class Lang extends BaseEntity {

    private static final long serialVersionUID = -1533213657178852992L;

    private String code;

    private String name;

    @Column(name = "code", length = 5)
    public String getCode() {
        return this.code;
    }

    @Column(name = "name", length = 64)
    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Lang)) {
            return false;
        }
        Lang that = (Lang) obj;
        return that.getCode() != null && that.getCode().equals(getCode());
    }

    @Override
    public int hashCode() {
        return getCode() == null ? 0 : getCode().hashCode();
    }

    @Override
    public String toString() {
        return code;
    }

}