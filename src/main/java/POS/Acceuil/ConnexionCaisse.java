package POS.Acceuil;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import POS.dao_Caissier.controleurCaissier.ControleurCaissier;
import POS.dao_Caissier.modelCaissier.Caissier;

public class ConnexionCaisse extends JFrame implements actionEvent {

    private ControleurCaissier ctrCaissier = ControleurCaissier.getControleurCaissier();
    private JPanel contentPane = new JPanel();
    private JTextField txtUserName = new JTextField(); 
    private JPasswordField txtMotDePasse = new JPasswordField(); 
    private String nomCaissier = "";
    final int H = 400;
    final int W = 600;

    public ConnexionCaisse(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, W, H);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setBackground(new Color(0,0,0));

		setContentPane(contentPane);
		contentPane.setLayout(null);

        JLabel lblUserName = new JLabel("User Name");
        lblUserName.setBounds(W/4, H/3, 400, 40);
        lblUserName.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
        lblUserName.setForeground(new Color(255,255,0));
        lblUserName.setLabelFor(txtUserName);
        contentPane.add(lblUserName);

        txtUserName = new JTextField();
        txtUserName.setBounds((W/4)+160, H/3+5, 150, 20);
        contentPane.add(txtUserName);

        JLabel lblMotDePasse = new JLabel("Mot Du passe");
        lblMotDePasse.setBounds(W/4, (H/3)+30, 400, 40);
        lblMotDePasse.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 20));
        lblMotDePasse.setForeground(new Color(255,255,0));
        lblMotDePasse.setLabelFor(txtMotDePasse);
        contentPane.add(lblMotDePasse);

        txtMotDePasse = new JPasswordField();
        txtMotDePasse.setBounds((W/4)+160, (H/3+5)+30, 150, 20);
        contentPane.add(txtMotDePasse);


    }
    public void verifierConnexion() {
        String strSql = "SELECT * FROM CAISSIER WHERE USERNAME = '" + txtUserName.getText() + "' AND MOTDEPASSE = '" + txtMotDePasse.getText() + "'";
        if(ctrCaissier.CtrCaissier_GetParRequete(strSql).size() != 0){
            Caissier caissier = ctrCaissier.CtrCaissier_GetParRequete(strSql).get(0);
            setNomCaissier(caissier.getNom());
            this.dispose();

        }else{
            nomCaissier = "";
            JOptionPane.showMessageDialog(null, " username ou mot de passe incorrecte!!");
        }
        txtUserName.setText("");
        txtMotDePasse.setText("");
        
    }
    public void actionBtn(ActionEvent ev){
		if(ev.getSource()== txtMotDePasse){
         verifierConnexion();
        }
    }
    

    @Override
    public void action() {
        txtMotDePasse.addActionListener(this::actionBtn);
        
    }

    public void setNomCaissier(String nomCaissier) {
        this.nomCaissier = nomCaissier;
    }

    public String getNomCaissier() {
        return nomCaissier;
    }
    
}
