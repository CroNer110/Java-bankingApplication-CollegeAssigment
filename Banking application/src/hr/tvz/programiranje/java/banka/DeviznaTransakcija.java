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
	 * Prima polazni i dolazni ra�un, te iznos transakcije, te sve predaje konstrukotru nadklase (klasa Transakcija).
	 * 
	 * @param polazniRacun	Ra�un sa kojeg se upla�uje iznos.
	 * @param dolazniRacun	Ra�un na koji se upla�uje iznos.
	 * @param iznos			Iznos uplate s polaznog na dolazni ra�un.
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
			throw new NedozvoljenoStanjeRacunaException("Ra�un " + brojRacuna + " nema dovoljno sredstava (" + polazniRacun.getStanjeRacuna() + ") za provo�enje transakcije (" + super.iznosPromjene + ")!");
			
		}
		
		else{
			polazniRacun.isplatiSRacuna(super.iznosPromjene);								// FLAG*
			BigDecimal konvertiraniIznos = mjenjacnica(super.iznosPromjene , ((DevizniRacun)dolazniRacun).getValuta());
			dolazniRacun.uplatiNaRacun(konvertiraniIznos);
		}
	}
	
	/**
	 * Vr�i provjeru da li je valuta unesena od strane korisnika podr�ana u programu/banci.
	 * Prima valutu u obliku stringa, te ga pretvara u objekt tipa Valuta. Ako string odgovara vrijednosti Valuta tada je ta valuta podr�ana. U suprotnom se baca gre�ka o nepodr�anoj valuti.
	 * 
	 * @param valuta						String valuta unesen od strane korisnika.
	 * @return								Objekt tipa Valuta koji je pretvoren iz unesenog stringa.
	 * @throws NepodrzanaValutaException	Gre�ka o nepodr�anoj valuti. Baca se kada uneseni String ne odgovara vrijednosti Valuta.
	 * @author Matej
	 *
	 */
	public static Valuta provjeriValutu(String valuta) throws NepodrzanaValutaException {
		try{
			return Valuta.valueOf(valuta);
		}
		catch(IllegalArgumentException ex) {
			throw new NepodrzanaValutaException("Valuta " + valuta + " nije podr�ana!", ex);
		}
	}

	public BigDecimal getIznosTransakcije() {
		return super.getIznosPromjene();
	}
	
}
