package com.warhammer.api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.warhammer.api.model.Espece;
import com.warhammer.api.repository.EspeceRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EspeceServiceImpl implements EspeceService{

	private EspeceRepository especeRepository;
	
	@Override
	public Espece creer(Espece espece) {
		return especeRepository.save(espece);
	}

	@Override
	public List<Espece> lire() {
		return especeRepository.findAll();
	}

	@Override
	public Espece modifier(Long id_espece, Espece espece) {
		return especeRepository.findById(id_espece)
				.map(p-> {
					p.setNameEspece(espece.getNameEspece());
					p.setImage(espece.getImage());
					p.setAttPhysique(espece.getAttPhysique());
					p.setDefPhysique(espece.getDefPhysique());
					p.setAttMagique(espece.getAttMagique());
					p.setDefMagique(espece.getDefMagique());
					p.setVitesse(espece.getVitesse());
					p.setPv(espece.getPv());
					p.setJouable(espece.getJouable());
					p.setResume(espece.getResume());
					p.setCaracteristique(espece.getCaracteristique());
					p.setMythologie(espece.getMythologie());
					p.setPresenceMedia(espece.getPresenceMedia());
					return especeRepository.save(p); 			
				}).orElseThrow(() -> new RuntimeException("Espèce non trouvé"));
	}

	@Override
	public String supprimer(Long id_espece) {
		especeRepository.deleteById(id_espece);
		return "Espèce supprimé";
	}

}
