package fr.dta.modele;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Employee {

	private Long id;
	private String prenom;
	private String nom;
	private String numeroSecu;
	private BigDecimal salaire;
	private LocalDate dateEmbauche;

	public Employee(Long i, String p, String n, String num, BigDecimal s, LocalDate d) {
		id = i;
		prenom = p;
		nom = n;
		numeroSecu = num;
		salaire = s;
		dateEmbauche = d;
	}
	
	
	public String toString() {
		return id+" "+prenom+" "+nom+" "+numeroSecu+" "+salaire+" "+dateEmbauche;
	}

	public LocalDate getDateEmbauche() {
		return dateEmbauche;
	}

	public void setDateEmbauche(LocalDate dateEmbauche) {
		this.dateEmbauche = dateEmbauche;
	}

	public BigDecimal getSalaire() {
		return salaire;
	}

	public void setSalaire(BigDecimal selaire) {
		this.salaire = selaire;
	}

	public String getNumeroSecu() {
		return numeroSecu;
	}

	public void setNumeroSecu(String numeroSecu) {
		this.numeroSecu = numeroSecu;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


}
