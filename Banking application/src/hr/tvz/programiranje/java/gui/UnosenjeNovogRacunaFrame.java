package hr.tvz.programiranje.java.gui;

import hr.tvz.programiranje.java.banka.DevizniRacun;
import hr.tvz.programiranje.java.banka.Racun;
import hr.tvz.programiranje.java.banka.TekuciRacun;
import hr.tvz.programiranje.java.banka.Valuta;
import hr.tvz.programiranje.java.banka.VrstaRacuna;
import hr.tvz.programiranje.java.baza.BazaPodataka;
import hr.tvz.programiranje.java.osoba.Osoba;

import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class UnosenjeNovogRacunaFrame extends JFrame {

	private JPanel contentPaneRacun;
	private JTextField textFieldStanjeRacuna;
	private JTextField textFieldBrojRacunaIban;

	/**
	 * Launch the application.
	 */
	public void prikaziEkran() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//UnosenjeNovogRacunaFrame frame = new UnosenjeNovogRacunaFrame();
					//frame.setVisible(true);
					setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public UnosenjeNovogRacunaFrame(List<Osoba> listaOsoba, final List<Racun> listaRacuna) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 200);
		contentPaneRacun = new JPanel();
		contentPaneRacun.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPaneRacun);
		GridBagLayout gbl_contentPaneRacun = new GridBagLayout();
		gbl_contentPaneRacun.columnWidths = new int[]{100, 178, 16, 56, 0};
		gbl_contentPaneRacun.rowHeights = new int[]{32, 30, 31, 30, 0, 0};
		gbl_contentPaneRacun.columnWeights = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPaneRacun.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPaneRacun.setLayout(gbl_contentPaneRacun);
		
		JLabel labelVrstaRacuna = new JLabel("Vrsta raèuna:");
		GridBagConstraints gbc_labelVrstaRacuna = new GridBagConstraints();
		gbc_labelVrstaRacuna.insets = new Insets(0, 0, 5, 5);
		gbc_labelVrstaRacuna.anchor = GridBagConstraints.EAST;
		gbc_labelVrstaRacuna.gridx = 0;
		gbc_labelVrstaRacuna.gridy = 0;
		contentPaneRacun.add(labelVrstaRacuna, gbc_labelVrstaRacuna);
		
		
		final JComboBox<VrstaRacuna> comboBoxVrstaRacuna = new JComboBox<>();
		for(VrstaRacuna vrstaRacuna : VrstaRacuna.values()){   
			comboBoxVrstaRacuna.addItem(vrstaRacuna); 
		} 
		GridBagConstraints gbc_comboBoxVrstaRacuna = new GridBagConstraints();
		gbc_comboBoxVrstaRacuna.gridwidth = 2;
		gbc_comboBoxVrstaRacuna.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxVrstaRacuna.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxVrstaRacuna.gridx = 1;
		gbc_comboBoxVrstaRacuna.gridy = 0;
		contentPaneRacun.add(comboBoxVrstaRacuna, gbc_comboBoxVrstaRacuna);
				
		
		JLabel labelVlasnikRauna = new JLabel("Vlasnik raèuna:");
		GridBagConstraints gbc_labelVlasnikRauna = new GridBagConstraints();
		gbc_labelVlasnikRauna.anchor = GridBagConstraints.EAST;
		gbc_labelVlasnikRauna.insets = new Insets(0, 0, 5, 5);
		gbc_labelVlasnikRauna.gridx = 0;
		gbc_labelVlasnikRauna.gridy = 1;
		contentPaneRacun.add(labelVlasnikRauna, gbc_labelVlasnikRauna);
		
		final JComboBox<Osoba> comboBoxVlasnikRacuna = new JComboBox<>();
		listaOsoba = BazaPodataka.dohvatiSveOsobe();
		for(Osoba osoba : listaOsoba) {
			comboBoxVlasnikRacuna.addItem(osoba);
		}
		GridBagConstraints gbc_comboBoxVlasnikRacuna = new GridBagConstraints();
		gbc_comboBoxVlasnikRacuna.gridwidth = 2;
		gbc_comboBoxVlasnikRacuna.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxVlasnikRacuna.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxVlasnikRacuna.gridx = 1;
		gbc_comboBoxVlasnikRacuna.gridy = 1;
		contentPaneRacun.add(comboBoxVlasnikRacuna, gbc_comboBoxVlasnikRacuna);
		
		final JLabel labelStanjeRacuna = new JLabel("Stanje raèuna:");
		GridBagConstraints gbc_labelStanjeRacuna = new GridBagConstraints();
		gbc_labelStanjeRacuna.anchor = GridBagConstraints.EAST;
		gbc_labelStanjeRacuna.insets = new Insets(0, 0, 5, 5);
		gbc_labelStanjeRacuna.gridx = 0;
		gbc_labelStanjeRacuna.gridy = 2;
		contentPaneRacun.add(labelStanjeRacuna, gbc_labelStanjeRacuna);
		
		textFieldStanjeRacuna = new JTextField();
		GridBagConstraints gbc_textFieldStanjeRacuna = new GridBagConstraints();
		gbc_textFieldStanjeRacuna.gridwidth = 2;
		gbc_textFieldStanjeRacuna.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldStanjeRacuna.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldStanjeRacuna.gridx = 1;
		gbc_textFieldStanjeRacuna.gridy = 2;
		contentPaneRacun.add(textFieldStanjeRacuna, gbc_textFieldStanjeRacuna);
		textFieldStanjeRacuna.setColumns(10);
		
		final JComboBox<Valuta> comboBoxValuta = new JComboBox<>();
		for(Valuta valuta : Valuta.values()) {
			comboBoxValuta.addItem(valuta);
		}
		GridBagConstraints gbc_comboBoxValuta = new GridBagConstraints();
		gbc_comboBoxValuta.insets = new Insets(0, 0, 5, 0);
		gbc_comboBoxValuta.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxValuta.gridx = 3;
		gbc_comboBoxValuta.gridy = 2;
		contentPaneRacun.add(comboBoxValuta, gbc_comboBoxValuta);
		
		final JLabel labelBrojRacunaIban = new JLabel("Broj raèuna:");
		GridBagConstraints gbc_labelBrojRacunaIban = new GridBagConstraints();
		gbc_labelBrojRacunaIban.anchor = GridBagConstraints.EAST;
		gbc_labelBrojRacunaIban.insets = new Insets(0, 0, 5, 5);
		gbc_labelBrojRacunaIban.gridx = 0;
		gbc_labelBrojRacunaIban.gridy = 3;
		contentPaneRacun.add(labelBrojRacunaIban, gbc_labelBrojRacunaIban);
		
		textFieldBrojRacunaIban = new JTextField();
		textFieldBrojRacunaIban.setColumns(10);
		GridBagConstraints gbc_textFieldBrojRacunaIban = new GridBagConstraints();
		gbc_textFieldBrojRacunaIban.gridwidth = 2;
		gbc_textFieldBrojRacunaIban.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldBrojRacunaIban.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldBrojRacunaIban.gridx = 1;
		gbc_textFieldBrojRacunaIban.gridy = 3;
		contentPaneRacun.add(textFieldBrojRacunaIban, gbc_textFieldBrojRacunaIban);
		
		JButton buttonSpremiRacun = new JButton("Spremi raèun");
		GridBagConstraints gbc_buttonSpremiRacun = new GridBagConstraints();
		gbc_buttonSpremiRacun.anchor = GridBagConstraints.EAST;
		gbc_buttonSpremiRacun.insets = new Insets(0, 0, 0, 5);
		gbc_buttonSpremiRacun.gridx = 1;
		gbc_buttonSpremiRacun.gridy = 4;
		contentPaneRacun.add(buttonSpremiRacun, gbc_buttonSpremiRacun);
		
		
		//Action listener
		comboBoxVrstaRacuna.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {   
				VrstaRacuna odabranaVrstaRacuna = (VrstaRacuna)comboBoxVrstaRacuna.getSelectedItem();   
				if(odabranaVrstaRacuna.equals(VrstaRacuna.DEVIZNI)) {     
					comboBoxValuta.setVisible(true);     
					labelBrojRacunaIban.setText("IBAN:");     
					labelStanjeRacuna.setText("Stanje raèuna:");   
				}   
						
				else {    
					comboBoxValuta.setVisible(false);     
					labelBrojRacunaIban.setText("Broj raèuna:");     
					labelStanjeRacuna.setText("Stanje raèuna (KN):");   
				}  
			} 
		});		
		
		
		//Action listener
		buttonSpremiRacun.addActionListener(new ActionListener() {   
			public void actionPerformed(ActionEvent arg0) {     
				VrstaRacuna odabranaVrstaRacuna = (VrstaRacuna)comboBoxVrstaRacuna.getSelectedItem();     
				if(odabranaVrstaRacuna.equals(VrstaRacuna.DEVIZNI)) {       
					String iban = textFieldBrojRacunaIban.getText();   
					Valuta valuta = (Valuta) comboBoxValuta.getSelectedItem();   
					BigDecimal stanjeRacuna = new BigDecimal(textFieldStanjeRacuna.getText());   
					Osoba vlasnikRacuna = (Osoba)comboBoxVlasnikRacuna.getSelectedItem();   
					DevizniRacun devizni = new DevizniRacun(iban, valuta, stanjeRacuna, vlasnikRacuna); 
					listaRacuna.add(devizni);        
					JOptionPane.showMessageDialog(null, "Uspješno ste dodali novi devizni raèun: " + iban);
					GlavniEkran.osvjeziPopisRacuna();
				}    
				
				else {      
					String brojRacuna = textFieldBrojRacunaIban.getText();  
					BigDecimal stanjeRacuna = new BigDecimal(textFieldStanjeRacuna.getText());   
					Osoba vlasnikRacuna = (Osoba) comboBoxVlasnikRacuna.getSelectedItem();   
					TekuciRacun tekuci = new TekuciRacun(brojRacuna, stanjeRacuna, vlasnikRacuna);   
					listaRacuna.add(tekuci);   
					JOptionPane.showMessageDialog(null, "Uspješno ste dodali novi tekuæi raèun: " + brojRacuna);
					GlavniEkran.osvjeziPopisRacuna();
				}  
				
				dispose();    
			} 
		}); 
		
	}

}
