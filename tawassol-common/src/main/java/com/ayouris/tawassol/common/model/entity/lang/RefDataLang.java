package com.ayouris.tawassol.common.model.entity.lang;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import com.ayouris.tawassol.common.model.entity.generic.BaseEntity;
import com.ayouris.tawassol.common.entity.listener.PreventAnyModificationListener;
import com.ayouris.tawassol.common.mapper.NotNullable;

import lombok.Setter;

@Setter
@EntityListeners(PreventAnyModificationListener.class)
@MappedSuperclass
@NotNullable
public class RefDataLang extends BaseEntity {

    private static final long serialVersionUID = -3147521829434204423L;

    public static enum COLUMNS {
        LANG("lang"),
        LABEL("label");

        private String name;

        COLUMNS(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    public Lang lang;

    public String label;

    @Column(name = "label", length = 64)
    public String getLabel() {
        return label;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "lang_id")
    public Lang getLang() {
        return lang;
    }

}
