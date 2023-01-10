package POS.dao_Session.modelSession;
public class Session {
    private int idSession;
    private int caissier;
    private String dateSession;
    private String heureOuverture;
    private String heureFermeture;

    
    public Session() {
    }

    

    public Session(int idSession, int caissier, String dateSession, String heureOuverture, String heureFermeture) {
        this.setIdSession(idSession);
        this.setCaissier(caissier);
        this.setDateSession(dateSession);
        this.setHeureFermeture(heureFermeture);
        this.setHeureOuverture(heureOuverture);
    }



    public Session(int caissier, String dateSession, String heureOuverture, String heureFermeture) {
        this.setCaissier(caissier);
        this.setDateSession(dateSession);
        this.setHeureFermeture(heureFermeture);
        this.setHeureOuverture(heureOuverture);
    }


    public int getIdSession() {
        return idSession;
    }
    public void setIdSession(int idSession) {
        this.idSession = idSession;
    }
    public String getDateSession() {
        return dateSession;
    }
    public void setDateSession(String dateSession) {
        this.dateSession = dateSession;
    }
    public String getHeureOuverture() {
        return heureOuverture;
    }
    public void setHeureOuverture(String heureOuverture) {
        this.heureOuverture = heureOuverture;
    }
    public String getHeureFermeture() {
        return heureFermeture;
    }
    public void setHeureFermeture(String heureFermeture) {
        this.heureFermeture = heureFermeture;
    }
    public int getCaissier() {
        return caissier;
    }
    public void setCaissier(int caissier) {
        this.caissier = caissier;
    }
    
    
}
