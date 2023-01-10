package POS.dao_Client.controleurClient;

import java.util.List;

import POS.dao_Client.modelClient.DaoClient;
import POS.dao_Client.modelClient.Client;


public class ControleurClient implements IActionsClient {

    private static ControleurClient CtrClient_Instance = null;
    private static DaoClient Dao_Instance = null;
    // Singleton du contrôleur
    // getControleurFilm() est devenu une zonne critique.
    // Pour ne pas avoir deux processus légers (threads) qui
    // appellent au même temps getConnexion
    private ControleurClient(){}

    public static synchronized ControleurClient getControleurClient() {
        try {
            if (CtrClient_Instance == null) {
                CtrClient_Instance = new ControleurClient();
                Dao_Instance = DaoClient.getClientDao();
            }
            return CtrClient_Instance;
        } catch (Exception e) {
            // e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    @Override
    public List<Client> CtrClient_GetAll() {
        return Dao_Instance.MdlClient_GetAll();
    }

    @Override
    public String CtrClient_Enregistrer(Client Client) {
        String message = null;
        message = Dao_Instance.MdlClient_Enregistrer(Client);
        return message;
    }

/*
    @Override
    public Client CtrClient_GetById(int id) {
        return Dao_Instance.MdlClient_GetByID(id);
    }
*/
    @Override
    public List<Client> CtrClient_GetByChamps(String champs, String valeur) {
        return Dao_Instance.MdlClient_GetByChamps(champs, valeur);
    }

    @Override
    public int CtrClient_Modifier(Client Client) {
        return Dao_Instance.MdlClient_Modifier(Client);
    }

    @Override
    public int CtrClient_Enlever(int id) {
        return Dao_Instance.MdlClient_Supprimer(id);
    }

    @Override
    public int CtrClient_EnregistrerParRequete(String strSql, String valeur) {
        return Dao_Instance.MdlClient_EnregistrerParRequete(strSql,valeur);
    }


    @Override
    public List<Client> CtrClient_GetParRequete(String strSql) {
        
        return Dao_Instance.MdlClient_GetParRequete(strSql);
    }

    

}
