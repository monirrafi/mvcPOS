package POS.dao_Produit.modelProduit;
public class Produit {
    private int idProduit;
    private String  nom ="";
    private double prixVente;
    private String codeProduit;



    
    public Produit() {
    }
    
    public Produit(int idProduit, String nom, double prixVente, String codeProduit) {
        this.setIdProduit(idProduit);
        this.setNom(nom);
        this.setPrixVente(prixVente);
        this.setCodeProduit(codeProduit);
    }

    public Produit(String nom, double prixVente, String codeProduit) {
        this.setNom(nom);
        this.setPrixVente(prixVente);
        this.setCodeProduit(codeProduit);
    }
    public int getIdProduit() {
        return idProduit;
    }
    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public double getPrixVente() {
        return prixVente;
    }
    public void setPrixVente(double prixVente) {
        this.prixVente = prixVente;
    }
    public String getCodeProduit() {
        return codeProduit;
    }
    public void setCodeProduit(String codeProduit) {
        this.codeProduit = codeProduit;
    }
    
    
        
}
