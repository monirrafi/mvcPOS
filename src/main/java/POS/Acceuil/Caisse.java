package POS.Acceuil;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import POS.dao_Caissier.controleurCaissier.ControleurCaissier;
import POS.dao_Caissier.modelCaissier.Caissier;
import POS.dao_Produit.controleurProduit.ControleurProduit;
import POS.dao_Produit.modelProduit.Produit;

import java.awt.Font;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

public class Caisse extends JFrame implements actionEvent {

    private ControleurProduit Ctr_produit = ControleurProduit.getControleurProduit();
    private ControleurCaissier Ctr_caissier = ControleurCaissier.getControleurCaissier();
	private JPanel contentPane;
	private JTextField txtEntree;
    private JTextPane textPane;
/*	private String listeProduit = "";
	private double sousTotal =0;
	private double tpsTotal =0;
	private double tvqTotal =0;
	private double montantTotal =0;
	*/
	private HashMap<String,Integer> hasProduit = new HashMap<>();
	
	final double TAUX_TPS=5.0;
	final double TAUX_TVQ=9.975;
	final String NOM_MAGASIN = "Menara";
	final String ADRESSE1_MAGASIN = "3483 Boulvard Cartier O";
	final String CODE_POSTAL_MAGASIN = "H7V 3T4";
	final String TEL_MAGASIN = "450-978-9595";
	final String EMAIL_MAGASIN = "entreprisemenara@hotmail.com";


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
		int pos = entree.indexOf("*");
		if(pos == -1){
			retour[0]="1";
			retour[1]= entree;

		}else{
			retour[0]=entree.substring(0, pos);
			retour[1]=entree.substring(pos+1);
		}

		return retour;

	}
	public String entrerProduit(String entree) {
		String qty = separationEntree(entree)[0];
		String code = separationEntree(entree)[1];
		int val = Integer.parseInt(qty);
        if(Ctr_produit.CtrProduit_GetByChamps("CODE_PRODUIT", code).size()==0){
			return code;
		}else{
		
			if(hasProduit.containsKey(code)){
				val = hasProduit.get(code);
				val = val + Integer.parseInt(qty);
			}
			hasProduit.put(code,val);
			return "1";
		}
	}
	
    public void afficherFacture(){
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern( "yyyy/MM/dd HH:mm:ss" );  
   		LocalDateTime now = LocalDateTime.now();  
		DecimalFormat df = new DecimalFormat("0.00");
		String tx = "";
		double tps = 0,tvq=0;
		int qty = 1;
		String listeProduit = "";
		double sousTotal =0;
		double tpsTotal =0;
		double tvqTotal =0;
		double montantTotal =0;
	
		Caissier caissier = Ctr_caissier.CtrCaissier_GetByChamps("USERNAME", "monirr").get(0);

		String text =NOM_MAGASIN + "\t\t\t\t\t\t"+ "Caissier\t" +caissier.getNom()+"\n" + ADRESSE1_MAGASIN + "\t\t\t\t\t"+ dtf.format(now) +"\n" +  CODE_POSTAL_MAGASIN  +"\n" +  TEL_MAGASIN + "\n" +  EMAIL_MAGASIN + "\n"; 
		text +="\n\n"+ packMot("Nom Produit",60) + packMot("Prix",10)+"\tTX\tQty\tMontant article\n";
		text +="================================================================================\n";

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
		text += listeProduit;
		text +="\n\n\n================================================================================\n";
		text += packMot("Sous Total :",20) + df.format(sousTotal) + "\n";
		text += packMot("Total TPS :",20) + df.format(tpsTotal) + "\n";
		text += packMot("Total TVQ :",20) + df.format(tvqTotal) + "\n";
		text += packMot("Montant Total :",20) + df.format(montantTotal) + "\n";
		textPane.setText(text);

    }
    public void actionBtn(ActionEvent ev){
		if(ev.getSource()== txtEntree){
			if(entrerProduit(txtEntree.getText()).equals("1")){
				afficherFacture();
			}else{
				JOptionPane.showMessageDialog(null, entrerProduit(txtEntree.getText()) + " n'existe pas");
			}
			txtEntree.setText("");
			contentPane.repaint();

		}
	}

    @Override
	public void action() {
		txtEntree.addActionListener(this::actionBtn);
		
	}	


}
