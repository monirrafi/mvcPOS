package POS.dao_Produit.vueProduit;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import POS.Acceuil.actionEvent;
import POS.dao_Produit.controleurProduit.ControleurProduit;
import POS.dao_Produit.modelProduit.Produit;

import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;

import java.awt.Color;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.*;
import java.util.ArrayList;

public class VueProduit extends JFrame implements actionEvent{
	private ControleurProduit ctrProduit = ControleurProduit.getControleurProduit();
	private JPanel contentPane;
	private JTextField txtNom;
	private JTextField txtPrixVente;
	private JTextField txtTPS;
	private JTextField txtTVQ;
	private JTextField txtCode;
	private JTable table;
	JButton btnAjouter = new JButton();
	JButton btnModifier = new JButton();
	JButton btnSuprimer = new JButton();
	JButton btnLister = new JButton();
	JComboBox<String> cmbNom = new JComboBox<>();
		
	public VueProduit() {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 950, 431);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitre = new JLabel("Mise à jour des Produits");
		lblTitre.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 30));
		lblTitre.setBounds(10, 10, 440, 50);
		contentPane.add(lblTitre);
		
		JPanel paneChamps = new JPanel();
		paneChamps.setBorder(new LineBorder(new Color(0, 0, 0)));
		paneChamps.setBounds(10, 70, 350, 235);
		contentPane.add(paneChamps);
		paneChamps.setLayout(null);
		
		JLabel lblNom = new JLabel("Nom Produit");
		lblNom.setBounds(20, 5, 117, 19);
		lblNom.setFont(new Font("Times New Roman", Font.BOLD, 12));
		paneChamps.add(lblNom);
		
		txtNom = new JTextField();
		txtNom.setBounds(142, 5, 198, 19);
		lblNom.setLabelFor(txtNom);
		paneChamps.add(txtNom);
		txtNom.setColumns(10);
		
	
		
		JLabel lblPrixVente = new JLabel("Prix Vente");
		lblPrixVente.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblPrixVente.setBounds(20, 35, 117, 19);
		paneChamps.add(lblPrixVente);
		
		txtPrixVente = new JTextField();
		lblPrixVente.setLabelFor(txtPrixVente);
		txtPrixVente.setBounds(142, 35, 198, 19);
		paneChamps.add(txtPrixVente);
		
		JLabel lblTPS= new JLabel("TPS");
		lblTPS.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblTPS.setBounds(20, 65, 117, 19);
		paneChamps.add(lblTPS);
		
		txtTPS = new JTextField();
		lblPrixVente.setLabelFor(txtTPS);
		txtTPS.setBounds(142, 65, 198, 19);
		paneChamps.add(txtTPS);

		JLabel lblTVQ = new JLabel("TVQ");
		lblTVQ.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblTVQ.setBounds(20, 95, 117, 19);
		paneChamps.add(lblTVQ);
		
		txtTVQ = new JTextField();
		lblPrixVente.setLabelFor(txtTVQ);
		txtTVQ.setBounds(142, 95, 198, 19);
		paneChamps.add(txtTVQ);

		JLabel lblCode = new JLabel("Code produit");
		lblCode.setLabelFor(txtCode);
		lblCode.setFont(new Font("Times New Roman", Font.BOLD, 12));
		lblCode.setBounds(20, 130, 117, 19);
		paneChamps.add(lblCode);

		txtCode = new JTextField();
		txtCode.setColumns(10);
		txtCode.setBounds(142, 130, 198, 19);
		paneChamps.add(txtCode);
		
		
		JPanel paneTable = new JPanel();
		paneTable.setBounds(370, 70, 545, 207);
		contentPane.add(paneTable);
		paneTable.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 525, 187);
		paneTable.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null},
			},
			new String[] {
				"Nom Produit", "Prix Vente", "Code Produit","TPS", "TVQ"
			}
		));
		
		JPanel panelBouton = new JPanel();
		panelBouton.setBounds(10, 334, 350, 50);
		contentPane.add(panelBouton);
		panelBouton.setLayout(null);
		
		btnAjouter = new JButton("Ajouter");
		btnAjouter.setBackground(new Color(255, 128, 0));
		btnAjouter.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		btnAjouter.setBounds(132, 5, 85, 35);
		panelBouton.add(btnAjouter);
		
		btnModifier = new JButton("Modifier");
		btnModifier.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		btnModifier.setBackground(new Color(255, 128, 0));
		btnModifier.setBounds(238, 5, 102, 35);
		panelBouton.add(btnModifier);
		
		btnSuprimer = new JButton("Suprimer");
		btnSuprimer.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		btnSuprimer.setBackground(new Color(255, 128, 0));
		btnSuprimer.setBounds(10, 5, 95, 35);
		panelBouton.add(btnSuprimer);
		
		btnLister = new JButton("Afficher tout");
		btnLister.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 15));
		btnLister.setBackground(new Color(0, 128, 255));
		btnLister.setBounds(757, 25, 158, 35);
		contentPane.add(btnLister);
		
		JLabel lblProduit = new JLabel("Choisir Nom");
		lblProduit.setForeground(new Color(0, 0, 255));
		lblProduit.setFont(new Font("Times New Roman", Font.BOLD, 15));
		lblProduit.setBounds(500, 30, 196, 28);
		contentPane.add(lblProduit);
		
		cmbNom = new JComboBox<>(getListeCBox());
		cmbNom.setBounds(600, 30, 150, 28);
		contentPane.add(cmbNom);
	}

