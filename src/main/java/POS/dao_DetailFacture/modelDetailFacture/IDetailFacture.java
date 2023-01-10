package POS.dao_DetailFacture.modelDetailFacture;
import java.util.List;

public interface IDetailFacture {

    // Pour le CRUD - Create Read Update Delete
    
    // Create
    public String MdlDetailFacture_Enregistrer(DetailFacture detailFacture);
    public int MdlDetailFacture_EnregistrerParRequete(String strSql, String valeur);
    
    // Read
    public List<DetailFacture> MdlDetailFacture_GetAll();
    public List<DetailFacture> MdlDetailFacture_GetParRequete(String strSql);


    //public DetailFacture MdlDetailFacture_GetByID(int id);

    public List<DetailFacture> MdlDetailFacture_GetByChamps(String champs,String valeur);
    
    // Update
    public int MdlDetailFacture_Modifier(DetailFacture detailFacture);

    // Delete
    public int MdlDetailFacture_Supprimer(int id);

}
