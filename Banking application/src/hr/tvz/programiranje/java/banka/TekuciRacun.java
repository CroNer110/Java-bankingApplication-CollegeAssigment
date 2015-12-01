package hr.tvz.programiranje.java.banka;

import hr.tvz.programiranje.java.osoba.Osoba;

import java.math.BigDecimal;


public class TekuciRacun extends Racun{
	private String brojRacuna;
	private int id;
	
	/**
	 * Konstruira objekt tipa Racun.
	 * Prima broj raèuna, stanje, te vlasnika raèuna. Stanje i vlasnika predaje konstrukotru nadklase.
	 * @param brojRacuna
	 * @param stanjeRacuna
	 * @param vlasnikRacuna
	 */
	public TekuciRacun(String brojRacuna, BigDecimal stanjeRacuna, Osoba vlasnikRacuna){
		super(vlasnikRacuna, stanjeRacuna);
		this.brojRacuna = brojRacuna;
	}

	public TekuciRacun(int id, String brojRacuna, BigDecimal stanjeRacuna, Osoba vlasnikRacuna) {
		this(brojRacuna, stanjeRacuna, vlasnikRacuna);
		this.setId(id);
	}

	/**
	 * Dohvaæa broj raèuna.
	 * 
	 * @return	Broj raèuna tipa string.
	 */
	public String getBrojRacuna() {
		return brojRacuna;
	}
	
	@Override
	public String toString() {  
		return brojRacuna; 
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
