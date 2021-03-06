package fr.diginamic.tpbanque.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Banque {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	private Integer id;
	@Column(name="NOM")
	private String nom;
	
	
	public Banque() {
		
	}

	public Banque(String nom) {
		this();
		this.nom = nom;
	}
	
	

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	
	
	public void addClient(Client client) {
		client.setBanque(this);
	}
	@Override
	public String toString() {
		return "Banque [id=" + id + ", nom=" + nom +  "]";
	}

}
