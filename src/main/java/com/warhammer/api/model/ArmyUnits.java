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
public class ArmyUnits {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long idArmyUnits;
	private int quantity;
	@Column(name="id_army")
	private int idArmy;
	@Column(name="id_units")
	private int idUnits;
	public ArmyUnits(int quantity, Long idUnits, Long idArmy) {
		this.quantity = quantity;
		this.idUnits = Math.toIntExact(idUnits);
		this.idArmy = Math.toIntExact(idArmy);
	}
}
