package POS.dao_Session.modelSession;
import java.util.List;

public interface ISession {

    // Pour le CRUD - Create Read Update Delete
    
    // Create
    public String MdlSession_Enregistrer(Session session);
    public int MdlSession_EnregistrerParRequete(String strSql, String valeur);
    
    // Read
    public List<Session> MdlSession_GetAll();
    public List<Session> MdlSession_GetParRequete(String strSql);


    //public Session MdlSession_GetByID(int id);

    public List<Session> MdlSession_GetByChamps(String champs,String valeur);
    
    // Update
    public int MdlSession_Modifier(Session session);

    // Delete
    public int MdlSession_Supprimer(int id);

}
