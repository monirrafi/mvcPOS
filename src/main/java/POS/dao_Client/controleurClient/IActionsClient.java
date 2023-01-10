package POS.dao_Client.controleurClient;

import java.util.List;

import POS.dao_Client.modelClient.Client;

public interface IActionsClient {
    // Create
    public String CtrClient_Enregistrer(Client Client);
    public int CtrClient_EnregistrerParRequete(String strSql,String valeur);
    
    // // Read
     public List<Client> CtrClient_GetAll();
     public List<Client> CtrClient_GetParRequete(String strSql);


//     public Client CtrClient_GetById(int id);

    public List<Client> CtrClient_GetByChamps(String champs,String valeur);

    // // Update
    public int CtrClient_Modifier(Client Client);

    // // Delete
     public int CtrClient_Enlever(int id); 
}
