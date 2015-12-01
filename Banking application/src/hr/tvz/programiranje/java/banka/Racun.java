package hr.tvz.programiranje.java.banka;

import hr.tvz.programiranje.java.osoba.Osoba;
import java.math.BigDecimal;


public abstract class Racun {
	private Osoba vlasnikRacuna;
	private int id;
	private BigDecimal stanjeRacuna;
	
	/**
	 * Konstruktor klase Racun. Koristi se samo u podklasama koje nasljeðuju ovu klasu.
	 * @param vlasnikRacuna	Vlasnik raèuna tipa Osoba.
	 * @param stanjeRacuna	Trenutno stanje raèuna.
	 */
	public Racun(Osoba vlasnikRacuna, BigDecimal stanjeRacuna){
		this.vlasnikRacuna = vlasnikRacuna;
		this.stanjeRacuna = stanjeRacuna;
	}
	
	public Racun(int id, Osoba vlasnikRacuna, BigDecimal stanjeRacuna){
		this(vlasnikRacuna, stanjeRacuna);
		this.id = id;
	}
	
	
	/**
	 * Vraæa podatke o vlasniku raèuna.
	 * @return Vlasnik raèuna tipa Osoba.
	 */
	public Osoba getVlasnikRacuna() {
		return vlasnikRacuna;
	}
	

	/**
	 * Vraæa trenutno stanje raèuna.
	 * @return	Stanje raèuna tipa BigDecimal.
	 */
	public BigDecimal getStanjeRacuna() {
		return stanjeRacuna;
	}
	
	/**
	 * Dodaje uplatu sa jednog raèuna na drugi.
	 * @param uplata	Iznos uplate koji treba dodati na dolazni raèun.
	 */
	public void uplatiNaRacun(BigDecimal uplata){
		stanjeRacuna = stanjeRacuna.add(uplata);
	}
	
	/**
	 * Isplaæuje iznos sa jednog raèuna na drugi.
	 * @param isplata	Iznos koji treba oduzeti sa polaznog raèuna.
	 */
	public void isplatiSRacuna(BigDecimal isplata){
		stanjeRacuna = stanjeRacuna.subtract(isplata);
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}
}
