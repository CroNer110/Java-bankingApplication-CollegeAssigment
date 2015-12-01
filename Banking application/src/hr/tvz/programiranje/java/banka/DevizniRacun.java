package hr.tvz.programiranje.java.banka;

import hr.tvz.programiranje.java.osoba.Osoba;

import java.math.BigDecimal;


public class DevizniRacun extends Racun{
	private String iban;
	Valuta valuta;
	private int id;
	
	/**
	 * Konstruira objekt klase DevizniRacun.
	 * Prima iban, stanje raèuna, te vlasnika raèuna.
	 * Parametre vlasnikRacuna i stanjeRacuna predaje konstruktoru nadklase (Racun).
	 * @param iban
	 * @param valuta
	 * @param stanjeRacuna
	 * @param vlasnikRacuna
	 */
	public DevizniRacun(String iban, Valuta valuta, BigDecimal stanjeRacuna, Osoba vlasnikRacuna){
		super(vlasnikRacuna, stanjeRacuna);
		this.iban = iban;
		this.valuta = valuta;
	}
	
	public DevizniRacun(int id, String iban, Valuta valuta, BigDecimal stanjeRacuna, Osoba vlasnikRacuna) {
		this(iban, valuta, stanjeRacuna, vlasnikRacuna);
		this.setId(id);
	}

	/**
	 * Vraæa IBAN deviznog raèuna.
	 * IBAN deviznog raèuna je tipa string.
	 * @return	IBAN deviznog raèuna
	 */
	public String getIban() {
		return iban;
	}
	
	/**
	 * Vraæa valutu deviznog raèuna.
	 * Valuta deviznog raèuna je tipa Valuta, definirana klasom Valuta.
	 * @return	Valuta deviznog raèuna
	 */
	public Valuta getValuta() {
		return valuta;
	}
	
	@Override
	public String toString() {  
		return iban; 
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
