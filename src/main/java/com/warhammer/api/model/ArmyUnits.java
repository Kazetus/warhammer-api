package com.warhammer.api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
/*
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
*/
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Getter
@Setter
@Table(name = "army_units")
public class ArmyUnits {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idArmyUnits;
	private int quantity;
	@Column(name="id_army")
	private int idArmy;
	@Column(name="id_units")
	private int idUnits;
	/*
	@JoinTable(name="army",
			joinColumns= {@JoinColumn(name="id_army")})
	private Army army;
	
	@JoinTable(name="units",
			joinColumns= {@JoinColumn(name="id_units")})
	private Units units;
	*/
}
