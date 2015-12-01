package hr.tvz.programiranje.java.banka;

import hr.tvz.programiranje.java.osoba.Osoba;
import java.math.BigDecimal;


public abstract class Racun {
	private Osoba vlasnikRacuna;
	private int id;
	private BigDecimal stanjeRacuna;
	
	/**
	 * Konstruktor klase Racun. Koristi se samo u podklasama koje naslje�uju ovu klasu.
	 * @param vlasnikRacuna	Vlasnik ra�una tipa Osoba.
	 * @param stanjeRacuna	Trenutno stanje ra�una.
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
	 * Vra�a podatke o vlasniku ra�una.
	 * @return Vlasnik ra�una tipa Osoba.
	 */
	public Osoba getVlasnikRacuna() {
		return vlasnikRacuna;
	}
	

	/**
	 * Vra�a trenutno stanje ra�una.
	 * @return	Stanje ra�una tipa BigDecimal.
	 */
	public BigDecimal getStanjeRacuna() {
		return stanjeRacuna;
	}
	
	/**
	 * Dodaje uplatu sa jednog ra�una na drugi.
	 * @param uplata	Iznos uplate koji treba dodati na dolazni ra�un.
	 */
	public void uplatiNaRacun(BigDecimal uplata){
		stanjeRacuna = stanjeRacuna.add(uplata);
	}
	
	/**
	 * Ispla�uje iznos sa jednog ra�una na drugi.
	 * @param isplata	Iznos koji treba oduzeti sa polaznog ra�una.
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
