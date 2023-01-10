package POS.dao_Client.modelClient;
import java.util.List;

public interface IClient {

    // Pour le CRUD - Create Read Update Delete
    
    // Create
    public String MdlClient_Enregistrer(Client client);
    public int MdlClient_EnregistrerParRequete(String strSql, String valeur);
    
    // Read
    public List<Client> MdlClient_GetAll();
    public List<Client> MdlClient_GetParRequete(String strSql);


    //public Client MdlClient_GetByID(int id);

    public List<Client> MdlClient_GetByChamps(String champs,String valeur);
    
    // Update
    public int MdlClient_Modifier(Client client);

    // Delete
    public int MdlClient_Supprimer(int id);

}
