package serveur;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class ServiceRéservation implements Runnable{

	private Socket client;
	private boolean fini = false;
	
	public ServiceRéservation(Socket accept) {
		client = accept;
	}

	
	public void run() {
		System.err.println("Nouveau client : " + this.client.getInetAddress());
		
		try {
			BufferedReader in = new BufferedReader (new InputStreamReader(client.getInputStream()));
			PrintWriter out = new PrintWriter (client.getOutputStream ( ), true);

			while (!fini) { 
				out.println("Voici les salles disponibles : ##" + affichageSalles() + "##Veuillez choisir l'indice de la salle que vous souhaitez réserver.");
				out.flush();
				int choixSalle = Integer.parseInt(in.readLine());
				out.println(salleChoisie(choixSalle));
				out.flush();
				char c = in.readLine().charAt(0);
				if (c == 'o'){
					out.println("Veuillez renseigner votre nom de famille.");
					out.flush();
					String nom = in.readLine();
					reservation(choixSalle , nom);
					out.println(recapitulatif(choixSalle));
					enregistrement(choixSalle);
					fini = true;
				} else {
					
				}
				
				
			}
			client.close();
		} catch (IOException e) {}	
		
		
	}
	
	
	public String affichageSalles(){
		String res ="";
		for(int i = 0; i < lesSalles.size() -1; ++i){
			res += i +" - Salle n° " + lesSalles.get(i).getNumSalle() + " - Places : " + lesSalles.get(i).getNbPlaces() +"##";
		}
		return res;
	}
	
	public String salleChoisie(int i){
		return "Est ce bien la salle n° " + lesSalles.get(i).getNumSalle() + " ayant "+ lesSalles.get(i).getNbPlaces() + " places ? (o/n)";
	}
	
	public void reservation (int i, String name){
		lesSalles.get(i).ReserverSalle(name);
	}
	
	
	public String recapitulatif(int i){
		return "Récapitulatif : ## - Numéro de salle : "+ lesSalles.get(i).getNumSalle() + "## - Nombre de place : " + lesSalles.get(i).getNbPlaces() + "## - Réservée par : " + lesSalles.get(i).getReservant();
	}
	
	public void enregistrement(int num){
		 try {
			FileWriter fw = new FileWriter("SallesRéservées.txt", true);
			BufferedWriter bw = new BufferedWriter (fw) ;
			PrintWriter pw = new PrintWriter (bw) ;
			pw.println(lesSalles.get(num).getNumSalle()+";"+lesSalles.get(num).getNbPlaces()+";"+lesSalles.get(num).getReservant()+"\n");
		} catch (IOException e) {
			System.out.println("Erreur dans la création du fichier");
		} 
	}
	public void lancer(){
		(new Thread(this)).start();		
	}
	

}
