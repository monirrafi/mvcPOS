package POS.dao_Session.controleurSession;

import java.util.List;

import POS.dao_Session.modelSession.Session;
import POS.dao_Session.modelSession.DaoSession;


public class ControleurSession implements IActionsSession {

    private static ControleurSession CtrSession_Instance = null;
    private static DaoSession Dao_Instance = null;
    // Singleton du contrôleur
    // getControleurFilm() est devenu une zonne critique.
    // Pour ne pas avoir deux processus légers (threads) qui
    // appellent au même temps getConnexion
    private ControleurSession(){}

    public static synchronized ControleurSession getControleurSession() {
        try {
            if (CtrSession_Instance == null) {
                CtrSession_Instance = new ControleurSession();
                Dao_Instance = DaoSession.getSessionDao();
            }
            return CtrSession_Instance;
        } catch (Exception e) {
            // e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    @Override
    public List<Session> CtrSession_GetAll() {
        return Dao_Instance.MdlSession_GetAll();
    }

    @Override
    public String CtrSession_Enregistrer(Session Session) {
        String message = null;
        message = Dao_Instance.MdlSession_Enregistrer(Session);
        return message;
    }

/*
    @Override
    public Session CtrSession_GetById(int id) {
        return Dao_Instance.MdlSession_GetByID(id);
    }
*/
    @Override
    public List<Session> CtrSession_GetByChamps(String champs, String valeur) {
        return Dao_Instance.MdlSession_GetByChamps(champs, valeur);
    }

    @Override
    public int CtrSession_Modifier(Session Session) {
        return Dao_Instance.MdlSession_Modifier(Session);
    }

    @Override
    public int CtrSession_Enlever(int id) {
        return Dao_Instance.MdlSession_Supprimer(id);
    }

    @Override
    public int CtrSession_EnregistrerParRequete(String strSql, String valeur) {
        return Dao_Instance.MdlSession_EnregistrerParRequete(strSql,valeur);
    }


    @Override
    public List<Session> CtrSession_GetParRequete(String strSql) {
        
        return Dao_Instance.MdlSession_GetParRequete(strSql);
    }

    

}
