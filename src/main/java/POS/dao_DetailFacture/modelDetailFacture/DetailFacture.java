package POS.dao_DetailFacture.modelDetailFacture;
public class DetailFacture {
    private int idDetailFacture;
    private int idFacture;
    private int idProduit;
    private double qty;

    
    public DetailFacture() {
    }
    
    public DetailFacture(int idDetailFacture, int idFacture, int idProduit, double qty) {
        this.setIdDetailFacture(idDetailFacture);
        this.setIdFacture(idFacture);
        this.setIdProduit(idProduit);
        this.setQty(qty);
    }

    public DetailFacture(int idFacture, int idProduit, double qty) {
        this.setIdFacture(idFacture);
        this.setIdProduit(idProduit);
        this.setQty(qty);
    }

    public int getIdDetailFacture() {
        return idDetailFacture;
    }
    public void setIdDetailFacture(int idDetailFacture) {
        this.idDetailFacture = idDetailFacture;
    }
    public int getIdFacture() {
        return idFacture;
    }
    public void setIdFacture(int idFacture) {
        this.idFacture = idFacture;
    }
    public int getIdProduit() {
        return idProduit;
    }
    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }
    public double getQty() {
        return qty;
    }
    public void setQty(double qty) {
        this.qty = qty;
    }
    
        
}
