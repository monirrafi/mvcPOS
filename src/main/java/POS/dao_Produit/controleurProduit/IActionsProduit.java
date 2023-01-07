package POS.dao_Produit.controleurProduit;

import java.util.List;

import POS.dao_Produit.modelProduit.Produit;

public interface IActionsProduit {
    // Create
    public String CtrProduit_Enregistrer(Produit produit);
    public int CtrProduit_EnregistrerParRequete(String strSql,String valeur);
    
    // // Read
     public List<Produit> CtrProduit_GetAll();
     public List<Produit> CtrProduit_GetParRequete(String strSql);


//     public Produit CtrProduit_GetById(int id);

    public List<Produit> CtrProduit_GetByChamps(String champs,String valeur);

    // // Update
    public int CtrProduit_Modifier(Produit produit);

    // // Delete
     public int CtrProduit_Enlever(int id); 
}
