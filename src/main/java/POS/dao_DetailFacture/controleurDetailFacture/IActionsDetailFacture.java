package POS.dao_DetailFacture.controleurDetailFacture;

import java.util.List;

import POS.dao_DetailFacture.modelDetailFacture.DetailFacture;

public interface IActionsDetailFacture {
    // Create
    public String CtrDetailFacture_Enregistrer(DetailFacture DetailFacture);
    public int CtrDetailFacture_EnregistrerParRequete(String strSql,String valeur);
    
    // // Read
     public List<DetailFacture> CtrDetailFacture_GetAll();
     public List<DetailFacture> CtrDetailFacture_GetParRequete(String strSql);


//     public DetailFacture CtrDetailFacture_GetById(int id);

    public List<DetailFacture> CtrDetailFacture_GetByChamps(String champs,String valeur);

    // // Update
    public int CtrDetailFacture_Modifier(DetailFacture DetailFacture);

    // // Delete
     public int CtrDetailFacture_Enlever(int id); 
}
