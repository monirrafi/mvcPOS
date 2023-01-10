package POS.dao_Client.modelClient;
public class Client {
    private int idClient;
    private String nomClient;
    private String emailClient;


    
    public Client() {
    }

    
    public Client(int idClient, String nomClient, String emailClient) {
        this.setIdClient(idClient);
        this.setNomClient(nomClient);
        this.setEmailClient(emailClient);
    }

    public Client(String nomClient, String emailClient) {
        this.setIdClient(idClient);
        this.setNomClient(nomClient);
        this.setEmailClient(emailClient);
    }

    public int getIdClient() {
        return idClient;
    }
    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }
    public String getNomClient() {
        return nomClient;
    }
    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }
    public String getEmailClient() {
        return emailClient;
    }
    public void setEmailClient(String emailClient) {
        this.emailClient = emailClient;
    }

    

    
}
