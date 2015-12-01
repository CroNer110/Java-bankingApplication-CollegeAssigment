package hr.tvz.programiranje.java.sortiranje;

import hr.tvz.programiranje.java.banka.Transakcija;

import java.util.Comparator;

public class SortiranjeTransakcija implements Comparator<Transakcija> {
	@Override
	public int compare(Transakcija t1, Transakcija t2){
		if( (t1.getIznosPromjene()).compareTo(t2.getIznosPromjene()) == 1 )
			return -1;
		else if( (t1.getIznosPromjene()).compareTo(t2.getIznosPromjene()) == -1 )
			return 1;
		else
			return 0;
	}
}
