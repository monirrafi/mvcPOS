package POS.dao_Facture.controleurFacture;

import java.util.List;

import POS.dao_Facture.modelFacture.Facture;

public interface IActionsFacture {
    // Create
    public String CtrFacture_Enregistrer(Facture facture);
    public int CtrFacture_EnregistrerParRequete(String strSql,String valeur);
    
    // // Read
     public List<Facture> CtrFacture_GetAll();
     public List<Facture> CtrFacture_GetParRequete(String strSql);
     public int CtrFacture_GetDernier();



//     public Facture CtrFacture_GetById(int id);

    public List<Facture> CtrFacture_GetByChamps(String champs,String valeur);

    // // Update
    public int CtrFacture_Modifier(Facture facture);

    // // Delete
     public int CtrFacture_Enlever(int id); 
}
