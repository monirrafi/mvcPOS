package POS.dao_Caissier.controleurCaissier;

import java.util.List;

import POS.dao_Caissier.modelCaissier.Caissier;
import POS.dao_Caissier.modelCaissier.DaoCaissier;


public class ControleurCaissier implements IActionsCaissier {

    private static ControleurCaissier CtrCaissier_Instance = null;
    private static DaoCaissier Dao_Instance = null;
    // Singleton du contrôleur
    // getControleurFilm() est devenu une zonne critique.
    // Pour ne pas avoir deux processus légers (threads) qui
    // appellent au même temps getConnexion
    private ControleurCaissier(){}

    public static synchronized ControleurCaissier getControleurCaissier() {
        try {
            if (CtrCaissier_Instance == null) {
                CtrCaissier_Instance = new ControleurCaissier();
                Dao_Instance = DaoCaissier.getCaissierDao();
            }
            return CtrCaissier_Instance;
        } catch (Exception e) {
            // e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    @Override
    public List<Caissier> CtrCaissier_GetAll() {
        return Dao_Instance.MdlCaissier_GetAll();
    }

    @Override
    public String CtrCaissier_Enregistrer(Caissier caissier) {
        String message = null;
        message = Dao_Instance.MdlCaissier_Enregistrer(caissier);
        return message;
    }

/*
    @Override
    public Caissier CtrCaissier_GetById(int id) {
        return Dao_Instance.MdlCaissier_GetByID(id);
    }
*/
    @Override
    public List<Caissier> CtrCaissier_GetByChamps(String champs, String valeur) {
        return Dao_Instance.MdlCaissier_GetByChamps(champs, valeur);
    }

    @Override
    public int CtrCaissier_Modifier(Caissier Caissier) {
        return Dao_Instance.MdlCaissier_Modifier(Caissier);
    }

    @Override
    public int CtrCaissier_Enlever(int id) {
        return Dao_Instance.MdlCaissier_Supprimer(id);
    }

    @Override
    public int CtrCaissier_EnregistrerParRequete(String strSql, String valeur) {
        return Dao_Instance.MdlCaissier_EnregistrerParRequete(strSql,valeur);
    }


    @Override
    public List<Caissier> CtrCaissier_GetParRequete(String strSql) {
        
        return Dao_Instance.MdlCaissier_GetParRequete(strSql);
    }

    

}
