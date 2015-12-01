package hr.tvz.programiranje.java.osoba;

public class Osoba {
	 
	 private Integer id;
	 private String ime;
	 private String prezime;
	 private String oib;
	 
	 public Osoba(String ime, String prezime, String oib) {
	  this.ime = ime;
	  this.prezime = prezime;
	  this.oib = oib;
	 } 
	 public Osoba(Integer id, String ime, String prezime, String oib) {
	  this(ime, prezime, oib);
	  this.id = id;
	 } 
	 public String getIme() {
	  return ime;
	 }
	 public String getPrezime() {
	  return prezime;
	 }
	 public String getOib() {
	  return oib;
	 }
	 public Integer getId() {
	  return id;
	 }
	 public String toString() {
	    return prezime + " " + ime + " (" + oib + ")";
	 }
	} 