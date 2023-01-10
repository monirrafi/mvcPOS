package POS.dao_Client.modelClient;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class DaoClient implements IClient {
    private static Connection conn = null;
    private static DaoClient instanceDao = null;

    // MySQL
    //private static final String PILOTE = "com.mysql.jdbc.Driver";
    private static final String NOM_BD = "bdcaisse";
    private static final String URL_BD = "jdbc:mysql://localhost/" +NOM_BD;     
    private static final String USAGER = "root";
    private static final String PASS = "";

    private static final String SUPPRIMER = "DELETE FROM Client WHERE ID_Client=?";
    private static final String GET_ALL = "SELECT * FROM Client ORDER BY ID_Client";
    private static final String GET_BY_ID = "SELECT * FROM Client WHERE ID_Client=?";
    private static final String GET_BY_CHAMPS = "SELECT * FROM Client WHERE ";
    private static final String ENREGISTRER = "INSERT INTO Client VALUES(0,?,?)";
    private static final String MODIFIER = "UPDATE Client SET NOM_CLIENT=?,EMAIL_Client=? WHERE ID_Client=?";

    // Singleton de connexion à la BD
    // getConnexion() est devenu une zonne critique. 
    // Pour ne pas avoir deux processus légers (threads) qui
    // appellent au même temps getConnexion

    private DaoClient(){};

    public static synchronized DaoClient getClientDao () {
        try {
                if (instanceDao == null) {
                    instanceDao = new DaoClient();
                    conn = DriverManager.getConnection(URL_BD, USAGER, PASS);
                }
                return instanceDao;
            
        } 
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
   
    private static void Mdl_Fermer(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                // e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }

    private static void Mdl_Fermer(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                // e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }
    public Connection getConn() {
        return conn;
    }

    @Override
    public String MdlClient_Enregistrer(Client Client) {
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(ENREGISTRER, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, Client.getNomClient());
            stmt.setString(2, Client.getEmailClient());
           
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                Client.setIdClient(rs.getInt(1));
            }
            return "Client est bien enregistré ";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Mdl_Fermer(stmt);
           // MdlLivre_Fermer(conn);
        }
    }

    @Override
    public List<Client> MdlClient_GetAll() {
            PreparedStatement stmt = null;
            List<Client> listeClients = new ArrayList<Client>();
    
            try {
                stmt = conn.prepareStatement(GET_ALL);
                ResultSet rs = stmt.executeQuery();
    
                while (rs.next()) {
                    Client Client = new Client();
                    Client.setIdClient(rs.getInt(1));
                    Client.setNomClient(rs.getString(2));
                    Client.setEmailClient(rs.getString(3));
            
                    listeClients.add(Client);
                    }
            } catch (SQLException e) {
                // e.printStackTrace();
                throw new RuntimeException(e);
            } finally {
                Mdl_Fermer(stmt);
               // MdlClient_Fermer(conn);
            }
    
            return listeClients;
        }
    
/*
    @Override
    public Client MdlClient_GetByID(int id) {
        PreparedStatement stmt = null;

        try {

            stmt = conn.prepareStatement(GET_BY_ID);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Client Client = new Client();
                Client.setId(rs.getInt(1));
                Client.setNom(rs.getString(2));
                Client.setPrenom(rs.getString(3));
                Client.setTelphone(rs.getString(4));
                Client.setEmail(rs.getString(5));
                Client.setUserName(rs.getString(6));
                Client.setMotPasse(rs.getString(7));
                Client.setNiveau(rs.getInt(8));

                return Client;
            } else {
                return null;
            }
        } catch (SQLException e) {
            // e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            Mdl_Fermer(stmt);
//            MdlLivre_Fermer(conn);
        }
    }
*/
    @Override
    public List<Client> MdlClient_GetByChamps(String champs, String valeur) {
        PreparedStatement stmt = null;
        List<Client> listeClients = new ArrayList<Client>();

        try {
            stmt = conn.prepareStatement(GET_BY_CHAMPS + champs + "=?");
            stmt.setString(1, valeur);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Client Client = new Client();
                Client.setIdClient(rs.getInt(1));
                Client.setNomClient(rs.getString(2));
                Client.setEmailClient(rs.getString(3));
            listeClients.add(Client);
            }
        } catch (SQLException e) {
            // e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            Mdl_Fermer(stmt);
           // MdlClient_Fermer(conn);
        }

        return listeClients;
}

    @Override
    public int MdlClient_Modifier(Client Client) {
        PreparedStatement stmt = null;
       
        try {
            stmt = conn.prepareStatement(MODIFIER);
            stmt.setString(1, Client.getNomClient());
            stmt.setString(2, Client.getEmailClient());
            stmt.setInt(3, Client.getIdClient());

            return stmt.executeUpdate();
        } catch (SQLException e) {
            // e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            Mdl_Fermer(stmt);
            //MdlLivre_Fermer(conn);
        }
    }

    @Override
    public int MdlClient_Supprimer(int id) {
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(SUPPRIMER);
            stmt.setInt(1, id);

            return stmt.executeUpdate();
        } catch (SQLException e) {
            // e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            Mdl_Fermer(stmt);
           // MdlLivre_Fermer(conn);
        }
    }

    @Override
    public int MdlClient_EnregistrerParRequete(String strSql,String valeur) {
        PreparedStatement stmt = null;
        int cle=0;
        try {
            stmt = conn.prepareStatement(strSql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, valeur);
           
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                cle = rs.getInt(1);
            }
            return cle;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Mdl_Fermer(stmt);
           // MdlLivre_Fermer(conn);
        }
    }

    @Override
    public List<Client> MdlClient_GetParRequete(String strSql) {
        PreparedStatement stmt = null;
        List<Client> listeClients = new ArrayList<Client>();

        try {
            stmt = conn.prepareStatement(strSql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Client Client = new Client();
                Client.setIdClient(rs.getInt(1));
                Client.setNomClient(rs.getString(2));
                Client.setEmailClient(rs.getString(3));

                listeClients.add(Client);
            }
        } catch (SQLException e) {
            // e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            Mdl_Fermer(stmt);
           // MdlClient_Fermer(conn);
        }

        return listeClients;
}

}
