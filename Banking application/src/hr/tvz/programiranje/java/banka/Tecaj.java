package hr.tvz.programiranje.java.banka;

import java.math.BigDecimal;

public class Tecaj {
	private Valuta valuta;
	private BigDecimal tecaj;


public Tecaj(Valuta valuta,BigDecimal tecaj){
	this.valuta=valuta;
	this.tecaj=tecaj;
}
public Valuta getValuta(){
	return valuta;
}
public void setValuta(Valuta valuta){
	this.valuta=valuta;
}
public BigDecimal getTecaj(){
	return tecaj;
}
public void setTecaj(BigDecimal tecaj){
	this.tecaj=tecaj;
}
}