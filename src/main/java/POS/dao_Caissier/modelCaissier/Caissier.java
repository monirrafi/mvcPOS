package POS.dao_Caissier.modelCaissier;
public class Caissier {
    private int idCaissier;
    private String  nom ="";
    private String userName;
    private String motPasse;
    
    
    public Caissier() {
    }

    
    public Caissier(int idCaissier, String nom, String userName,  String motPasse) {
        this.setIdCaissier(idCaissier);
        this.setNom(nom);
        this.setUserName(userName);
        this.setMotPasse(motPasse);
    }

    public Caissier(String nom, String userName,  String motPasse) {
        this.setNom(nom);
        this.setUserName(userName);
        this.setMotPasse(motPasse);
    }

    public int getIdCaissier() {
        return idCaissier;
    }


    public void setIdCaissier(int idCaissier) {
        this.idCaissier = idCaissier;
    }


    public String getNom() {
        return nom;
    }


    public void setNom(String nom) {
        this.nom = nom;
    }


    public String getUserName() {
        return userName;
    }


    public void setUserName(String userName) {
        this.userName = userName;
    }


    public String getMotPasse() {
        return motPasse;
    }


    public void setMotPasse(String motPasse) {
        this.motPasse = motPasse;
    }

        
}
