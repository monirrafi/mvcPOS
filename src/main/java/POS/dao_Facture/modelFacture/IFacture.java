package POS.dao_Facture.modelFacture;
import java.util.List;

public interface IFacture {

    // Pour le CRUD - Create Read Update Delete
    
    // Create
    public String MdlFacture_Enregistrer(Facture Facture);
    public int MdlFacture_EnregistrerParRequete(String strSql, String valeur);
    
    // Read
    public List<Facture> MdlFacture_GetAll();
    public List<Facture> MdlFacture_GetParRequete(String strSql);
    public int MdlFacture_GetDernier();


    //public Facture MdlFacture_GetByID(int id);

    public List<Facture> MdlFacture_GetByChamps(String champs,String valeur);
    
    // Update
    public int MdlFacture_Modifier(Facture Facture);

    // Delete
    public int MdlFacture_Supprimer(int id);

}
