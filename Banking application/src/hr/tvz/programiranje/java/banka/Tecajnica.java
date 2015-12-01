package hr.tvz.programiranje.java.banka;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * sadrzi listu tecajeva
 * @author Josip
 *
 */
public class Tecajnica {
	
	public static List<Tecaj> dohvatiTecajeve(){
		List<Tecaj> listaTecajeva = new ArrayList <>();
		InputStream in = null;
		BufferedReader reader = null;
		
		try{
			URL u = new URL("http://www.hnb.hr/tecajn/f161113.dat"); 
			in = u.openStream(); 
			reader = new BufferedReader(new InputStreamReader(in)); 
		}
		
		catch(IOException ex){
			System.err.println(ex.getMessage());
			ex.printStackTrace();
		}
		
		int i = 0;
		String line = "";
		try{
			reader.readLine();
			while ((line = reader.readLine()) != null) {
				listaTecajeva.add(i, new Tecaj(Valuta.valueOf(line.substring(3, 6)), new BigDecimal(srednjiTecaj(line.replace(',', '.'))).divide(new BigDecimal(line.substring(6, 9)), 8, RoundingMode.HALF_UP)));
				//listaTecajeva.add(index i, new Tecaj(Valuta.enum, new BigDecimal("tecaj")));
				i++;
			} 
		}
		
		catch(IOException ex){
			System.err.println(ex.getMessage());
			ex.printStackTrace();
		}
		
		return listaTecajeva;
	}
	
	public static String srednjiTecaj(String line){
		StringTokenizer tokenizer = new StringTokenizer(line, " "); 
		tokenizer.nextToken(); 
		tokenizer.nextToken(); 
		String tecaj = tokenizer.nextToken();
		
		return tecaj;
	}
}