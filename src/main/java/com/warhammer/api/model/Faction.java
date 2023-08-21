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
@Table(name = "faction")
public class Faction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	 private long idFaction;
	
	@Column(name="faction_name")
	private String factionName;
	@Column(name="id_alliance")
	private int idAlliance;
	@Column(name="id_edition")
	private int idEdition;
}
