package POS.dao_Caissier.modelCaissier;
import java.util.List;

public interface ICaissier {

    // Pour le CRUD - Create Read Update Delete
    
    // Create
    public String MdlCaissier_Enregistrer(Caissier Caissier);
    public int MdlCaissier_EnregistrerParRequete(String strSql, String valeur);
    
    // Read
    public List<Caissier> MdlCaissier_GetAll();
    public List<Caissier> MdlCaissier_GetParRequete(String strSql);


    //public Caissier MdlCaissier_GetByID(int id);

    public List<Caissier> MdlCaissier_GetByChamps(String champs,String valeur);
    
    // Update
    public int MdlCaissier_Modifier(Caissier Caissier);

    // Delete
    public int MdlCaissier_Supprimer(int id);

}
