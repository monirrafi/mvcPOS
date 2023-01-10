package POS.dao_Session.controleurSession;

import java.util.List;

import POS.dao_Session.modelSession.Session;

public interface IActionsSession {
    // Create
    public String CtrSession_Enregistrer(Session session);
    public int CtrSession_EnregistrerParRequete(String strSql,String valeur);
    
    // // Read
     public List<Session> CtrSession_GetAll();
     public List<Session> CtrSession_GetParRequete(String strSql);


//     public Session CtrSession_GetById(int id);

    public List<Session> CtrSession_GetByChamps(String champs,String valeur);

    // // Update
    public int CtrSession_Modifier(Session session);

    // // Delete
     public int CtrSession_Enlever(int id); 
}
