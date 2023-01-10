package POS.dao_Paiement.modelPaiement;
public class Paiement {
    private int idPaiement;
    private int idFacture;
    private int idSession;
    private String datePaiement;
    private String typePaiement;
    private double montantPaiement;
    

    
    public Paiement() {
    }


    public Paiement(int idPaiement, int idFacture, int idSession, String datePaiement, String typePaiement,
            double montantPaiement) {
        this.setIdPaiement(idPaiement);
        this.setIdFacture(idFacture);
        this.setIdSession(idSession);
        this.setDatePaiement(datePaiement);
        this.setTypePaiement(typePaiement);
        this.setMontantPaiement(montantPaiement);
    }

    public Paiement(int idFacture, int idSession, String datePaiement, String typePaiement,
            double montantPaiement) {
        this.setIdFacture(idFacture);
        this.setIdSession(idSession);
        this.setDatePaiement(datePaiement);
        this.setTypePaiement(typePaiement);
        this.setMontantPaiement(montantPaiement);
    }



    public int getIdPaiement() {
        return idPaiement;
    }



    public void setIdPaiement(int idPaiement) {
        this.idPaiement = idPaiement;
    }



    public int getIdFacture() {
        return idFacture;
    }



    public void setIdFacture(int idFacture) {
        this.idFacture = idFacture;
    }



    public int getIdSession() {
        return idSession;
    }



    public void setIdSession(int idSession) {
        this.idSession = idSession;
    }



    public String getDatePaiement() {
        return datePaiement;
    }



    public void setDatePaiement(String datePaiement) {
        this.datePaiement = datePaiement;
    }



    public String getTypePaiement() {
        return typePaiement;
    }



    public void setTypePaiement(String typePaiement) {
        this.typePaiement = typePaiement;
    }



    public double getMontantPaiement() {
        return montantPaiement;
    }



    public void setMontantPaiement(double montantPaiement) {
        this.montantPaiement = montantPaiement;
    }

    
    
}
