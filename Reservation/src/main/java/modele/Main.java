package modele;

import dao.*;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.List;

import javax.persistence.EntityManager;

import Service.ServicePassager;
import Service.ServiceVol;

public class Main {

	static EntityManager em = null;
	static SimpleDateFormat formater = null;
	static boolean ui;

	public static void main(String[] args) throws SQLException, ParseException {

		EntityManager em = DBHelper.createEntityManager();
		em.getTransaction().begin();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

		Vol v1 = new Vol("0001", "A777", 77, "Orl�ans", "Montpellier", formatter.parse("11/01/2019"));
		List<Vol> vols = new ArrayList<Vol>();
		vols.add(v1);
		Passager p1 = new Passager("Carreaux", "Baptiste", 23, v1);
		Passager p2 = new Passager("Rouer", "Elie", 23, v1);
		Passager p3 = new Passager("Montet", "Benjamin", 26, v1);
		List<Passager> passagerVol0001 = new ArrayList<Passager>();
		passagerVol0001.add(p1);
		passagerVol0001.add(p2);
		passagerVol0001.add(p3);

		for (Vol v : vols) {
			ServiceVol.creationVol(v);
		}
		v1.ajoutPassager(p1);
		v1.ajoutPassager(p2);
		v1.ajoutPassager(p3);

		for (Passager p : passagerVol0001) {
			ServicePassager.creationPassager(p);
		}

		/* DEBUT DE l'UI */
		while (!ui) {
			Ui.afficheMenuGeneral();
		}
		em.close();
	}
}