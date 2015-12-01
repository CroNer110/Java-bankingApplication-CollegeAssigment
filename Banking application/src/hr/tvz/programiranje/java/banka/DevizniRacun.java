package hr.tvz.programiranje.java.banka;

import hr.tvz.programiranje.java.osoba.Osoba;

import java.math.BigDecimal;


public class DevizniRacun extends Racun{
	private String iban;
	Valuta valuta;
	private int id;
	
	/**
	 * Konstruira objekt klase DevizniRacun.
	 * Prima iban, stanje ra�una, te vlasnika ra�una.
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
	 * Vra�a IBAN deviznog ra�una.
	 * IBAN deviznog ra�una je tipa string.
	 * @return	IBAN deviznog ra�una
	 */
	public String getIban() {
		return iban;
	}
	
	/**
	 * Vra�a valutu deviznog ra�una.
	 * Valuta deviznog ra�una je tipa Valuta, definirana klasom Valuta.
	 * @return	Valuta deviznog ra�una
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
