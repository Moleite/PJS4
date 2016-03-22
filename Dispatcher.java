package model;

import java.util.List;

import exception.IntrouvableException;
import persistence.*;


/**
 * Classe générale de l'application en charge de déléguer les différentes actions possibles sur le modèle
 */
public class Dispatcher {
	
	private static Dispatcher instance = new Dispatcher();
	private static UserDAO compteDao = UserDAO.getInstance();
	private static ReservationDAO reservationDao = ReservationDAO.getInstance();
	private static VolDAO volDao = VolDAO.getInstance();

	/**
	 * Récupérer un compte à partir de son login et de son mot de passe
	 * 
	 * @param login Login supposé du compte
	 * @param mdp Mot de passe supposé du compte
	 * @return le compte correspondant aux paramètres
	 * @throws CompteIntrouvableException si aucun voyageur ne possède cette combinaison
	 */
	public User getCompte(String login, String mdp)
		 throws CompteIntrouvableException{
		User compte = compteDao.findUser(login, mdp);
		return compte;
	}
	
	/**
	 * Récupérer une réservation à partir de son numéro unique
	 * 
	 * @param numeroReservation Numéro unique de la réservation
	 * @return la réservation correspondante au numéro
	 * @throws IntrouvableException si la réservation ou ses différentes composantes (vol, compte) sont introuvables
	 */
	public Reservation getReservation(int numeroReservation) throws IntrouvableException {
		return reservationDao.trouverReservation(numeroReservation);
	}
	
	/**
	 * Récupérer l'ensemble des réservation d'un compte
	 * 
	 * @param compte le compte dont on recherche les réservation
	 * @return une liste de réservation
	 */
	public List<Reservation> getReservations(User compte) {
		return reservationDao.recupererReservations(compte);
	}
	
	/**
	 * Récupérer un vol à partir de son numéro unique
	 * 
	 * @param numeroVol le numéro du vol
	 * @return le vol correspondant
	 * @throws IntrouvableException si le vol correspondant est introuvable
	 */
	public Room getVol(int numeroVol) throws IntrouvableException {
		return volDao.trouverVol(numeroVol);
	}
	
	/**
	 * Récupérer un un vol à partir d'une réservation
	 * 
	 * @param reserv la réservation dont on recherche le vol
	 * @return le vol correspondant à la réservation
	 * @throws IntrouvableException si le vol 
	 */
	public Room getVol(Reservation reserv) throws IntrouvableException {
		return volDao.trouverVol(reserv);
	}
	
	/**
	 * Récupérer l'ensemble des v suivant certains critères
	 * 
	 * 
	 * @param villeDestination la ville de destination du vol
	 * @param dateDebut le vol doit avoir lieu après cette date (format jj/mm/yyyy)
	 * @param nbPlace le nombre de place du vol (format jj/mm/yyyy)
	 * @return une liste de vols
	 */
	public List<Room> getVols(String villeDestination, String dateDebut, int nbPlace){
		return volDao.recupererVols(villeDestination, dateDebut, nbPlace);
	}
	
	/**
	 * Ajouter un comtpe
	 * 
	 * @param login le login choisi pour le compte
	 * @param mdp le mot de passe choisi pour le compte
	 * @param nom le nom choisi pour le compte
	 * @param prenom le prenom choisi pour le compte
	 * @param le role du compte (utilisateur ou admin)
	 * @return le compte ainsi ajouté
	 * @throws CompteExisteDejaException si un voyageur correspondant au login existe déjà
	 */
	public User ajouterCompte(String login, String mdp, String nom, String prenom, String role) 
			throws CompteExisteDejaException {
		User compte = new User(login, mdp, nom, prenom,role );
		compteDao.addUser(compte);
		return compte;
	}
	
	/**
	 * Ajouter un Vol
	 * 
	 * @param String destination de la réservation
	 * @param String dateDebut de la réservation
	 * @param int nombrePlaces de la réservation
	 * @param float prix de la réservation
	 */
	public Room ajouterVol(String destination, String dateDebut, int nombrePlaces, float prix) {
		Room vol = new Room(5, destination, dateDebut, nombrePlaces, prix);
		volDao.ajouterVol(destination, dateDebut, nombrePlaces, prix);
		return vol;
	}
	
	/**
	 * Ajouter une réservation
	 * 
	 * @param vol vol de la réservation
	 * @param compte comtpe futur de la réservation
	 */
	public void ajouterReservation(Room vol, User compte, int nbPlaces) {
		reservationDao.ajouterReservation(vol, compte, nbPlaces);
	}

	/**
	 * Supprimer une réservation
	 * 
	 * @param reservation la réservation à supprimer
	 */
	public void removeReservation(Reservation reservation) {
		reservationDao.supprimerReservation(reservation);
	}
	
	/**
	 * Récupérer l'instance unique de SNGF (Singleton)
	 * 
	 * @return l'instance
	 */
	public static Dispatcher getInstance() {
		return instance;
	}

}