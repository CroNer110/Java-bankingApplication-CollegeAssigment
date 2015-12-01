package hr.tvz.programiranje.java.glavna;

import hr.tvz.programiranje.java.banka.*;
import hr.tvz.programiranje.java.osoba.Osoba;
import hr.tvz.programiranje.java.sortiranje.SortiranjeTransakcija;
import hr.tvz.programiranje.java.iznimke.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

/**
 * Glavna (main) metoda u aplikaciji.
 * Uèitava podatke dvaju bankovnih raèuna, deviznog i tekuæeg, te vrši transakcije izmeðu istih.
 * @author Josip
 * @version 0.9
 *
 */

@SuppressWarnings("unused")
public class Glavna {
	
	private static final Logger logger = LoggerFactory.getLogger(Glavna.class);

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		BigDecimal iznos = null;
		Scanner input = new Scanner(System.in);
		int redniBrojRacuna = 0, brojUnosaObadvaRacuna = 0;
		Racun r1 = null, r2 = null;
		File uneseniPodaci = new File("uneseniPodaci.txt");
		
		try(FileWriter pisac = new FileWriter(uneseniPodaci)){
			
			List<Racun> listaRacuna = new ArrayList<>();
			
			//Unos raèuna
			do {
				System.out.println("Unesite vrstu raèuna (T - tekuæi); ostalo - devizni): ");
				String vrstaRacuna = input.nextLine();
				if(vrstaRacuna.equals("T")) {
					++redniBrojRacuna;
					zapisiUDatoteku(pisac, ("Vrsta " + redniBrojRacuna + ". raèuna: " + vrstaRacuna));
					
					r1 = unosTekucegRacuna(input, redniBrojRacuna);
					zapisiUDatoteku(pisac, ("Ime vlasnika " + redniBrojRacuna + ". raèuna: " + r1.getVlasnikRacuna().getIme()));
					zapisiUDatoteku(pisac, ("Prezime vlasnika " + redniBrojRacuna + ". raèuna: " + r1.getVlasnikRacuna().getPrezime()));
					zapisiUDatoteku(pisac, ("OIB vlasnika " + redniBrojRacuna + ". raèuna: " + r1.getVlasnikRacuna().getOib()));
					zapisiUDatoteku(pisac, ("Stanje " + redniBrojRacuna + ". raèuna: " + r1.getStanjeRacuna()));
					zapisiUDatoteku(pisac, ("Broj " + redniBrojRacuna + ". raèuna: " + ((TekuciRacun)r1).getBrojRacuna()));
					
					
					
					listaRacuna.add(redniBrojRacuna-1, r1);
				}
				else {
					++redniBrojRacuna;
					zapisiUDatoteku(pisac, ("Vrsta " + redniBrojRacuna + ". raèuna: " + vrstaRacuna));
					
					r2 = unosDeviznogRacuna(input, redniBrojRacuna);
					zapisiUDatoteku(pisac, ("Ime vlasnika " + redniBrojRacuna + ". raèuna: " + r2.getVlasnikRacuna().getIme()));
					zapisiUDatoteku(pisac, ("Prezime vlasnika " + redniBrojRacuna + ". raèuna: " + r2.getVlasnikRacuna().getPrezime()));
					zapisiUDatoteku(pisac, ("OIB vlasnika " + redniBrojRacuna + ". raèuna: " + r2.getVlasnikRacuna().getOib()));
					zapisiUDatoteku(pisac, ("Stanje " + redniBrojRacuna + ". raèuna: " + r2.getStanjeRacuna()));
					if(vrstaRacuna == "T"){
						zapisiUDatoteku(pisac, ("Broj " + redniBrojRacuna + ". raèuna: " + ((TekuciRacun)r2).getBrojRacuna()));
					}
					else{
						zapisiUDatoteku(pisac, ("IBAN " + redniBrojRacuna + ". raèuna: " + ((DevizniRacun)r2).getIban()));
						zapisiUDatoteku(pisac, ("Valuta " + redniBrojRacuna + ". raèuna: " + ((DevizniRacun)r2).getValuta()));
					}
					
					listaRacuna.add(redniBrojRacuna-1, r2);
				}
				brojUnosaObadvaRacuna++;
				
				if(brojUnosaObadvaRacuna == 2 && !(listaRacuna.get(0) instanceof TekuciRacun) && ((listaRacuna.get(1) instanceof DevizniRacun) || (listaRacuna.get(1) instanceof TekuciRacun))) {
					System.out.println("Oba raèuna moraju biti tekuæi ili prvi tekuæi, a drugi devizni! Morate ponovno unijeti oba raèuna.");
					brojUnosaObadvaRacuna = 0;
					redniBrojRacuna = 0;
				}
				
			}while(brojUnosaObadvaRacuna < 2);
			
			
			//Transakcija
			SortedSet<Transakcija> setTransakcija = new TreeSet<>(new SortiranjeTransakcija());
			
			char potvrdaUnosa = ' ';
			
			do{
				System.out.print("Unesite iznos transakcije (u KN): ");
				iznos = input.nextBigDecimal();
				zapisiUDatoteku(pisac, ("Iznos transakcije: " + iznos + " KN"));
				logger.info("Unesen je iznos transakcije (u kunama) sa prvog na drugi raèun: " + iznos);
				
				if(listaRacuna.get(1) instanceof TekuciRacun){
					Transakcija <TekuciRacun, TekuciRacun> transakcija1 = new Transakcija <>((TekuciRacun)r1, (TekuciRacun)r2, iznos);
					setTransakcija.add(transakcija1);
					try{
						transakcija1.provediTransakciju();
					}
					catch(NedozvoljenoStanjeRacunaException ex){
						logger.info(ex.getMessage(), ex);
						System.out.println(ex.getMessage());
					}
					System.out.println("Transakcija provedena!\n");
				}
				
				
				else {
					DeviznaTransakcija <TekuciRacun, DevizniRacun> transakcija1 = new DeviznaTransakcija<>((TekuciRacun)r1, (DevizniRacun)r2, iznos);
					setTransakcija.add(transakcija1);
					try{
						transakcija1.provediTransakciju();
					}
					catch(NedozvoljenoStanjeRacunaException ex){
						logger.info(ex.getMessage(), ex);
						System.out.println(ex.getMessage());
					}
					System.out.println("Transakcija provedena!\n");
				}
				
				System.out.println("Želite li unijeti novu transakciju (D/N)? ");
				potvrdaUnosa = input.next().charAt(0);
				if(potvrdaUnosa == 'N')
					break;
			
			}while(potvrdaUnosa == 'D');
			
			//Output
			if(listaRacuna.get(1) instanceof TekuciRacun) {
				System.out.println("Stanje na prvom raèunu: " + listaRacuna.get(0).getStanjeRacuna() + " KN");
				zapisiUDatoteku(pisac, ("Stanje na prvom raèunu: " + listaRacuna.get(0).getStanjeRacuna() + " KN"));
				
				System.out.println("Stanje na drugom raèunu: " + listaRacuna.get(1).getStanjeRacuna() + " KN");
				zapisiUDatoteku(pisac, ("Stanje na drugom raèunu: " + listaRacuna.get(1).getStanjeRacuna() + " KN"));
				
				System.out.println("\nTransakcija s najviše sredstava: " + setTransakcija.first().getIznosPromjene());
			}
			
			else {
				System.out.println("Stanje na prvom raèunu: " + listaRacuna.get(0).getStanjeRacuna() + " KN");
				zapisiUDatoteku(pisac, ("Stanje na prvom raèunu: " + listaRacuna.get(0).getStanjeRacuna() + " KN"));
				
				System.out.println("Stanje na drugom raèunu: " + listaRacuna.get(1).getStanjeRacuna() + " " + ((DevizniRacun)listaRacuna.get(1)).getValuta());
				zapisiUDatoteku(pisac, ("Stanje na prvom raèunu: " + listaRacuna.get(1).getStanjeRacuna() + " " + ((DevizniRacun)listaRacuna.get(1)).getValuta()));
				
				System.out.println("\nTransakcija s najviše sredstava: " + setTransakcija.first().getIznosPromjene());
			}
		}
		