/*============================================================================================================= */
/*										Ecouetuers																*/
/*============================================================================================================= */

	public void actionBtn(ActionEvent ev){
		if(ev.getSource()== btnLister){
			table.setModel(remplirTable("",""));
			viderChamps();

		}else if(ev.getSource()== btnModifier){
			modifierProduit();

		}else if(ev.getSource()== btnSuprimer){
			Suprimer();

		}else if(ev.getSource()== btnAjouter){
			//String strCle = JOptionPane.showInputDialog(null, "Entrez le nom du Produit a ajouter");
			ajouter();

		}
	}

	public void itemStateChanged(ItemEvent e) {
		if(e.getSource()== cmbNom){
			String nom = (String)cmbNom.getSelectedItem();
			//nom = nom.split("_")[0];
			DefaultTableModel model = remplirTable("NOM_Produit",nom);
			table.setModel(model);
			//remplirChamps(nom);
			
		}
	}
	public void valueChanged(ListSelectionEvent e) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		String nomChoisi="";
		if(table.getSelectedRowCount() ==1){
			nomChoisi =  model.getValueAt( table.getSelectedRow(),table.getSelectedColumn()).toString();
			Produit Produit = ctrProduit.CtrProduit_GetByChamps("NOM_Produit", nomChoisi).get(0);
			remplirChamps(String.valueOf(Produit.getIdProduit()));
		}
		
	}


	
/*============================================================================================================= */
/*										Fonctions																*/
/*=============================================================================================================*/
public void viderChamps() {
	txtNom.setText("");
	txtCode.setText("");
	txtPrixVente.setText("");
	txtTPS.setText("");
	txtTVQ.setText("");
}
public DefaultTableModel remplirTable(String champs,String valeur) {
	ArrayList<Produit> listeProduits = new ArrayList<>();
	
	if(champs.equals("")){
		listeProduits = (ArrayList<Produit>) ctrProduit.CtrProduit_GetAll(); 	

	}else{
		
		listeProduits = (ArrayList<Produit>) ctrProduit.CtrProduit_GetByChamps(champs, valeur);

	}
	String[] column = {"Nom Produit", "Prix Vente", "Code Produit","TPS", "TVQ"};
	DefaultTableModel model = new DefaultTableModel(column,0);
	
	
	for(Produit produit:listeProduits){
			model.addRow(new Object[]{produit.getNom(),produit.getPrixVente(),produit.getCodeProduit(),produit.getTPS(),produit.getTVQ()});				
		}
	return model;

}

public  String[] getListeCBox(){

	String[] retour =new String[1];
	ArrayList<String>  liste = new ArrayList<String>();
	ArrayList<String>  listeTmp = new ArrayList<String>();
	for(Produit Produit:ctrProduit.CtrProduit_GetAll()){		
			liste.add(String.valueOf(Produit.getNom()));
	}

	listeTmp.add(liste.get(0));
	for(String current:liste){
		if(listeTmp.indexOf(current)==-1){
			listeTmp.add(current);
		}

	}


	retour = new String[listeTmp.size()];
	for(int i=0;i<listeTmp.size();i++){
		retour[i]=listeTmp.get(i);
	}
	
	return retour;
	
	}
	public void remplirChamps(String nom) {
		Produit Produit = ctrProduit.CtrProduit_GetByChamps("ID_Produit", nom).get(0);
		txtNom.setText(Produit.getNom());
		txtCode.setText(Produit.getCodeProduit());
		txtPrixVente.setText(String.valueOf(Produit.getPrixVente()));
		txtTPS.setText(String.valueOf(Produit.getTPS()));
		txtTVQ.setText(String.valueOf(Produit.getTVQ()));
	}

	public boolean verifierRemplissageChamps() {
		if(!txtCode.getText().equals("") & txtCode.getText() != null &
		!txtNom.getText().equals("") & txtNom.getText() != null &
		!txtTPS.getText().equals("") & txtTPS.getText() != null &
		!txtTVQ.getText().equals("") & txtTVQ.getText() != null &
		!txtPrixVente.getText().equals("") & txtPrixVente.getText() != null ){
			return true;

		}
		return false;
		
	}

