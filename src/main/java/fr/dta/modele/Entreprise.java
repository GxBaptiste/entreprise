package fr.dta.modele;

import java.util.List;

public class Entreprise {

	private Long id;
	private String nom;
	private List<Employee> listeEmp;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public List<Employee> getListeEmp() {
		return listeEmp;
	}

	public void setListeEmp(List<Employee> listeEmp) {
		this.listeEmp = listeEmp;
	}

}
