import java.io.*;

public class Main {
	public static void main(final String[] args) {
		
		// Inizializzazione variabili
		String via = "";
		int civico = 0;
		int piano = 0;
		Boolean flag = true;

		// Inserimento dimensione dell'array
		System.out.print("Inserisci numero di scuole e plessi da creare: ");
		int n = Integer.parseInt(GetStringInput());

		// Dichiarazione array e distretto
		Scuola arrayScuola[] = new Scuola[n];
		Plesso arrayPlesso[] = new Plesso[n];
		Distretto distretto = new Distretto(n);

		// Ciclo for per inserimento delle scuole
		for (int i = 0; i < n; i++) {

			// Dichiarazione oggetto scuola temporaneo
			Scuola scuola = null;

			// Inserimento di Scuola
			do {
				System.out.print("\nInserisci via e civico:\n");
				via = GetStringInput();
				civico = Integer.parseInt(GetStringInput());
				
				// Try e catch per inizializzazione dell'oggetto scuola
				try {
					scuola = new Scuola(via, civico);
					flag = false;
				} catch (CivicNumberException cne) {
					System.err.print("Errore. Numero civico non valido");
					flag = true;
				} catch (Exception e) {
					System.err.print("Errore generico");
					flag = true;
				}
			} while (flag);

			// Inserimento di scuola nell'array
			arrayScuola[i] = scuola;
			
			// Invocazione metodo per scrittura su file
			WriteOnFile(scuola.getVia(), true);
			WriteOnFile("" + scuola.getCivico(), true);
		}

		// Ciclo for per oggetti plesso
		for (int i = 0; i < n; i++) {
			Plesso plesso = null;

			// Inserimento di Plessi
			do {
				System.out.print("\nInserisci via, civico e piani:\n");
				via = GetStringInput();
				civico = Integer.parseInt(GetStringInput());
				piano = Integer.parseInt(GetStringInput());
				
				// Try e catch per inizializzazione dell'oggetto plesso
				try {
					plesso = new Plesso(via, civico, piano);
					flag = false;
				} catch (CivicNumberException cne) {
					System.err.print("Errore. Numero civico non valido");
					flag = true;
				} catch (PianoException pe) {
					System.err.print("Errore. Numero piani non valido");
					flag = true;
				} catch (Exception e) {
					System.err.print("Errore generico");
					flag = true;
				}
			} while (flag);

			// Inserimento di plesso nell'array
			arrayPlesso[i] = plesso;
			
			// Invocazione metodo per scrittura su file
			WriteOnFile(plesso.getVia(), true);
			WriteOnFile("" + plesso.getCivico(), true);
			WriteOnFile("" + plesso.getPiani(), true);
		}

		// Inserimento degli array nell'oggetto distretto e stampa finale
		distretto.setArrayPlesso(arrayPlesso);
		distretto.setArrayScuola(arrayScuola);
		distretto.printAllDistretto();
	}

	// Metodo per input che ritorna una stringa
	public static String GetStringInput() {
		
		// Inizializzazione oggetti per l'input da tastiera
		InputStreamReader inputStream = new InputStreamReader(System.in);
		BufferedReader bufferedReader = new BufferedReader(inputStream);
		String input = "";
		Boolean flag;

		// Acquisizione valore da tastiera e geatione dell'eccezione
		do {
			try {
				input = bufferedReader.readLine();
				flag = false;
			} catch (IOException ioe) {
				flag = true;
			}
		} while (flag);
		
		// Ritorno della stringa acquisita
		return input;
	}

	// Metodo per scrivere una stringa su file
	public static void WriteOnFile(String string, Boolean newLine) {
		
		// Inizializzazione di fileWriter
		FileWriter fileWriter = null;
		try {
			fileWriter = new FileWriter("scuole-plessi.txt", true);
			
			// Scrittura della stringa su file
			fileWriter.write(string);
			
			// Se viene passato un valore true, passa al rigo successivo
			if (newLine) {
				fileWriter.write("\n");
			}
			// Chiusura file
			fileWriter.close();
		} catch (IOException e) {
			System.err.print("Errore IOException");
		}
	}

}
