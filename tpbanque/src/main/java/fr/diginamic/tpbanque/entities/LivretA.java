package fr.diginamic.tpbanque.entities;

import javax.persistence.Entity;

@Entity
public class LivretA extends Compte {

	private Double taux;

	public LivretA() {
		super();
	}

	public LivretA(Double taux) {
		super();
		this.taux = taux;
	}
	
	public LivretA(String numero, Double  solde,Double taux) {
		super(numero, solde);
		this.taux = taux;
	}

	public Double getTaux() {
		return taux;
	}

	public void setTaux(Double taux) {
		this.taux = taux;
	}

	@Override
	public String toString() {
		return "LivretA [taux=" + taux + "]";
	}

}
