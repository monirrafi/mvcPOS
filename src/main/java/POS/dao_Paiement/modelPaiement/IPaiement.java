package POS.dao_Paiement.modelPaiement;
import java.util.List;

public interface IPaiement {

    // Pour le CRUD - Create Read Update Delete
    
    // Create
    public String MdlPaiement_Enregistrer(Paiement paiement);
    public int MdlPaiement_EnregistrerParRequete(String strSql, String valeur);
    
    // Read
    public List<Paiement> MdlPaiement_GetAll();
    public List<Paiement> MdlPaiement_GetParRequete(String strSql);


    //public Paiement MdlPaiement_GetByID(int id);

    public List<Paiement> MdlPaiement_GetByChamps(String champs,String valeur);
    
    // Update
    public int MdlPaiement_Modifier(Paiement paiement);

    // Delete
    public int MdlPaiement_Supprimer(int id);

}
