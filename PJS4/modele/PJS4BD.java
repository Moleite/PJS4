package modele;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.mysql.jdbc.exceptions.MySQLSyntaxErrorException;

public class PJS4BD {

	// Information d'accès à la base de données
	protected static String login = "benharka";
	protected static String passwd = "benharka";
	protected static String url = "jdbc:mysql://127.0.0.1/pjs4";
	
	protected static Connection cn =null;
	protected static Statement st =null;
	protected static ResultSet rs =null;
	
	
	public static void main(String[] args) {
		//ENREGISTRER
		//sauverEnBasePersonne("Test","Test","Test","Test","Test");
		//sauverEnBaseSalle(1,1,today(),1);
		//sauverEnBaseReservation(1,1,1);

		//LIRE
		lireTable("personne");
		System.out.println();
		
		lireTable("salle");
		System.out.println();
		
		lireTable("reservation");
		System.out.println();
		
	}

	public static String today() {
		SimpleDateFormat d = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		return d.format(date);
	}
	
	public static void sql(String requete) {
		try {
			// Etape 1 : Chargement du driver
			Class.forName("com.mysql.jdbc.Driver");

			// Etape 2 : récupération de la connexion
			cn = DriverManager.getConnection(url, login, passwd);

			// Etape 3 : Création d'un statement
			st = cn.createStatement();
			
			// Etape 4 : exécution requête
			st.executeUpdate(requete);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			sqlClose();
		}
	}
	public static void sqlClose() {
		try {
		// Etape 6 : libérer ressources de la mémoire.
			cn.close();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void lireTable(String table) {
		String requete = "SELECT * FROM "+ table;
		ResultSet rs =null;
		
		try {
			// Etape 1 : Chargement du driver
			Class.forName("com.mysql.jdbc.Driver");

			// Etape 2 : récupération de la connexion
			cn = DriverManager.getConnection(url, login, passwd);

			// Etape 3 : Création d'un statement
			st = cn.createStatement();

			// Etape 4 : exécution requête
			rs = st.executeQuery(requete);

			// Si récup données alors étapes 5 (parcours Resultset)
			if (!rs.next()) {
				System.out.println("Cette table est vide.");
			}
			rs.previous();
			
			while (rs.next()) {
				switch (table) {
					case "personne":
						System.out.print("IdPersonne : " + rs.getString("IdPersonne") + " ");
						System.out.print("NomPers : " + rs.getString("NomPers") + " ");
						System.out.print("PrenomPers : " + rs.getString("PrenomPers") + " ");
						System.out.print("Mdp : " + rs.getString("Mdp") + " ");
						System.out.print("Departement : " + rs.getString("Departement"));
						System.out.println();
					break;
					
					case "salle":
						System.out.print("IdSalle : " + rs.getString("IdSalle") + " ");
						System.out.print("NumEtage : " + rs.getString("NumEtage") + " ");
						System.out.print("NumSalle : " + rs.getString("NumSalle") + " ");
						System.out.print("HeureReserv : " + rs.getString("HeureReserv") + " ");
						System.out.print("NbPlace : " + rs.getString("NbPlace") + " ");
						System.out.println();
					break;
					
					case "reservation":
						System.out.print("IdReservation :" + rs.getString("IdReservation") + " ");
						System.out.print("IdPersonne : " + rs.getString("IdPersonne") + " ");
						System.out.print("IdSalle : " + rs.getString("IdSalle") + " ");
						System.out.print("DureeReservation : " + rs.getString("DureeReservation") + " ");
						System.out.println();
					break;	
				}	
			}
			
		} catch (MySQLSyntaxErrorException e) {
			System.out.print("Cette table n'existe pas dans la BDD.");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			sqlClose();
		}
	}
	
	public Connection getCn() {
		return cn;
	}
	public Statement getSt() {
		return st;
	}

	public static boolean verifCompte(String pseudo, String mdp) {
		// TODO Auto-generated method stub
		return false;
	}
	
}