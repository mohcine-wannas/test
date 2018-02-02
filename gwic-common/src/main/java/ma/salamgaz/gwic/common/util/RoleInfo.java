package ma.salamgaz.gwic.common.util;

import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.common.reflect.TypeToken;

import ma.salamgaz.gwic.common.enums.ContactType;

import lombok.Getter;
import lombok.Setter;

import com.fasterxml.jackson.annotation.JsonProperty;

@Getter
@Setter
@SuppressWarnings("unused")
public class RoleInfo {

    @SuppressWarnings("serial")
	public static final Type LIST_TYPE = new TypeToken<ArrayList<RoleInfo>>() {
    }.getType();

	private Long id;

    @JsonProperty("label")
    private String name;

    private String description;

    private Integer rank;

    private ContactType organizationType;

    public RoleInfo() {

    }

    public RoleInfo(String name, int rank) {
        this.name = name;
        this.rank = Integer.valueOf(rank);
    }
}
