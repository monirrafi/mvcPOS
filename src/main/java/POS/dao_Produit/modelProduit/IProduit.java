package POS.dao_Produit.modelProduit;
import java.util.List;

public interface IProduit {

    // Pour le CRUD - Create Read Update Delete
    
    // Create
    public String MdlProduit_Enregistrer(Produit produit);
    public int MdlProduit_EnregistrerParRequete(String strSql, String valeur);
    
    // Read
    public List<Produit> MdlProduit_GetAll();
    public List<Produit> MdlProduit_GetParRequete(String strSql);


    //public Produit MdlProduit_GetByID(int id);

    public List<Produit> MdlProduit_GetByChamps(String champs,String valeur);
    
    // Update
    public int MdlProduit_Modifier(Produit produit);

    // Delete
    public int MdlProduit_Supprimer(int id);

}
