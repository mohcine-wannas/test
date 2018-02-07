package ma.salamgaz.tawassol.common.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import lombok.Setter;
import ma.salamgaz.tawassol.admin.model.enums.ColumnType;
import ma.salamgaz.tawassol.admin.model.enums.ViewName;
import ma.salamgaz.tawassol.common.model.entity.generic.BaseEntity;

@Setter
@Entity
@Table(name = "columndef", schema = "tawassol")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class ColumnDef extends BaseEntity{
/**
	 * 
	 */ 
	private static final long serialVersionUID = -289624990658556167L;

	String headerName;
	String field;
	String nestedField;
	Integer width;
	Boolean editable;
	Boolean supressMenu;
	Boolean suppressSorting;
	Boolean hide;
	Boolean pinned;
	ColumnType type;
	ViewName viewName;
	Integer order;
	
    @Enumerated(EnumType.STRING)
	@Column(name="column_type")    
	public ColumnType getType() {
		return type;
	}
	
    @Enumerated(EnumType.STRING)
	public ViewName getViewName() {
		return viewName;
	}

	@Column(name="column_hide")
    public Boolean getHide() {
    	return hide;
    }

	@Column(name="column_order")
    public Integer getOrder() {
    	return order;
    }

	public String getHeaderName() {
		return headerName;
	}

	public String getField() {
		return field;
	}

	public String getNestedField() {
		return nestedField;
	}

	public Integer getWidth() {
		return width;
	}

	public Boolean getEditable() {
		return editable;
	}

	public Boolean getSupressMenu() {
		return supressMenu;
	}

	public Boolean getSuppressSorting() {
		return suppressSorting;
	}

	public Boolean getPinned() {
		return pinned;
	}

}
