package hr.tvz.programiranje.java.gui;

import hr.tvz.programiranje.java.baza.BazaPodataka;
import hr.tvz.programiranje.java.osoba.Osoba;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JButton;

public class UnosenjeNoveOsobeFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPaneOsoba;
	private JTextField textFieldPrezime;
	private JTextField textFieldIme;
	private JTextField textFieldOib;

	/**
	 * Launch the application.
	 */
	public void prikaziEkran() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//UnosenjeNoveOsobeFrame frame = new UnosenjeNoveOsobeFrame();
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
	public UnosenjeNoveOsobeFrame(final List<Osoba> listaOsoba) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 180);
		contentPaneOsoba = new JPanel();
		contentPaneOsoba.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPaneOsoba);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] {77, 301, 0};
		gbl_contentPane.rowHeights = new int[] {35, 31, 31, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPaneOsoba.setLayout(gbl_contentPane);
		
		JLabel labelPrezime = new JLabel("Prezime:");
		GridBagConstraints gbc_labelPrezime = new GridBagConstraints();
		gbc_labelPrezime.anchor = GridBagConstraints.EAST;
		gbc_labelPrezime.insets = new Insets(0, 0, 5, 5);
		gbc_labelPrezime.gridx = 0;
		gbc_labelPrezime.gridy = 0;
		contentPaneOsoba.add(labelPrezime, gbc_labelPrezime);
		
		textFieldPrezime = new JTextField();
		GridBagConstraints gbc_textFieldPrezime = new GridBagConstraints();
		gbc_textFieldPrezime.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldPrezime.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldPrezime.gridx = 1;
		gbc_textFieldPrezime.gridy = 0;
		contentPaneOsoba.add(textFieldPrezime, gbc_textFieldPrezime);
		textFieldPrezime.setColumns(10);
		
		JLabel labelIme = new JLabel("Ime:");
		GridBagConstraints gbc_labelIme = new GridBagConstraints();
		gbc_labelIme.anchor = GridBagConstraints.EAST;
		gbc_labelIme.insets = new Insets(0, 0, 5, 5);
		gbc_labelIme.gridx = 0;
		gbc_labelIme.gridy = 1;
		contentPaneOsoba.add(labelIme, gbc_labelIme);
		
		textFieldIme = new JTextField();
		textFieldIme.setColumns(10);
		GridBagConstraints gbc_textFieldIme = new GridBagConstraints();
		gbc_textFieldIme.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldIme.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldIme.gridx = 1;
		gbc_textFieldIme.gridy = 1;
		contentPaneOsoba.add(textFieldIme, gbc_textFieldIme);
		
		JLabel labelOib = new JLabel("OIB:");
		GridBagConstraints gbc_labelOib = new GridBagConstraints();
		gbc_labelOib.anchor = GridBagConstraints.EAST;
		gbc_labelOib.insets = new Insets(0, 0, 5, 5);
		gbc_labelOib.gridx = 0;
		gbc_labelOib.gridy = 2;
		contentPaneOsoba.add(labelOib, gbc_labelOib);
		
		textFieldOib = new JTextField();
		textFieldOib.setColumns(10);
		GridBagConstraints gbc_textFieldOib = new GridBagConstraints();
		gbc_textFieldOib.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldOib.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldOib.gridx = 1;
		gbc_textFieldOib.gridy = 2;
		contentPaneOsoba.add(textFieldOib, gbc_textFieldOib);
		
		
//		JButton btnSpremi = new JButton("Spremi");
//		GridBagConstraints gbc_btnSpremi = new GridBagConstraints();
//		gbc_btnSpremi.gridx = 1;
//		gbc_btnSpremi.gridy = 3;
//		contentPaneOsoba.add(btnSpremi, gbc_btnSpremi);
//		//Action listener
//		btnSpremi.addActionListener(new ActionListener() {   
//			public void actionPerformed(ActionEvent arg0) {    
//				String prezime = textFieldPrezime.getText();     
//				String ime = textFieldIme.getText();     
//				String oib = textFieldOib.getText();     
//				Osoba novaOsoba = new Osoba(prezime, ime, oib);     
//				listaOsoba.add(novaOsoba);     
//				dispose();     
//				JOptionPane.showMessageDialog(null, "Uspješno ste unijeli osobu: " +       prezime + " " + ime);   
//				} 
//			});
		
		JButton btnSpremi = new JButton("Spremi"); 
		
		GridBagConstraints gbc_btnSpremi = new GridBagConstraints();
		gbc_btnSpremi.gridx = 1;
		gbc_btnSpremi.gridy = 3;
		contentPaneOsoba.add(btnSpremi, gbc_btnSpremi);
		
			btnSpremi.addActionListener(new ActionListener() {  
				public void actionPerformed(ActionEvent arg0) {    
					String prezime = textFieldPrezime.getText();     
					String ime = textFieldIme.getText();     
					String oib = textFieldOib.getText();
					Osoba novaOsoba = new Osoba(prezime, ime, oib);     
					BazaPodataka.spremiOsobu(novaOsoba);     
					dispose();    
					
					JOptionPane.showMessageDialog(null, "Uspješno ste unijeli osobu: " + prezime + " " + ime);   
				}  
			});  
	}

}
