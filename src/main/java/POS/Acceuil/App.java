package POS.Acceuil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import POS.dao_Caissier.vueCaissier.VueCaissier;
import POS.dao_DetailFacture.controleurDetailFacture.ControleurDetailFacture;
import POS.dao_DetailFacture.modelDetailFacture.DetailFacture;
import POS.dao_Produit.vueProduit.VueProduit;

public final class App {
    private App() {
    }

    public static void main(String[] args) {
        Caisse caisse = new Caisse();
        caisse.setVisible(true);
        caisse.afficherFacture();
        caisse.action();
        /*
        ControleurDetailFacture  CtrDetailFacture = ControleurDetailFacture.getControleurDetailFacture();
        DetailFacture DetailFacture = new DetailFacture(1,1,2.39);
        CtrDetailFacture.CtrDetailFacture_Enregistrer(DetailFacture);
         System.out.println(CtrDetailFacture.CtrDetailFacture_GetAll().toString());



        Acceuil acceuil = new Acceuil();
        acceuil.setVisible(true);
        acceuil.action();


        VueProduit vueProduit = new VueProduit();
        vueProduit.action();
        vueProduit.setVisible(true);
        
        VueProduit vueProduit = new VueProduit();
        vueProduit.action();
        vueProduit.setVisible(true);

        VueCaissier vueCaissier = new VueCaissier();
        vueCaissier.action();
        vueCaissier.setVisible(true);
*/
    }
}