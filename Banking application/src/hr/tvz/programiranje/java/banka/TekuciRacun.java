package hr.tvz.programiranje.java.banka;

import hr.tvz.programiranje.java.osoba.Osoba;

import java.math.BigDecimal;


public class TekuciRacun extends Racun{
	private String brojRacuna;
	private int id;
	
	/**
	 * Konstruira objekt tipa Racun.
	 * Prima broj ra�una, stanje, te vlasnika ra�una. Stanje i vlasnika predaje konstrukotru nadklase.
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
	 * Dohva�a broj ra�una.
	 * 
	 * @return	Broj ra�una tipa string.
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
