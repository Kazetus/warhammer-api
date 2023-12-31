package com.warhammer.api.model;

import java.util.Collection;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.warhammer.api.config.Rank;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class User implements UserDetails{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	 private long idUser;
	 private String username;
	 private String mail;
	 private String password;
	 @Column(name="id_role")
	 private int idRole;
	 @Transient
	 private Iterable<UserArmy> army;
	 @Enumerated(EnumType.STRING)
	 @Transient
	 private Rank rank;
	 
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}
	// Gives the user his ranks to defines accessible routes
	public Rank getRank() {
		if(this.idRole == 1) {
			this.setRank(Rank.ADMIN);
		} else {
			this.setRank(Rank.USER);
		}
		return this.rank;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		if(this.idRole == 1) {
			this.setRank(Rank.ADMIN);
		} else {
			this.setRank(Rank.USER);
		}
		return rank.getAuthorities();
	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
}
