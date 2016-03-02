package modele;

public class ReservationBD extends PJS4BD {

	public static void sauverEnBaseReservation(int idPersonne, int idSalle, int duree) {
		String requete = "INSERT INTO `RESERVATION` (`IdPersonne`, `IdSalle`, `DureeReservation`) VALUES "
				+ "('"+ idPersonne +"','" + idSalle + "','" + duree + "')";
		sql(requete);
	}

}
