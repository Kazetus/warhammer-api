package com.warhammer.api.service;

import com.warhammer.api.model.*;
import java.util.List;

public interface EspeceService {
	Espece creer(Espece espece);
	
	List<Espece> lire();
	
	Espece modifier(Long id_espece, Espece espece);
	
	String supprimer(Long id_espece);
}