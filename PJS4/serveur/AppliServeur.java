package serveur;

import java.io.IOException;

public class AppliServeur {
	
	private static final int PORT = 1234;
	public static void main(String[] args) {
		try {
			new Serveur(PORT).lancer();
			System.out.println("Serveur lance sur le port " + PORT);

				
		} catch (IOException e) {
				System.err.println("Pb lors de la création du serveur : " +  e);			
		}
			
			

		}

	}
