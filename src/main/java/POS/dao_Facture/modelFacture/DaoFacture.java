package POS.dao_Facture.modelFacture;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class DaoFacture implements IFacture {
    private static Connection conn = null;
    private static DaoFacture instanceDao = null;

    // MySQL
    //private static final String PILOTE = "com.mysql.jdbc.Driver";
    private static final String NOM_BD = "bdcaisse";
    private static final String URL_BD = "jdbc:mysql://localhost/" +NOM_BD;     
    private static final String USAGER = "root";
    private static final String PASS = "";

    private static final String SUPPRIMER = "DELETE FROM Facture WHERE ID_Facture=?";
    private static final String GET_ALL = "SELECT * FROM Facture ORDER BY ID_Facture";
    private static final String GET_BY_ID = "SELECT * FROM Facture WHERE ID_Facture=?";
    private static final String GET_BY_CHAMPS = "SELECT * FROM Facture WHERE ";
    private static final String ENREGISTRER = "INSERT INTO Facture VALUES(0,?,?)";
    private static final String MODIFIER = "UPDATE Facture SET ID_CLIENT=?,DATE_Facture=? WHERE ID_Facture=?";

    // Singleton de connexion à la BD
    // getConnexion() est devenu une zonne critique. 
    // Pour ne pas avoir deux processus légers (threads) qui
    // appellent au même temps getConnexion

    private DaoFacture(){};

    public static synchronized DaoFacture getFactureDao () {
        try {
                if (instanceDao == null) {
                    instanceDao = new DaoFacture();
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
    public String MdlFacture_Enregistrer(Facture Facture) {
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(ENREGISTRER, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, Facture.getIdClient());
            stmt.setString(2, Facture.getDateFacture());
           
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                Facture.setIdFacture(rs.getInt(1));
            }
            return "Facture est bien enregistré ";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Mdl_Fermer(stmt);
           // MdlLivre_Fermer(conn);
        }
    }

    @Override
    public List<Facture> MdlFacture_GetAll() {
            PreparedStatement stmt = null;
            List<Facture> listeFactures = new ArrayList<Facture>();
    
            try {
                stmt = conn.prepareStatement(GET_ALL);
                ResultSet rs = stmt.executeQuery();
    
                while (rs.next()) {
                    Facture Facture = new Facture();
                    Facture.setIdFacture(rs.getInt(1));
                    Facture.setIdClient(rs.getInt(2));
                    Facture.setDateFacture(rs.getString(3));
            
                    listeFactures.add(Facture);
                    }
            } catch (SQLException e) {
                // e.printStackTrace();
                throw new RuntimeException(e);
            } finally {
                Mdl_Fermer(stmt);
               // MdlFacture_Fermer(conn);
            }
    
            return listeFactures;
        }
    
/*
    @Override
    public Facture MdlFacture_GetByID(int id) {
        PreparedStatement stmt = null;

        try {

            stmt = conn.prepareStatement(GET_BY_ID);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Facture Facture = new Facture();
                Facture.setId(rs.getInt(1));
                Facture.setNom(rs.getString(2));
                Facture.setPrenom(rs.getString(3));
                Facture.setTelphone(rs.getString(4));
                Facture.setEmail(rs.getString(5));
                Facture.setUserName(rs.getString(6));
                Facture.setMotPasse(rs.getString(7));
                Facture.setNiveau(rs.getInt(8));

                return Facture;
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
    public List<Facture> MdlFacture_GetByChamps(String champs, String valeur) {
        PreparedStatement stmt = null;
        List<Facture> listeFactures = new ArrayList<Facture>();

        try {
            stmt = conn.prepareStatement(GET_BY_CHAMPS + champs + "=?");
            stmt.setString(1, valeur);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Facture Facture = new Facture();
                Facture.setIdFacture(rs.getInt(1));
                Facture.setIdClient(rs.getInt(2));
                Facture.setDateFacture(rs.getString(3));
                listeFactures.add(Facture);
            }
        } catch (SQLException e) {
            // e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            Mdl_Fermer(stmt);
           // MdlFacture_Fermer(conn);
        }

        return listeFactures;
}

    @Override
    public int MdlFacture_Modifier(Facture Facture) {
        PreparedStatement stmt = null;
       
        try {
            stmt = conn.prepareStatement(MODIFIER);
            stmt.setInt(1, Facture.getIdClient());
            stmt.setString(2, Facture.getDateFacture());
            stmt.setInt(3, Facture.getIdFacture());

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
    public int MdlFacture_Supprimer(int id) {
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
    public int MdlFacture_EnregistrerParRequete(String strSql,String valeur) {
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
    public List<Facture> MdlFacture_GetParRequete(String strSql) {
        PreparedStatement stmt = null;
        List<Facture> listeFactures = new ArrayList<Facture>();

        try {
            stmt = conn.prepareStatement(strSql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Facture Facture = new Facture();
                Facture.setIdFacture(rs.getInt(1));
                Facture.setIdClient(rs.getInt(2));
                Facture.setDateFacture(rs.getString(3));

                listeFactures.add(Facture);
            }
        } catch (SQLException e) {
            // e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            Mdl_Fermer(stmt);
           // MdlFacture_Fermer(conn);
        }

        return listeFactures;
}

    @Override
    public int MdlFacture_GetDernier() {
        PreparedStatement stmt = null;
        int dernier = 0;

        try {
            stmt = conn.prepareStatement("SELECT MAX(ID_FACTURE) FROM FACTURE");
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                dernier = rs.getInt(1);
            }
            return dernier;
        } catch (SQLException e) {
            // e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            Mdl_Fermer(stmt);
           // MdlFacture_Fermer(conn);
        }
}

}
