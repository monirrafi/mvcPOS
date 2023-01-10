package POS.dao_DetailFacture.controleurDetailFacture;

import java.util.List;

import POS.dao_DetailFacture.modelDetailFacture.DetailFacture;
import POS.dao_DetailFacture.modelDetailFacture.DaoDetailFacture;


public class ControleurDetailFacture implements IActionsDetailFacture {

    private static ControleurDetailFacture CtrDetailFacture_Instance = null;
    private static DaoDetailFacture Dao_Instance = null;
    // Singleton du contrôleur
    // getControleurFilm() est devenu une zonne critique.
    // Pour ne pas avoir deux processus légers (threads) qui
    // appellent au même temps getConnexion
    private ControleurDetailFacture(){}

    public static synchronized ControleurDetailFacture getControleurDetailFacture() {
        try {
            if (CtrDetailFacture_Instance == null) {
                CtrDetailFacture_Instance = new ControleurDetailFacture();
                Dao_Instance = DaoDetailFacture.getDetailFactureDao();
            }
            return CtrDetailFacture_Instance;
        } catch (Exception e) {
            // e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    @Override
    public List<DetailFacture> CtrDetailFacture_GetAll() {
        return Dao_Instance.MdlDetailFacture_GetAll();
    }

    @Override
    public String CtrDetailFacture_Enregistrer(DetailFacture DetailFacture) {
        String message = null;
        message = Dao_Instance.MdlDetailFacture_Enregistrer(DetailFacture);
        return message;
    }

/*
    @Override
    public DetailFacture CtrDetailFacture_GetById(int id) {
        return Dao_Instance.MdlDetailFacture_GetByID(id);
    }
*/
    @Override
    public List<DetailFacture> CtrDetailFacture_GetByChamps(String champs, String valeur) {
        return Dao_Instance.MdlDetailFacture_GetByChamps(champs, valeur);
    }

    @Override
    public int CtrDetailFacture_Modifier(DetailFacture DetailFacture) {
        return Dao_Instance.MdlDetailFacture_Modifier(DetailFacture);
    }

    @Override
    public int CtrDetailFacture_Enlever(int id) {
        return Dao_Instance.MdlDetailFacture_Supprimer(id);
    }

    @Override
    public int CtrDetailFacture_EnregistrerParRequete(String strSql, String valeur) {
        return Dao_Instance.MdlDetailFacture_EnregistrerParRequete(strSql,valeur);
    }


    @Override
    public List<DetailFacture> CtrDetailFacture_GetParRequete(String strSql) {
        
        return Dao_Instance.MdlDetailFacture_GetParRequete(strSql);
    }

    

}
