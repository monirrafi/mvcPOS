package POS.dao_Facture.controleurFacture;

import java.util.List;

import POS.dao_Facture.modelFacture.DaoFacture;
import POS.dao_Facture.modelFacture.Facture;


public class ControleurFacture implements IActionsFacture {

    private static ControleurFacture CtrFacture_Instance = null;
    private static DaoFacture Dao_Instance = null;
    // Singleton du contrôleur
    // getControleurFilm() est devenu une zonne critique.
    // Pour ne pas avoir deux processus légers (threads) qui
    // appellent au même temps getConnexion
    private ControleurFacture(){}

    public static synchronized ControleurFacture getControleurFacture() {
        try {
            if (CtrFacture_Instance == null) {
                CtrFacture_Instance = new ControleurFacture();
                Dao_Instance = DaoFacture.getFactureDao();
            }
            return CtrFacture_Instance;
        } catch (Exception e) {
            // e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    @Override
    public List<Facture> CtrFacture_GetAll() {
        return Dao_Instance.MdlFacture_GetAll();
    }

    @Override
    public String CtrFacture_Enregistrer(Facture Facture) {
        String message = null;
        message = Dao_Instance.MdlFacture_Enregistrer(Facture);
        return message;
    }

/*
    @Override
    public Facture CtrFacture_GetById(int id) {
        return Dao_Instance.MdlFacture_GetByID(id);
    }
*/
    @Override
    public List<Facture> CtrFacture_GetByChamps(String champs, String valeur) {
        return Dao_Instance.MdlFacture_GetByChamps(champs, valeur);
    }

    @Override
    public int CtrFacture_Modifier(Facture Facture) {
        return Dao_Instance.MdlFacture_Modifier(Facture);
    }

    @Override
    public int CtrFacture_Enlever(int id) {
        return Dao_Instance.MdlFacture_Supprimer(id);
    }

    @Override
    public int CtrFacture_EnregistrerParRequete(String strSql, String valeur) {
        return Dao_Instance.MdlFacture_EnregistrerParRequete(strSql,valeur);
    }


    @Override
    public List<Facture> CtrFacture_GetParRequete(String strSql) {
        
        return Dao_Instance.MdlFacture_GetParRequete(strSql);
    }

    @Override
    public int CtrFacture_GetDernier() {
        return Dao_Instance.MdlFacture_GetDernier();
    }

    

}
