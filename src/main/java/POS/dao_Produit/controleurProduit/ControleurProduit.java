package POS.dao_Produit.controleurProduit;

import java.util.List;

import POS.dao_Produit.modelProduit.Produit;
import POS.dao_Produit.modelProduit.DaoProduit;


public class ControleurProduit implements IActionsProduit {

    private static ControleurProduit CtrProduit_Instance = null;
    private static DaoProduit Dao_Instance = null;
    // Singleton du contrôleur
    // getControleurFilm() est devenu une zonne critique.
    // Pour ne pas avoir deux processus légers (threads) qui
    // appellent au même temps getConnexion
    private ControleurProduit(){}

    public static synchronized ControleurProduit getControleurProduit() {
        try {
            if (CtrProduit_Instance == null) {
                CtrProduit_Instance = new ControleurProduit();
                Dao_Instance = DaoProduit.getProduitDao();
            }
            return CtrProduit_Instance;
        } catch (Exception e) {
            // e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    @Override
    public List<Produit> CtrProduit_GetAll() {
        return Dao_Instance.MdlProduit_GetAll();
    }

    @Override
    public String CtrProduit_Enregistrer(Produit produit) {
        String message = null;
        message = Dao_Instance.MdlProduit_Enregistrer(produit);
        return message;
    }

/*
    @Override
    public Produit CtrProduit_GetById(int id) {
        return Dao_Instance.MdlProduit_GetByID(id);
    }
*/
    @Override
    public List<Produit> CtrProduit_GetByChamps(String champs, String valeur) {
        return Dao_Instance.MdlProduit_GetByChamps(champs, valeur);
    }

    @Override
    public int CtrProduit_Modifier(Produit produit) {
        return Dao_Instance.MdlProduit_Modifier(produit);
    }

    @Override
    public int CtrProduit_Enlever(int id) {
        return Dao_Instance.MdlProduit_Supprimer(id);
    }

    @Override
    public int CtrProduit_EnregistrerParRequete(String strSql, String valeur) {
        return Dao_Instance.MdlProduit_EnregistrerParRequete(strSql,valeur);
    }


    @Override
    public List<Produit> CtrProduit_GetParRequete(String strSql) {
        
        return Dao_Instance.MdlProduit_GetParRequete(strSql);
    }

    

}
