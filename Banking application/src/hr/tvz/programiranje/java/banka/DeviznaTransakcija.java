package hr.tvz.programiranje.java.banka;

import java.math.*;
import java.util.Date;

import hr.tvz.programiranje.java.iznimke.*;


@SuppressWarnings("unused")
public class DeviznaTransakcija<T extends TekuciRacun, S extends DevizniRacun> extends Transakcija<T, S> implements Devizna{
	private BigDecimal polazniIznosKN;
	private Date datumTransakcije;
	private int id;
	
	/**
	 * Konstruira objekt deviznaTransakcija.
	 * Prima polazni i dolazni raèun, te iznos transakcije, te sve predaje konstrukotru nadklase (klasa Transakcija).
	 * 
	 * @param polazniRacun	Raèun sa kojeg se uplaæuje iznos.
	 * @param dolazniRacun	Raèun na koji se uplaæuje iznos.
	 * @param iznos			Iznos uplate s polaznog na dolazni raèun.
	 */
	public DeviznaTransakcija(T polazniRacun, S dolazniRacun, BigDecimal iznos){
		super(polazniRacun, dolazniRacun, iznos);
	}
	
	public DeviznaTransakcija(int id, T polazniRacun, S dolazniRacun, BigDecimal iznos, Date datumTransakcije){
		this(polazniRacun, dolazniRacun, iznos);
		this.id = id;
		this.datumTransakcije = datumTransakcije;
	}
	
	public BigDecimal mjenjacnica(BigDecimal polazniIznosKN, Valuta valuta){
		for(Tecaj tecaj : Tecajnica.dohvatiTecajeve()){
			if(tecaj.getValuta().compareTo(valuta) == 0){
				BigDecimal iznos = polazniIznosKN.divide(tecaj.getTecaj(), 2, RoundingMode.HALF_UP);
				return iznos;
				}
			}
		return polazniIznosKN;	       
	}
	
	@Override
	public void provediTransakciju() throws NedozvoljenoStanjeRacunaException{
		if(polazniRacun.getStanjeRacuna().compareTo(super.iznosPromjene) == -1){
			String brojRacuna = polazniRacun.getBrojRacuna();
			throw new NedozvoljenoStanjeRacunaException("Raèun " + brojRacuna + " nema dovoljno sredstava (" + polazniRacun.getStanjeRacuna() + ") za provoðenje transakcije (" + super.iznosPromjene + ")!");
			
		}
		
		else{
			polazniRacun.isplatiSRacuna(super.iznosPromjene);								// FLAG*
			BigDecimal konvertiraniIznos = mjenjacnica(super.iznosPromjene , ((DevizniRacun)dolazniRacun).getValuta());
			dolazniRacun.uplatiNaRacun(konvertiraniIznos);
		}
	}
	
	/**
	 * Vrši provjeru da li je valuta unesena od strane korisnika podržana u programu/banci.
	 * Prima valutu u obliku stringa, te ga pretvara u objekt tipa Valuta. Ako string odgovara vrijednosti Valuta tada je ta valuta podržana. U suprotnom se baca greška o nepodržanoj valuti.
	 * 
	 * @param valuta						String valuta unesen od strane korisnika.
	 * @return								Objekt tipa Valuta koji je pretvoren iz unesenog stringa.
	 * @throws NepodrzanaValutaException	Greška o nepodržanoj valuti. Baca se kada uneseni String ne odgovara vrijednosti Valuta.
	 * @author Matej
	 *
	 */
	public static Valuta provjeriValutu(String valuta) throws NepodrzanaValutaException {
		try{
			return Valuta.valueOf(valuta);
		}
		catch(IllegalArgumentException ex) {
			throw new NepodrzanaValutaException("Valuta " + valuta + " nije podržana!", ex);
		}
	}

	public BigDecimal getIznosTransakcije() {
		return super.getIznosPromjene();
	}
	
}
