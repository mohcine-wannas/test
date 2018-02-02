package ma.salamgaz.gwic.common.model.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Setter;
import ma.salamgaz.gwic.common.model.entity.generic.RefEntity;

/**
 * 
 * @author m.wannas
 *
 */
@Setter
@Entity
@Table(name = "periode", schema = "gwic")
public class Periode extends RefEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6031176976071844210L;
	private Integer start;
	private Integer day;
	private Integer end;
	private Date from;
	private Integer tolerance;
	private Integer daysNumber;
	private ConditionCommerciale conditionCommerciale;
	
	@Column(name="periode_start")
	public Integer getStart() {
		return start;
	}
	@Column(name="periode_day")
	public Integer getDay() {
		return day;
	}
	@Column(name="periode_end")
	public Integer getEnd() {
		return end;
	}
	@Column(name="periode_from")
	public Date getFrom() {
		return from;
	}
	public Integer getTolerance() {
		return tolerance;
	}
	public Integer getDaysNumber() {
		return daysNumber;
	}
	
    @ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "condition_commerciale_id", foreignKey = @ForeignKey(name = "FK_PERIODE_COND_COMMERCIALE_ID")) 
	public ConditionCommerciale getConditionCommerciale() {
		return conditionCommerciale;
	}
	
	
    
}
