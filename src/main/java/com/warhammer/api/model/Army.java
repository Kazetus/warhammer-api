package com.warhammer.api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Getter
@Setter
@Table(name = "army")
public class Army {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	 private long idArmy;
	
	@Column(name="army_name")
	 private String armyName;
	
	@Column(name="id_user")
	 private int idUser;
	@Transient
	private String faction;
	@Transient
	private String alliance;
	@Transient
	private String edition;
	@Transient
	private Iterable<Units> units;
	
	public void setUnits(Iterable<Units> units) {
		this.units = units;
	}
	public long getIdFaction() {
		int value = 0;
		for(Units unit : units) {
			value = unit.getIdFaction();
		}
		return Long.valueOf(value);
	}
}
