package hr.tvz.programiranje.java.banka;

import hr.tvz.programiranje.java.banka.Racun;

import java.math.BigDecimal;
import java.util.Date;


public class Transakcija <T extends Racun, S extends Racun>{
	protected T polazniRacun;
	protected S dolazniRacun;
	protected BigDecimal iznosPromjene;
	protected Date datumTransakcije;
	int id;
	
	/**
	 * Konstruira objekt klase Transakcija.
	 * @param polazniRacun
	 * @param odlazniRacun
	 * @param iznosPromjene
	 */
	public Transakcija(T polazniRacun, S odlazniRacun, BigDecimal iznosPromjene){
		this.polazniRacun = polazniRacun;
		this.dolazniRacun = odlazniRacun;
		this.iznosPromjene = iznosPromjene;
	}

	public Transakcija(int id, T polazniRacun, S odlazniRacun, BigDecimal iznosPromjene, Date datumTransakcije) {
		this(polazniRacun, odlazniRacun, iznosPromjene);
		this.id = id;
		this.datumTransakcije = datumTransakcije;		
	}

	public void provediTransakciju(){
		polazniRacun.isplatiSRacuna(iznosPromjene);
		dolazniRacun.uplatiNaRacun(iznosPromjene);
	}

	/**
	 * Vraæa iznos transakcije.
	 * 
	 * @return	Iznos transakcije.
	 */
	public BigDecimal getIznosPromjene() {
		return iznosPromjene;
	}

	public T getPolazniRacun() {
		return polazniRacun;
	}

	public S getDolazniRacun() {
		return dolazniRacun;
	}
	
}