		catch(IOException ex){
			System.err.println(ex);
			ex.printStackTrace();
		}
	}
	
	
	/**
	 * Stvara novi tekuæi raèun.
	 * Prima objekt tipa Scanner koji služi za unos s tpkovnice, te redni broj unosa. Pomoæu unesenih podataka o osobi i raèunu kreira se novi tekuæi raèun i vraæa kao vrijednost.
	 * @param input				Unos sa tipkovnice
	 * @param redniBrojRacuna	Redni broj unosa
	 * @return					Tekuæi raèun. Objekt tipa TekuciRacun
	 * @author Matej
	 */
	public static TekuciRacun unosTekucegRacuna(Scanner input, int redniBrojRacuna) {
		String ime1, prezime1, oib1;
		BigDecimal stanje1 = null;
		String brojRacuna;
		
		System.out.println("Unesite ime vlasnika " + redniBrojRacuna + ". raèuna: ");
		ime1 = input.nextLine();
		logger.info("Uneseno ime vlasnika"  + redniBrojRacuna + ". raèuna: " + ime1);			//Logiranje
		
		System.out.println("Unesite prezime vlasnika " + redniBrojRacuna + ". raèuna: ");
		prezime1 = input.nextLine();
		logger.info("Uneseno prezime vlasnika " + redniBrojRacuna + ". raèuna: " + prezime1);	//Logiranje
		
		System.out.println("Unesite OIB vlasnika " + redniBrojRacuna + ". raèuna: ");
		oib1 = input.nextLine();
		logger.info("Unesen je OIB vlasnika " + redniBrojRacuna + ". raèuna: " + oib1);		//Logiranje
		
		boolean error = false;
		
		do{
			error = false;
			System.out.println("Unesite stanje " + redniBrojRacuna + ". raèuna: ");
			
			try{
				stanje1 = input.nextBigDecimal();
			}
			catch(InputMismatchException ex){
				error = true;
				logger.info("Uneseno neispravan iznos za stanje stanje " + redniBrojRacuna + ". raèuna: " + stanje1, ex);
				input.nextLine();
			}
		}while(error == true);
		
		input.nextLine();			//Debug za preskakanje novog reda
		
		System.out.println("Unesite broj " + redniBrojRacuna + ". raèuna: ");
		brojRacuna = input.nextLine();
		logger.info("Unesen je broj " + redniBrojRacuna + ". raèuna: " + brojRacuna);			//Logiranje
		
		Osoba osoba1 = new Osoba(ime1, prezime1, oib1);
		return new TekuciRacun(brojRacuna, stanje1, osoba1);
		
	}
	
	/**
	 * Stvara novi devizni raèun.
	 * Prima objekt tipa Scanner koji služi za unos s tpkovnice, te redni broj unosa. Pomoæu unesenih podataka o osobi i raèunu kreira se novi devizni raèun i vraæa kao vrijednost.
	 * @param input				Unos sa tipkovnice
	 * @param redniBrojRacuna	Redni broj unosa
	 * @return					Devizni raèun. Objekt tipa DevizniRacun
	 * @author Matej
	 */
	public static DevizniRacun unosDeviznogRacuna(Scanner input, int redniBrojRacuna) {
		String ime2, prezime2, oib2;
		BigDecimal stanje2 = null;
		String iban;
		String valuta = "VAL";
		Valuta valutaPretvoreno = null;
		
		System.out.println("Unesite ime vlasnika " + redniBrojRacuna + ". raèuna: ");
		ime2 = input.nextLine();
		logger.info("Uneseno ime vlasnika " + redniBrojRacuna + ". raèuna: " + ime2);			//Logiranje
		System.out.println("Unesite prezime " + redniBrojRacuna + ". drugog raèuna: ");
		prezime2 = input.nextLine();
		logger.info("Uneseno ime prezime " + redniBrojRacuna + ". raèuna: " + prezime2);		//Logiranje
		System.out.println("Unesite OIB vlasnika drugog raèuna: ");
		oib2 = input.nextLine();
		logger.info("Unesen je OIB vlasnika " + redniBrojRacuna + ". raèuna: " + oib2);		//Logiranje
		
		boolean error = false;
		
		do{
			error = false;
			System.out.println("Unesite stanje " + redniBrojRacuna + ". raèuna: ");
			
			try{
				stanje2 = input.nextBigDecimal();
			}
			catch(InputMismatchException ex){
				error = true;
				logger.info("Uneseno neispravan iznos za stanje stanje " + redniBrojRacuna + ". raèuna: " + stanje2, ex);
				input.nextLine();
			}
		}while(error == true);
		
		input.nextLine();			//Debug za preskakanje novog reda
		
		System.out.println("Unesite IBAN " + redniBrojRacuna + ". raèuna: ");
		iban = input.nextLine();
		logger.info("Unesen je IBAN " + redniBrojRacuna + ". raèuna: " + iban);				//Logiranje
		
		do{
			error = false;
			System.out.println("Unesite valutu " + redniBrojRacuna + ". raèuna: ");
			
			try{
				valuta = input.nextLine();
				logger.info("Unesena je valuta " + redniBrojRacuna + ". raèuna: " + valuta);		//Logiranje
				valutaPretvoreno = DeviznaTransakcija.provjeriValutu(valuta);
			}
			catch(NepodrzanaValutaException ex){
				error = true;
				logger.info(ex.getMessage(), ex);
				System.out.println(ex.getMessage());
			}
		}while(error == true);
		
		
		Osoba osoba2 = new Osoba(ime2, prezime2, oib2);
		return new DevizniRacun(iban, valutaPretvoreno, stanje2, osoba2);
	}
	
	
	/**
	 * Zapisuje unos korisnika u tekstualnu datoteku.
	 * Datoteka se zove uneseniPodaci.txt. Prima se objekt out tipa FileWriter. Poziva se prilikom svakog korisnikovog unosa.
	 * 
	 * @param f - Izlaz preko kojeg se sadržaj zapisuje u vanjsku txt datoteku.
	 * @author Matej
	 */
	public static void zapisiUDatoteku(FileWriter out, String poruka){
		PrintWriter x = new PrintWriter(out);
		x.println(poruka);
		//x.close();
	}

}