/*============================================================================================================= */
/*										S-A-R     															    */
/*=============================================================================================================*/
public void Suprimer() {
	if(verifierRemplissageChamps()){
		String strCle = txtCode.getText();
		int rep = JOptionPane.showConfirmDialog(null, "Voulez-vous suprimer:\n"+ strCle ,"SOUPRESSION", JOptionPane.YES_NO_OPTION);
		if(rep==0){
		int cle = ctrProduit.CtrProduit_GetByChamps("CODE_PRODUIT", strCle).get(0).getIdProduit();
		ctrProduit.CtrProduit_Enlever(cle);

		DefaultComboBoxModel<String> modelNum = new DefaultComboBoxModel<>(getListeCBox());
		cmbNom.removeAll();
		cmbNom.setModel(modelNum);
		}	
	}else{
		JOptionPane.showMessageDialog(null,"Choisissez d'abord le nom!?");
	}
	DefaultTableModel modelTable = remplirTable("","");
	table.setModel(modelTable);
	viderChamps();
}
public void ajouter() {
	if(verifierRemplissageChamps()){
		String strCle = txtNom.getText();
		if(ctrProduit.CtrProduit_GetByChamps("code_Produit", strCle).size() != 0){
				JOptionPane.showMessageDialog(null, "l'Produit  existe déjà!!","AJOUT", JOptionPane.YES_NO_OPTION);
		}else{

		Produit Produit = new Produit(txtNom.getText(),Double.parseDouble(txtPrixVente.getText()),txtCode.getText(),Boolean.parseBoolean(txtTPS.getText()),Boolean.parseBoolean(txtTVQ.getText()));
		ctrProduit.CtrProduit_Enregistrer(Produit);

				DefaultComboBoxModel<String> modelNum = new DefaultComboBoxModel<>(getListeCBox());
				cmbNom.removeAll();
				cmbNom.setModel(modelNum);

				DefaultTableModel modelTable = remplirTable("","");
				table.setModel(modelTable);
				viderChamps();
			}	
		}else{
			JOptionPane.showMessageDialog(null,"Remplissez tous les champs !?");
		}

}

public void modifierProduit() {
	if(verifierRemplissageChamps()){
		String strCle = txtCode.getText();
		int rep = JOptionPane.showConfirmDialog(null, "Voulez-vous enregistrer les modifications portées sur :\n"+ strCle ,"MODIFICATION", JOptionPane.YES_NO_OPTION);
		Produit Produit = ctrProduit.CtrProduit_GetByChamps("CODE_PRODUIT", strCle).get(0);

		if(rep==JOptionPane.YES_OPTION){
		
			Produit ProduitNew = new Produit(Produit.getIdProduit(),txtNom.getText(),Double.parseDouble(txtPrixVente.getText()),txtCode.getText(),Boolean.parseBoolean(txtTPS.getText()),Boolean.parseBoolean(txtTVQ.getText()));
	
			ctrProduit.CtrProduit_Modifier(ProduitNew);
	
			DefaultComboBoxModel<String> modelNum = new DefaultComboBoxModel<>(getListeCBox());
			cmbNom.removeAll();
			cmbNom.setModel(modelNum);
		}	
	}else{
		JOptionPane.showMessageDialog(null,"Remplissez tous les champs!?");
	}
	DefaultTableModel modelTable = remplirTable("","");
	table.setModel(modelTable);
	viderChamps();

	}

	@Override
	public void action() {
		cmbNom.addItemListener(this::itemStateChanged);
		btnLister.addActionListener(this::actionBtn);
		btnAjouter.addActionListener(this::actionBtn);
		btnModifier.addActionListener(this::actionBtn);
		btnSuprimer.addActionListener(this::actionBtn);
		table.getSelectionModel().addListSelectionListener(this::valueChanged);
		
	}	
	
	public ControleurProduit getControleur() {
		return ctrProduit;
		
	}

}