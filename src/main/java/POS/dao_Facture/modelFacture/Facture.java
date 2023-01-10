package POS.dao_Facture.modelFacture;
public class Facture {
    private int idFacture;
    private int idClient;
    private String dateFacture;


    
    public Facture() {
    }
    
    public Facture(int idFacture, int idClient, String dateFacture) {
        this.setIdFacture(idFacture);
        this.setIdClient(idClient);
        this.setDateFacture(dateFacture);
    }

    public Facture(int idClient, String dateFacture) {
        this.setIdClient(idClient);
        this.setDateFacture(dateFacture);
    }
    public int getIdFacture() {
        return idFacture;
    }
    public void setIdFacture(int idFacture) {
        this.idFacture = idFacture;
    }
    public int getIdClient() {
        return idClient;
    }
    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }
    public String getDateFacture() {
        return dateFacture;
    }
    public void setDateFacture(String dateFacture) {
        this.dateFacture = dateFacture;
    }

    
    
}
