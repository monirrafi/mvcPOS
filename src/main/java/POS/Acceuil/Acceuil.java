package POS.Acceuil;

import java.awt.*;

import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.EmptyBorder;

public class Acceuil extends JFrame implements actionEvent{
    private JPanel contentPane;
    private JButton btnConnexion = new JButton("Connexion");
    private JButton btnCaisse = new JButton("Caisse");
    static ConnexionCaisse caisseConnexion = new ConnexionCaisse();
    static String nomCaissier = "";



    public Acceuil() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1300, 650);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel();
		lblNewLabel_1.setIcon(new ImageIcon("src\\main\\java\\Projet_Collecte_Sang\\Acceuil\\logo1.jpg"));
		lblNewLabel_1.setBounds(215, 123, 1100, 300);
		contentPane.add(lblNewLabel_1);

        btnCaisse.setBounds(100,500,300,30);
        contentPane.add(btnCaisse);

        btnConnexion.setBounds(400,500,300,30);
        contentPane.add(btnConnexion);


    }
    public void actionBtn(ActionEvent e){
        if(e.getSource() == btnCaisse){
            nomCaissier = caisseConnexion.getNomCaissier();
            if(!nomCaissier.equals("")){
                Caisse caisse = new Caisse();
                caisse.setVisible(true);
                caisse.setCaissier(nomCaissier);
                caisse.afficherFacture();
                caisse.action();
                //caisseConnexion.setVisible(false);
            }else{
                JOptionPane.showMessageDialog(null,"Il faut se connecter d'abord!!");
            }
        }else if(e.getSource() == btnConnexion){
            caisseConnexion.setVisible(true);
            caisseConnexion.action();

    
        }

    }
    @Override
    public void action() {
        btnCaisse.addActionListener(this::actionBtn);
        btnConnexion.addActionListener(this::actionBtn);
        
    }

}
