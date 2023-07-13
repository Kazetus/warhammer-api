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
@Table(name = "edition")
public class Edition {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	 private long idEdition;
	
	@Column(name="edition_name")
	 private String editionName;
}
