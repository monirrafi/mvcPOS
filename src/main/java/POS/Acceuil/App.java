package POS.Acceuil;

import POS.dao_Caissier.vueCaissier.VueCaissier;
import POS.dao_Produit.vueProduit.VueProduit;

public final class App {
    private App() {
    }

    public static void main(String[] args) {
        VueProduit vueProduit = new VueProduit();
        vueProduit.action();
        vueProduit.setVisible(true);

/*
        Acceuil acceuil = new Acceuil();
        acceuil.action();
        acceuil.setVisible(true);
        VueCaissier vueCaissier = new VueCaissier();
        vueCaissier.action();
        vueCaissier.setVisible(true);
*/
    }
}