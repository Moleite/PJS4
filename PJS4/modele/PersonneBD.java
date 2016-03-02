package modele;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.exceptions.MySQLSyntaxErrorException;

public class PersonneBD extends PJS4BD{

	public static void sauverEnBasePersonne(String nom, String prenom, String pseudo, String mdp, String departement) {
		String requete = "INSERT INTO `PERSONNE` (`NomPers`, `PrenomPers`, `Pseudo`, `Mdp`, `Departement`) VALUES "
				+ "('"+ nom +"','" + prenom + "','" + pseudo + "','" + mdp + "','" + departement + "')";
		sql(requete);
	}

	public static boolean verifCompte(String pseudo, String mdp) {
		String requete = "SELECT * FROM personne WHERE Pseudo = '" + pseudo + "' AND Mdp = '" + mdp + "'";
		
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
				System.out.println("Erreur : Utilisateur inconnu!");
				return false;
			} else {
				return true;
			}
			
		} catch (MySQLSyntaxErrorException e) {
			System.out.print("Erreur de syntaxe SQL");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			sqlClose();
		}
		
		return false;
	}
}
