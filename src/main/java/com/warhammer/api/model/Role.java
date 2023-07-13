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
@Table(name = "role")
public class Role{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	 private long idRole;
	@Column(name="role_name")
	 private String roleName;
}
