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
@Table(name = "army_units")
public class UnitsArmy {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idArmyUnits;
	@Column(name="id_army")
	private int idArmy;
	@Column(name="id_units")
	private int idUnits;
	@Column(name="units_name")
	private String unitsName;
	@Column(name="nombre_figurine")
	private int nombreFigurine;
	private int points;
	@Column(name="id_faction")
	private int idFaction;
}
