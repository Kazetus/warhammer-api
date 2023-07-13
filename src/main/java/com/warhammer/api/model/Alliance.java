package com.warhammer.api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Column;
import jakarta.persistence.GenerationType;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name= "alliance")
@NoArgsConstructor
public class Alliance {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idAlliance;

	@Column(name="alliance_name")
	private String allianceName;
}