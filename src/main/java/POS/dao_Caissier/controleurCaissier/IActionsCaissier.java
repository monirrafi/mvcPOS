package POS.dao_Caissier.controleurCaissier;

import java.util.List;

import POS.dao_Caissier.modelCaissier.Caissier;

public interface IActionsCaissier {
    // Create
    public String CtrCaissier_Enregistrer(Caissier caissier);
    public int CtrCaissier_EnregistrerParRequete(String strSql,String valeur);
    
    // // Read
     public List<Caissier> CtrCaissier_GetAll();
     public List<Caissier> CtrCaissier_GetParRequete(String strSql);


//     public Caissier CtrCaissier_GetById(int id);

    public List<Caissier> CtrCaissier_GetByChamps(String champs,String valeur);

    // // Update
    public int CtrCaissier_Modifier(Caissier caissier);

    // // Delete
     public int CtrCaissier_Enlever(int id); 
}
