package modele;

public class SalleBD extends PJS4BD{

	public static void sauverEnBaseSalle(int etage, int salle, String date, int places) {
		String requete = "INSERT INTO `SALLE` (`NumEtage`, `NumSalle`, `HeureReserv`, `NbPlace`) VALUES "
				+ "('"+ etage +"','" + salle + "','" + date + "','" + places + "')";
		sql(requete);
	}

}
