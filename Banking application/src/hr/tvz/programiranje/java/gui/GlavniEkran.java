package hr.tvz.programiranje.java.gui;

import hr.tvz.programiranje.java.banka.DeviznaTransakcija;
import hr.tvz.programiranje.java.banka.DevizniRacun;
import hr.tvz.programiranje.java.banka.Racun;
import hr.tvz.programiranje.java.banka.TekuciRacun;
import hr.tvz.programiranje.java.banka.Transakcija;
import hr.tvz.programiranje.java.baza.BazaPodataka;
import hr.tvz.programiranje.java.iznimke.NedozvoljenoStanjeRacunaException;
import hr.tvz.programiranje.java.osoba.Osoba;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GlavniEkran {

	private JFrame frmTvzBankarstvo;
	private List<Osoba> listaOsoba;
	private static List<Racun> listaRacuna;
	
	private JTextField iznosTransakcijeTextField; 
	@SuppressWarnings("rawtypes")
	private static JComboBox prviRacunComboBox; 
	@SuppressWarnings("rawtypes")
	private static JComboBox drugiRacunComboBox; 
//	private static JPanel stanjePrvogRacunaPanel; 
//	private static JPanel stanjeDrugogRacunaPanel; 
	private static final Logger logger = LoggerFactory.getLogger(GlavniEkran.class); 
	private static JTable transakcijeTable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GlavniEkran window = new GlavniEkran();
					window.frmTvzBankarstvo.setVisible(true);
					List<Racun> listaRacuna = BazaPodataka.dohvatiSveRacune();      
					if(listaRacuna.isEmpty() == false) {       
						osvjeziPopisRacuna();      
					}     
					List<Transakcija<?,?>> listaTransakcija = BazaPodataka.dohvatiSveTransakcije();  
					for(Transakcija<?,?> transakcija : listaTransakcija) {      
						dodajTransakcijuUTablicu(transakcija.getPolazniRacun(), transakcija.getDolazniRacun(), transakcija.getIznosPromjene()); 
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	@SuppressWarnings("unchecked")
	public static void osvjeziPopisRacuna() {  
		prviRacunComboBox.removeAllItems();  
		drugiRacunComboBox.removeAllItems();  
		for(Racun racun : listaRacuna) {  
			prviRacunComboBox.addItem(racun);   
			drugiRacunComboBox.addItem(racun); 
		} 
	}
	
	public static void dodajTransakcijuUTablicu(Racun polazni, Racun dolazni, BigDecimal iznos) {    
		Object[] podaciUTablici = new Object[5]; 
		podaciUTablici[0] = transakcijeTable.getRowCount() + 1 + "."; 
		podaciUTablici[1] = ((TekuciRacun) polazni).getBrojRacuna();   
		if(dolazni instanceof TekuciRacun) {  
			podaciUTablici[2] = ((TekuciRacun) dolazni).getBrojRacuna(); 
		}
		
		else if (dolazni instanceof DevizniRacun) {  
			podaciUTablici[2] = ((DevizniRacun) dolazni).getIban(); 
		}    
		
		podaciUTablici[3] = iznos.toString(); podaciUTablici[4] = "KN";    
		((DefaultTableModel)transakcijeTable.getModel()).addRow(podaciUTablici); 
	}

	/**
	 * Create the application.
	 */
	public GlavniEkran() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("rawtypes")
	private void initialize() {
		frmTvzBankarstvo = new JFrame();
		listaOsoba = new ArrayList<Osoba>();
		listaRacuna = new ArrayList<Racun>();
		
		frmTvzBankarstvo.setBounds(100, 100, 450, 300);
		frmTvzBankarstvo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frmTvzBankarstvo.setJMenuBar(menuBar);
		
		JMenu menuOsobe = new JMenu("Osobe");
		menuBar.add(menuOsobe);
		
		JMenuItem menuItemDodajNovuOsobu = new JMenuItem("Dodaj novu osobu");
		menuOsobe.add(menuItemDodajNovuOsobu);
		//Action listener
		menuItemDodajNovuOsobu.addActionListener(new ActionListener() {   
			public void actionPerformed(ActionEvent e) {    
				UnosenjeNoveOsobeFrame frame = new UnosenjeNoveOsobeFrame(listaOsoba);    
				frame.prikaziEkran();   
			} 
		});
		
		JMenu menuRacuni = new JMenu("Raèuni");
		menuBar.add(menuRacuni);
		
		JMenuItem menuItemDodajNoviRacun = new JMenuItem("Dodaj novi raèun");
		menuRacuni.add(menuItemDodajNoviRacun);
		
		JPanel racuniPanel = new JPanel();
		frmTvzBankarstvo.getContentPane().add(racuniPanel, BorderLayout.NORTH);
		racuniPanel.setLayout(new BoxLayout(racuniPanel, BoxLayout.X_AXIS));
		
		JPanel panelPrviRacun = new JPanel();
		racuniPanel.add(panelPrviRacun);
		panelPrviRacun.setLayout(new BoxLayout(panelPrviRacun, BoxLayout.Y_AXIS));
		panelPrviRacun.setBorder(new TitledBorder(new LineBorder(Color.black, 1), "Polazni raèun"));
		
		final JPanel stanjePrvogRacunaPanel = new JPanel();
		panelPrviRacun.add(stanjePrvogRacunaPanel);
		
		JLabel stanjePrvogRacunaTextLabel = new JLabel("Stanje raèuna:");
		stanjePrvogRacunaPanel.add(stanjePrvogRacunaTextLabel);
		//Disable until selected
		stanjePrvogRacunaPanel.setVisible(false);
		
		final JLabel stanjePrvogRacunaLabel = new JLabel("");
		stanjePrvogRacunaPanel.add(stanjePrvogRacunaLabel);
		
		final JLabel valutaPrvogRacunaLabel = new JLabel("");
		stanjePrvogRacunaPanel.add(valutaPrvogRacunaLabel);
		
		//final JComboBox<Racun> 
		prviRacunComboBox = new JComboBox();
		panelPrviRacun.add(prviRacunComboBox);
		
		JPanel panelDrugiRacun = new JPanel();
		racuniPanel.add(panelDrugiRacun);
		panelDrugiRacun.setLayout(new BoxLayout(panelDrugiRacun, BoxLayout.Y_AXIS));
		panelDrugiRacun.setBorder(new TitledBorder(new LineBorder(Color.black, 1), "Dolazni raèun"));
		
		final JPanel stanjeDrugogRacunaPanel = new JPanel();
		panelDrugiRacun.add(stanjeDrugogRacunaPanel);
		
		JLabel stanjeDrugogRacunaTextLabel = new JLabel("Stanje raèuna:");
		stanjeDrugogRacunaPanel.add(stanjeDrugogRacunaTextLabel);
		
		final JLabel stanjeDrugogRacunaLabel = new JLabel("");
		stanjeDrugogRacunaPanel.add(stanjeDrugogRacunaLabel);
		//Disable until selected
		stanjeDrugogRacunaPanel.setVisible(false);
		
		final JLabel valutaDrugogRacunaLabel = new JLabel("");
		stanjeDrugogRacunaPanel.add(valutaDrugogRacunaLabel);
		
		//final JComboBox<Racun> 
		drugiRacunComboBox = new JComboBox();
		panelDrugiRacun.add(drugiRacunComboBox);
		
		JPanel iznosTransakcijePanel = new JPanel();
		frmTvzBankarstvo.getContentPane().add(iznosTransakcijePanel, BorderLayout.SOUTH);
		iznosTransakcijePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel unosIznosaTextLabel = new JLabel("Iznos transakcije:");
		iznosTransakcijePanel.add(unosIznosaTextLabel);
		
		iznosTransakcijeTextField = new JTextField();
		iznosTransakcijePanel.add(iznosTransakcijeTextField);
		iznosTransakcijeTextField.setColumns(10);
		
		JLabel iznosTransakcijeValutaLabel = new JLabel("KN");
		iznosTransakcijePanel.add(iznosTransakcijeValutaLabel);
		
		JButton izvrsavanjeTransakcijeButton = new JButton("Izvrši transakciju");
		//Action listener
		izvrsavanjeTransakcijeButton.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent arg0) {  
				Racun prviRacun = (Racun) prviRacunComboBox.getSelectedItem();  
				Racun drugiRacun = (Racun) drugiRacunComboBox.getSelectedItem();       
				if (prviRacun instanceof TekuciRacun && drugiRacun instanceof TekuciRacun) {   
					Transakcija<Racun, Racun> transakcija = new Transakcija<>(prviRacun, drugiRacun, new BigDecimal(iznosTransakcijeTextField.getText()));    
					
					try {   
						transakcija.provediTransakciju();   
						dodajTransakcijuUTablicu(prviRacun, drugiRacun, transakcija.getIznosPromjene());   
						stanjePrvogRacunaLabel.setText(prviRacun.getStanjeRacuna().toString());   
						stanjeDrugogRacunaLabel.setText(    drugiRacun.getStanjeRacuna().toString());
					}    
					
					catch(NedozvoljenoStanjeRacunaException ex) {   
						String message = "Transakcija se nije provela! Nedozvoljeno stanje raèuna!";   
						System.out.println(message);   
						JOptionPane.showMessageDialog(null, message);   
						logger.error(message, ex);    
					}  
				}  
				
				else if (prviRacun instanceof TekuciRacun && drugiRacun instanceof DevizniRacun) {   
					DeviznaTransakcija<TekuciRacun, DevizniRacun> transakcija = new DeviznaTransakcija<>((TekuciRacun)prviRacun, (DevizniRacun)drugiRacun, new BigDecimal(iznosTransakcijeTextField.getText()));   
					
					try {    
						transakcija.provediTransakciju(); 
						dodajTransakcijuUTablicu(prviRacun, drugiRacun, transakcija.getIznosTransakcije());    
						stanjePrvogRacunaLabel.setText(prviRacun.getStanjeRacuna().toString());    
						stanjeDrugogRacunaLabel.setText(drugiRacun.getStanjeRacuna().toString());   
					}   
					
					catch(NedozvoljenoStanjeRacunaException ex) {    
						String message = "Transakcija se nije provela! Nedozvoljeno stanje raèuna!";     
						System.out.println(message);     
						JOptionPane.showMessageDialog(null, message);     
						logger.error(message, ex);   
					}  
				}  
				
				else {   
					JOptionPane.showMessageDialog(null, "Odabrali ste nepodržanu transakciju!"); 
				}  
			} 
		}); 
		iznosTransakcijePanel.add(izvrsavanjeTransakcijeButton);
		
		JPanel popisTransakcijaPanel = new JPanel();
		frmTvzBankarstvo.getContentPane().add(popisTransakcijaPanel, BorderLayout.WEST);
		
		//Tablica
		transakcijeTable = new JTable(); 
		transakcijeTable.setModel(new DefaultTableModel(
				new Object[][] {}, 
				new String[] {
						"Rbr.", "Polazni raèun", "Dolazni raèun", "Iznos", "Valuta"
				}  
		));  
		
		//Action listener
		menuItemDodajNoviRacun.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				UnosenjeNovogRacunaFrame frame = new UnosenjeNovogRacunaFrame(listaOsoba, listaRacuna);
				frame.prikaziEkran();
			}
		});
		
		//Action listener
		prviRacunComboBox.addActionListener(new ActionListener() {  
			public void actionPerformed(ActionEvent arg0) {  
				Racun prviRacun = (Racun) prviRacunComboBox.getSelectedItem(); 
				if(prviRacun == null) {  
					prviRacun = listaRacuna.get(0);  
				}  
				
				if(prviRacun instanceof TekuciRacun) {   
					stanjePrvogRacunaLabel.setText(prviRacun.getStanjeRacuna().toString());  
					valutaPrvogRacunaLabel.setText("KN");  
				} 
				
				else {  
					stanjePrvogRacunaLabel.setText(prviRacun.getStanjeRacuna().toString());   
					valutaPrvogRacunaLabel.setText(((DevizniRacun)prviRacun).getValuta().toString()); 
				}  
				
				stanjePrvogRacunaPanel.setVisible(true); 
			} 
		}); 
		
		//Action listener
		drugiRacunComboBox.addActionListener(new ActionListener() {  
			public void actionPerformed(ActionEvent arg0) {  
				Racun drugiRacun = (Racun) drugiRacunComboBox.getSelectedItem();  
				if(drugiRacun == null) {  
					drugiRacun = listaRacuna.get(0);  
				}  
				
				if(drugiRacun instanceof TekuciRacun) {   
					stanjeDrugogRacunaLabel.setText(drugiRacun.getStanjeRacuna().toString());  
					valutaDrugogRacunaLabel.setText("KN");  
				} 
				
				else {  
					stanjePrvogRacunaLabel.setText(drugiRacun.getStanjeRacuna().toString());   
					valutaPrvogRacunaLabel.setText(((DevizniRacun)drugiRacun).getValuta().toString()); 
				}  
				
				stanjeDrugogRacunaPanel.setVisible(true); 
			} 
		}); 
		
		//Tablica
		transakcijeTable.getColumnModel().getColumn(0).setPreferredWidth(35); 
		transakcijeTable.getColumnModel().getColumn(0).setMinWidth(35); 
		transakcijeTable.getColumnModel().getColumn(4).setPreferredWidth(50);
		transakcijeTable.setPreferredScrollableViewportSize(new Dimension(420, 70));
		JScrollPane transakcijeTableScrollPane = new JScrollPane(transakcijeTable); 
		popisTransakcijaPanel.add(transakcijeTableScrollPane);
	}
}
