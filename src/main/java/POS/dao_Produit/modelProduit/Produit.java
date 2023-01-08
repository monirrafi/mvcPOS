package POS.dao_Produit.modelProduit;
public class Produit {
    private int idProduit;
    private String  nom ="";
    private double prixVente;
    private String codeProduit;
    private boolean TPS=false;
    private boolean TVQ=false;



    
    public Produit() {
    }
    



    public Produit(int idProduit, String nom, double prixVente, String codeProduit, boolean tPS, boolean tVQ) {
        this.setIdProduit(idProduit);
        this.setNom(nom);
        this.setPrixVente(prixVente);
        this.setCodeProduit(codeProduit);
        this.setTPS(tPS);
        this.setTVQ(tVQ);
    }




    @Override
    public String toString() {
        return "Produit [idProduit=" + idProduit + ", nom=" + nom + ", prixVente=" + prixVente + ", codeProduit="
                + codeProduit + ", TPS=" + TPS + ", TVQ=" + TVQ + "]";
    }




    public Produit(String nom, double prixVente, String codeProduit, boolean tPS, boolean tVQ) {
        this.setNom(nom);
        this.setPrixVente(prixVente);
        this.setCodeProduit(codeProduit);
        this.setTPS(tPS);
        this.setTVQ(tVQ);
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

    public boolean getTPS() {
        return TPS;
    }

    public void setTPS(boolean tPS) {
        this.TPS = tPS;
    }

    public boolean getTVQ() {
        return TVQ;
    }

    public void setTVQ(boolean tVQ) {
        this.TVQ = tVQ;
    }
    
    
        
}
