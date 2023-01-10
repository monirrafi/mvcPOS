package POS.Acceuil;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import POS.dao_DetailFacture.controleurDetailFacture.ControleurDetailFacture;
import POS.dao_DetailFacture.modelDetailFacture.DetailFacture;
import POS.dao_Facture.controleurFacture.ControleurFacture;
import POS.dao_Facture.modelFacture.Facture;
import POS.dao_Produit.controleurProduit.ControleurProduit;
import POS.dao_Produit.modelProduit.Produit;

import java.awt.Font;
import java.awt.event.*;
import java.awt.print.PrinterException;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;

public class Caisse extends JFrame implements actionEvent {

    private ControleurProduit Ctr_produit = ControleurProduit.getControleurProduit();
    private ControleurFacture Ctr_Facture = ControleurFacture.getControleurFacture();
    private ControleurDetailFacture Ctr_DetailFacture = ControleurDetailFacture.getControleurDetailFacture();
    private String caissier = "";
	private int noFacture = 0;
	private JPanel contentPane;
	private JTextField txtEntree;
    private JTextPane textPane = new JTextPane();
	private HashMap<String,Integer> hasProduit = new HashMap<>();
	
	final double TAUX_TPS=5.0;
	final double TAUX_TVQ=9.975;
	final String NOM_MAGASIN = "Menara";
	final String ADRESSE1_MAGASIN = "3483 Boulvard Cartier O";
	final String CODE_POSTAL_MAGASIN = "H7V 3T4";
	final String TEL_MAGASIN = "450-978-9595";
	final String EMAIL_MAGASIN = "entreprisemenara@hotmail.com";

	DateTimeFormatter dtf = DateTimeFormatter.ofPattern( "yyyy/MM/dd HH:mm:ss" );  
	LocalDateTime now = LocalDateTime.now();  
	DecimalFormat df = new DecimalFormat("0.00");

