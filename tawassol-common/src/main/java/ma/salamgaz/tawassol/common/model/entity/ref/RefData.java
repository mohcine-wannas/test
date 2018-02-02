package ma.salamgaz.tawassol.common.model.entity.ref;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;


import ma.salamgaz.tawassol.common.entity.listener.PreventAnyModificationListener;
import ma.salamgaz.tawassol.common.mapper.NotNullable;
import ma.salamgaz.tawassol.common.model.entity.lang.RefDataLang;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ma.salamgaz.tawassol.common.model.entity.generic.UserLangListener;

import ma.salamgaz.tawassol.common.model.entity.generic.BaseEntity;

@Setter
@EntityListeners(PreventAnyModificationListener.class)
@MappedSuperclass
@NotNullable
public abstract class RefData<E extends RefDataLang> extends BaseEntity {

    private static final long serialVersionUID = 5569361057686079462L;

    public static enum COLUMNS {
        CODE("code"),
        LOCALIZED_LABEL("localizedLabel"),
        LABEL("label"),
        ACTIVE("active");

        private String name;

        COLUMNS(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    protected String code;

    @Column(name = "code")
    public String getCode() {
        return code;
    }

    @JsonIgnore
    @Transient
    public abstract Set<E> getLocalizedLabel();

    @Transient
    public String getLabel(String lang) {
        for (E data : getLocalizedLabel()) {
            if (data.getLang().getCode().equalsIgnoreCase(lang)) {
                return data.getLabel();
            }
        }
        if (getLocalizedLabel().iterator().hasNext()) {
            return getLocalizedLabel().iterator().next().getLabel();
        }
        return "<MISSING LABEL>";
    }


    @Transient
    public String getLabel() {
        return getLabel(UserLangListener.getCurrentLang());
    }

    @Override
    public int hashCode() {
        return 31 + ((code == null) ? 0 : code.hashCode());
    }

    @SuppressWarnings("unchecked")
	@Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj)
            return true;
        if (getClass() != obj.getClass())
            return false;
        RefData<E> other = (RefData<E>) obj;
        if (code == null) {
            if (other.code != null)
                return false;
        } else if (!code.equals(other.code))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return code;
    }

}
