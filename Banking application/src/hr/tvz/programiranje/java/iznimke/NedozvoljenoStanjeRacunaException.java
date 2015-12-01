package hr.tvz.programiranje.java.iznimke;

import java.lang.RuntimeException;

public class NedozvoljenoStanjeRacunaException extends RuntimeException {
	
	public NedozvoljenoStanjeRacunaException(String poruka){
		super(poruka);
	}
	public NedozvoljenoStanjeRacunaException(String poruka,Throwable cause){
		super(poruka, cause);
	}		
	
}
