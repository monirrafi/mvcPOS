package POS.Acceuil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import POS.dao_Caissier.vueCaissier.VueCaissier;
import POS.dao_Produit.vueProduit.VueProduit;

public final class App {
    private App() {
    }

    public static void main(String[] args) {
        Caisse caisse = new Caisse();
        caisse.setVisible(true);
        caisse.action();

        /*

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