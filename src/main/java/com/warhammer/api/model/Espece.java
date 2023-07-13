package com.warhammer.api.model;

// import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name= "espece")
@Getter
@Setter
@NoArgsConstructor
public class Espece {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idEspece;

	// @JoinColumn(name= "id_equipement")
	// @MapsId
	@Column(name="name_espace", length = 25)
	private String nameEspece;

    @Column(name="image", length = 25)
	private String image;
    
    @Column(name="att_physique")
	private int attPhysique;
	
    @Column(name="def_physique")
	private int defPhysique;
	
    @Column(name="att_magique")
	private int attMagique;
    
    @Column(name="def_magique")
	private int defMagique;
    
    @Column(name="vitesse")
	private int vitesse;
    
    @Column(name="pv")
	private int pv;
    
    @Column(name="jouable")
	private int jouable;
    
    @Column(name="resume", length = 800)
	private String resume;
    
    @Column(name="caracteristique", length = 50)
	private String caracteristique;
    
    @Column(name="mythologie", length = 500)
	private String mythologie;

    @Column(name="presence_media", length = 100)
	private String presenceMedia;
}