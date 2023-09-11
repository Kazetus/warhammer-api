package com.warhammer.api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "army")
public class UserArmy {
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
	private Iterable<UnitsArmy> units;
	
	public void setUnits(Iterable<UnitsArmy> units) {
		this.units = units;
	}
	public long getIdFaction() {
		int value = 0;
		for(UnitsArmy unit : units) {
			value = unit.getIdFaction();
		}
		return Long.valueOf(value);
	}
}
