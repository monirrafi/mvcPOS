package POS.dao_Paiement.controleurPaiement;

import java.util.List;

import POS.dao_Paiement.modelPaiement.Paiement;
import POS.dao_Paiement.modelPaiement.DaoPaiement;


public class ControleurPaiement implements IActionsPaiement {

    private static ControleurPaiement CtrPaiement_Instance = null;
    private static DaoPaiement Dao_Instance = null;
    // Singleton du contrôleur
    // getControleurFilm() est devenu une zonne critique.
    // Pour ne pas avoir deux processus légers (threads) qui
    // appellent au même temps getConnexion
    private ControleurPaiement(){}

    public static synchronized ControleurPaiement getControleurPaiement() {
        try {
            if (CtrPaiement_Instance == null) {
                CtrPaiement_Instance = new ControleurPaiement();
                Dao_Instance = DaoPaiement.getPaiementDao();
            }
            return CtrPaiement_Instance;
        } catch (Exception e) {
            // e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    @Override
    public List<Paiement> CtrPaiement_GetAll() {
        return Dao_Instance.MdlPaiement_GetAll();
    }

    @Override
    public String CtrPaiement_Enregistrer(Paiement Paiement) {
        String message = null;
        message = Dao_Instance.MdlPaiement_Enregistrer(Paiement);
        return message;
    }

/*
    @Override
    public Paiement CtrPaiement_GetById(int id) {
        return Dao_Instance.MdlPaiement_GetByID(id);
    }
*/
    @Override
    public List<Paiement> CtrPaiement_GetByChamps(String champs, String valeur) {
        return Dao_Instance.MdlPaiement_GetByChamps(champs, valeur);
    }

    @Override
    public int CtrPaiement_Modifier(Paiement Paiement) {
        return Dao_Instance.MdlPaiement_Modifier(Paiement);
    }

    @Override
    public int CtrPaiement_Enlever(int id) {
        return Dao_Instance.MdlPaiement_Supprimer(id);
    }

    @Override
    public int CtrPaiement_EnregistrerParRequete(String strSql, String valeur) {
        return Dao_Instance.MdlPaiement_EnregistrerParRequete(strSql,valeur);
    }


    @Override
    public List<Paiement> CtrPaiement_GetParRequete(String strSql) {
        
        return Dao_Instance.MdlPaiement_GetParRequete(strSql);
    }

    

}