	public Caisse() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 887, 487);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 586, 382);
		contentPane.add(scrollPane);
		
		textPane = new JTextPane();
		scrollPane.setViewportView(textPane);
		
		txtEntree = new JTextField();
		txtEntree.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 30));

		txtEntree.setBounds(10, 402, 586, 38);
		contentPane.add(txtEntree);
		txtEntree.setColumns(10);
		
		JPanel paneVideo = new JPanel();
		paneVideo.setBounds(606, 10, 257, 215);
		contentPane.add(paneVideo);
		
		JLabel lblVideo = new JLabel("Video");
		paneVideo.add(lblVideo);
		
		JPanel paneAffiche = new JPanel();
		paneAffiche.setBounds(606, 235, 257, 205);
		contentPane.add(paneAffiche);
		
		JLabel lblimage = new JLabel("Image");
		paneAffiche.add(lblimage);
		addWindowListener( new WindowAdapter() {
			public void windowOpened( WindowEvent e ){
				txtEntree.requestFocus();
			}
		}); 	
	}
	public String packMot(String mot, int max){
		String retour=mot;
		int lng = mot.length();
		if(lng<= max){
			for(int i=0;i<max-lng;i++){
				retour += " ";
			}
		}else{
			retour = mot.substring(0,max);
		}
		return retour;
	}
	public String[] separationEntree(String entree){
		String[] retour = new String[3];
		String[] codeQty = new String[2];
		if(entree.charAt(0)=='-'){
			retour[0]="-";
		}else{
			if(entree.charAt(0)=='$'){
				retour[0]="$";
				retour[1]=String.valueOf(entree.charAt(1));
				retour[2]=entree.substring(2);
			}else{
				retour[0]="+";

			}
		}
		if(!retour[0].equals("$")){
			int pos = entree.indexOf("*");
			if(pos == -1){
				codeQty[0]="1";
				codeQty[1]= entree;

			}else{
				codeQty[0]=entree.substring(0, pos);
				codeQty[1]=entree.substring(pos+1);
			}
			retour[1]=codeQty[0];
			retour[2]=codeQty[1];
		}
		return retour;

	}
	public void imprimerFacture() {
		try {
			textPane.print();
		} catch (PrinterException e) {
			e.printStackTrace();
		}
		
	}
	public String entrerProduit(String entree) {
		String msg = "";
		if(entree.length()>1){
			if (separationEntree(entree)[0].equals("$")) {
				if(separationEntree(entree)[1].equalsIgnoreCase("A") ||
				separationEntree(entree)[1].equalsIgnoreCase("D") ||
				separationEntree(entree)[1].equalsIgnoreCase("C") ){
					msg= "1";
				}else{
					msg= "Le type du paiement doit etre 'A' ou 'D' ou 'C' \n Exemple '$A7.35 ou $D108.35";
				}
			} else {
					
				
				String qty = separationEntree(entree)[1];
				String code = separationEntree(entree)[2];
				int val = Integer.parseInt(qty);
				if(Ctr_produit.CtrProduit_GetByChamps("CODE_PRODUIT", code).size()==0){
					msg = "Le " + code + " n'existe pas";
				}else{
				
					if(hasProduit.containsKey(code)){
						val = hasProduit.get(code);
						val = val + Integer.parseInt(qty);
					}
					hasProduit.put(code,val);
					msg= "1";
				}
			}
		}else{
			msg = "le code ou le paiement est incomplet!";
		}
		return msg;
	}
	public void sauvegarderFacture() {
		Ctr_Facture.CtrFacture_Enregistrer(new Facture(0,1,dtf.format(now)));
		for(String code:hasProduit.keySet()){
			Produit produit = Ctr_produit.CtrProduit_GetByChamps("CODE_PRODUIT",code).get(0);
			Ctr_DetailFacture.CtrDetailFacture_Enregistrer(new DetailFacture(noFacture, produit.getIdProduit(), hasProduit.get(code)));
		}

		
	}
	public String afficherEntete() {
		String text = "Facture No : " + noFacture + "\n" ;
		text += NOM_MAGASIN + "\t\t\t\t\t\t"+ "Caissier\t" +caissier+"\n" + ADRESSE1_MAGASIN + "\t\t\t\t\t"+ dtf.format(now) +"\n" +  CODE_POSTAL_MAGASIN  +"\n" +  TEL_MAGASIN + "\n" +  EMAIL_MAGASIN + "\n"; 
		text +="\n\n"+ packMot("Nom Produit",60) + packMot("Prix",10)+"\tTX\tQty\tMontant article\n";
		text +="================================================================================\n";
		return text;
	}

	public void afficherFacture(){
			String lettre1 = "";
			noFacture = Ctr_Facture.CtrFacture_GetDernier()+1;
			String tx = "";
			double tps = 0,tvq=0;
			int qty = 1;
			String listeProduit = "";
			double sousTotal =0;
			double tpsTotal =0;
			double tvqTotal =0;
			double montantTotal =0;
			double montantPaiement=0;
			String typePaiement="";
			double change=0;

			String text = afficherEntete();
			if(!txtEntree.getText().equals("") & txtEntree != null){
				lettre1 = separationEntree(txtEntree.getText())[0];
					for(String code:hasProduit.keySet()){
						Produit produit = Ctr_produit.CtrProduit_GetByChamps("CODE_PRODUIT", code).get(0);
						qty = hasProduit.get(code);
						if(produit.getTPS()==true){
							tx="x";
							tps = produit.getPrixVente()*(TAUX_TPS/100);
							tvq = produit.getPrixVente()*(TAUX_TVQ/100); 
						}
						double montantItem = (qty)*(produit.getPrixVente() + tps + tvq);
						listeProduit += packMot(produit.getNom(),60) + packMot(String.valueOf(produit.getPrixVente()),10) + "\t" + tx  + "\t" + qty  + "\t" + df.format(montantItem) +"\n";

						sousTotal += produit.getPrixVente()*(qty);
						tpsTotal += tps;
						tvqTotal += tvq;
					}
					montantTotal = sousTotal + tpsTotal + tvqTotal;
				
				if(lettre1.equals("$")){
					if(separationEntree(txtEntree.getText())[1].equalsIgnoreCase("A")){
						typePaiement = "Cash";
					}else if(separationEntree(txtEntree.getText())[1].equalsIgnoreCase("C")){
						typePaiement = "Carte Credit";
					}else if(separationEntree(txtEntree.getText())[1].equalsIgnoreCase("D")){
						typePaiement = "Carte debit";
					}
					montantPaiement = Double.parseDouble(separationEntree(txtEntree.getText())[2]);
					change= montantPaiement-montantTotal;
			
				}

			}	
			text += listeProduit;
			text +="\n\n\n================================================================================\n";
			text += packMot("Sous Total :",20) + df.format(sousTotal) + "\t\t\t" + packMot("Montant Paiement :",20) + montantPaiement + "\n";
			text += packMot("Total TPS :",20) + df.format(tpsTotal) + "\t\t\t" + packMot("Type Paiement :",20) + typePaiement + "\n";
			text += packMot("Total TVQ :",20) + df.format(tvqTotal) + "\t\t\t" + packMot("Change :",20) +  df.format(change) + "\n";
			text += packMot("Montant Total :",20) + df.format(montantTotal) + "\n";
			textPane.setText(text);
			if(lettre1.equals("$")){
				JOptionPane.showMessageDialog(null, "Votre change " + df.format(change));
				sauvegarderFacture();
				imprimerFacture();
				noFacture= Ctr_Facture.CtrFacture_GetDernier() +1 ;
				text = "";
				text = afficherEntete();
				hasProduit.clear();
				tx = "";
				tps = 0;
				tvq=0;
				qty = 1;
				listeProduit = "";
				sousTotal =0;
				tpsTotal =0;
				tvqTotal =0;
				montantTotal =0;
				montantPaiement=0;
				typePaiement="";
				change=0;
				lettre1="";
				text += listeProduit;
				text +="\n\n\n================================================================================\n";
				text += packMot("Sous Total :",20) + df.format(sousTotal) + "\t\t\t" + packMot("Montant Paiement :",20) + montantPaiement + "\n";
				text += packMot("Total TPS :",20) + df.format(tpsTotal) + "\t\t\t" + packMot("Type Paiement :",20) + typePaiement + "\n";
				text += packMot("Total TVQ :",20) + df.format(tvqTotal) + "\t\t\t" + packMot("Change :",20) +  df.format(change) + "\n";
				text += packMot("Montant Total :",20) + df.format(montantTotal) + "\n";
	
				textPane.setText(text);
				
			}
    }
    public void actionBtn(ActionEvent ev){
		if(ev.getSource()== txtEntree){
			if(entrerProduit(txtEntree.getText()).equals("1")){
				afficherFacture();
			}else{
				JOptionPane.showMessageDialog(null, entrerProduit(txtEntree.getText()));
			}
			txtEntree.setText("");
			contentPane.repaint();

		}
	}

    @Override
	public void action() {
		txtEntree.addActionListener(this::actionBtn);
		
	}
	public String getCaissier() {
		return caissier;
	}
	public void setCaissier(String caissier) {
		this.caissier = caissier;
	}

	
}
