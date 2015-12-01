package hr.tvz.programiranje.java.iznimke;


public class NepodrzanaValutaException extends Exception {
	
	public NepodrzanaValutaException(String poruka){
		super(poruka);
	}
	public NepodrzanaValutaException(String poruka,Throwable cause){
		super(poruka,cause);
	}		
	
}
