package POS.dao_Paiement.controleurPaiement;

import java.util.List;

import POS.dao_Paiement.modelPaiement.Paiement;

public interface IActionsPaiement {
    // Create
    public String CtrPaiement_Enregistrer(Paiement paiement);
    public int CtrPaiement_EnregistrerParRequete(String strSql,String valeur);
    
    // // Read
     public List<Paiement> CtrPaiement_GetAll();
     public List<Paiement> CtrPaiement_GetParRequete(String strSql);


//     public Paiement CtrPaiement_GetById(int id);

    public List<Paiement> CtrPaiement_GetByChamps(String champs,String valeur);

    // // Update
    public int CtrPaiement_Modifier(Paiement paiement);

    // // Delete
     public int CtrPaiement_Enlever(int id); 
}
