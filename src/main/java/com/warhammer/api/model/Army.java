package com.warhammer.api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;/*
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;*/
import jakarta.persistence.Table;

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
	/*
	@JoinTable(name="army_units",
			joinColumns= {@JoinColumn(name="id_army")})
	private ArmyUnits armyUnits;
	*/
}
