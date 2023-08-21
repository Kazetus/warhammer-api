package com.warhammer.api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Getter
@Setter
@Table(name = "units")
public class Units {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	 private long idUnits;
	@Column(name="units_name")
	private String unitsName;
	@Column(name="nombre_figurine")
	private int nombreFigurine;
	private int points;
	@Column(name="id_faction")
	private int idFaction;
}
