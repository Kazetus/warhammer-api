package com.warhammer.api.config;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import static com.warhammer.api.config.Permission.ADMIN_CREATE;
import static com.warhammer.api.config.Permission.ADMIN_DELETE;
import static com.warhammer.api.config.Permission.ADMIN_READ;
import static com.warhammer.api.config.Permission.USER_READ;
import static com.warhammer.api.config.Permission.ADMIN_UPDATE;

@RequiredArgsConstructor
public enum Rank {
	 USER(Set.of(USER_READ)),
	  ADMIN(
	          Set.of(
	                  ADMIN_READ,
	                  ADMIN_UPDATE,
	                  ADMIN_DELETE,
	                  ADMIN_CREATE,
	                  USER_READ
	          )
	  )

	  ;

	  @Getter
	  private final Set<Permission> permissions;

	  public List<SimpleGrantedAuthority> getAuthorities() {
	    var authorities = getPermissions()
	            .stream()
	            .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
	            .collect(Collectors.toList());
	    authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
	    return authorities;
	  }
}
