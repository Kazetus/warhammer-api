package com.warhammer.api.controller;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;


import com.warhammer.api.model.Profil;
import com.warhammer.api.service.ProfilService;

@RestController
public class ProfilController {
	
	@Autowired
	private ProfilService profilService;
	
	@GetMapping("/profils")
	public Iterable<Profil> getProfil() {
		return profilService.getProfil();
	}
	
	@GetMapping("/profils/{id}")
	public Profil getProfil(@PathVariable("id") final Long id){
		Optional<Profil> profil = profilService.getProfil(id);
		if(profil.isPresent()) {
			return profil.get();
		} else {
			return null;
		}
	}
	@PutMapping("/profils/{id}")
	public Profil updateProfil(@PathVariable("id") final Long id, @RequestBody Profil profil) {
		//profil.setPassword(passwordEncoder.encode(profil.getPassword()));
		Optional<Profil> e = profilService.getProfil(id);
		if(e.isPresent()) {
			Profil currentProfil = e.get();
			
			String username = profil.getUsername();
			if(username != null) {
				currentProfil.setUsername(username);
			}
			String mail = profil.getMail();
			if(mail != null) {
				currentProfil.setMail(mail);;
			}
			boolean mineurMajeur = profil.getMineurMajeur();
			currentProfil.setMineurMajeur(mineurMajeur);
			String password = profil.getPassword();
			if(password != null) {
				currentProfil.setPassword(password);
			}
			int age = profil.getAge();
			if(age != 0) {
				currentProfil.setAge(age);
			}
			boolean genreProfil = profil.getGenreProfil();
			currentProfil.setGenreProfil(genreProfil);
			int idRole = profil.getIdRole();
			if(idRole != 0) {
				currentProfil.setIdRole(idRole);
			}
			profilService.saveProfil(currentProfil);
			return currentProfil;
		} else {
			return null;
		}
	}
	@PatchMapping("/profils/{id}")
	public Profil patchProfil(@PathVariable("id") final Long id, @RequestBody Profil profil){
		//profil.setPassword(passwordEncoder.encode(profil.getPassword()));		
		Optional<Profil> e = profilService.getProfil(id);
		if(e.isPresent()) {
			Profil currentProfil = e.get();
			
			String username = profil.getUsername();
			String mail = profil.getMail();
			String password = profil.getPassword();
			int age = profil.getAge();
			int idRole = profil.getIdRole();
			boolean genreProfil = profil.getGenreProfil();
			boolean mineurMajeur = profil.getMineurMajeur();
			if(username != null && mail != null && password != null && age != 0 && idRole != 0) {
				currentProfil.setUsername(username);
				currentProfil.setMail(mail);
				currentProfil.setMineurMajeur(mineurMajeur);
				currentProfil.setPassword(password);
				currentProfil.setAge(age);
				currentProfil.setGenreProfil(genreProfil);
				profilService.saveProfil(currentProfil);
			return currentProfil;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}
	@DeleteMapping("/profils/{id}")
	public void deleteProfil(@PathVariable("id") final Long id) {
		profilService.deleteProfil(id);
	}
}
