package POS.dao_Caissier.modelCaissier;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class DaoCaissier implements ICaissier {
    private static Connection conn = null;
    private static DaoCaissier instanceDao = null;

    // MySQL
    //private static final String PILOTE = "com.mysql.jdbc.Driver";
    private static final String NOM_BD = "bdcaisse";
    private static final String URL_BD = "jdbc:mysql://localhost/" +NOM_BD;     
    private static final String USAGER = "root";
    private static final String PASS = "";

    private static final String SUPPRIMER = "DELETE FROM Caissier WHERE ID_Caissier=?";
    private static final String GET_ALL = "SELECT * FROM Caissier ORDER BY ID_Caissier";
    private static final String GET_BY_ID = "SELECT * FROM Caissier WHERE ID_Caissier=?";
    private static final String GET_BY_CHAMPS = "SELECT * FROM Caissier WHERE ";
    private static final String ENREGISTRER = "INSERT INTO Caissier VALUES(0,?,?,?)";
    private static final String MODIFIER = "UPDATE Caissier SET NOM_CAISSIER=?,USERNAME=?,MOTDEPASSE=? WHERE ID_Caissier=?";

    // Singleton de connexion à la BD
    // getConnexion() est devenu une zonne critique. 
    // Pour ne pas avoir deux processus légers (threads) qui
    // appellent au même temps getConnexion

    private DaoCaissier(){};

    public static synchronized DaoCaissier getCaissierDao () {
        try {
                if (instanceDao == null) {
                    instanceDao = new DaoCaissier();
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
    public String MdlCaissier_Enregistrer(Caissier Caissier) {
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(ENREGISTRER, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, Caissier.getNom());
            stmt.setString(2, Caissier.getUserName());
            stmt.setString(3, Caissier.getMotPasse());
           
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                Caissier.setIdCaissier(rs.getInt(1));
            }
            return "Caissier est bien enregistré ";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Mdl_Fermer(stmt);
           // MdlLivre_Fermer(conn);
        }
    }

    @Override
    public List<Caissier> MdlCaissier_GetAll() {
            PreparedStatement stmt = null;
            List<Caissier> listeCaissiers = new ArrayList<Caissier>();
    
            try {
                stmt = conn.prepareStatement(GET_ALL);
                ResultSet rs = stmt.executeQuery();
    
                while (rs.next()) {
                    Caissier Caissier = new Caissier();
                    Caissier.setIdCaissier(rs.getInt(1));
                    Caissier.setNom(rs.getString(2));
                    Caissier.setUserName(rs.getString(3));
                    Caissier.setMotPasse(rs.getString(4));

                    listeCaissiers.add(Caissier);
                }
            } catch (SQLException e) {
                // e.printStackTrace();
                throw new RuntimeException(e);
            } finally {
                Mdl_Fermer(stmt);
               // MdlCaissier_Fermer(conn);
            }
    
            return listeCaissiers;
        }
    
/*
    @Override
    public Caissier MdlCaissier_GetByID(int id) {
        PreparedStatement stmt = null;

        try {

            stmt = conn.prepareStatement(GET_BY_ID);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Caissier Caissier = new Caissier();
                Caissier.setId(rs.getInt(1));
                Caissier.setNom(rs.getString(2));
                Caissier.setPrenom(rs.getString(3));
                Caissier.setTelphone(rs.getString(4));
                Caissier.setEmail(rs.getString(5));
                Caissier.setUserName(rs.getString(6));
                Caissier.setMotPasse(rs.getString(7));
                Caissier.setNiveau(rs.getInt(8));

                return Caissier;
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
    public List<Caissier> MdlCaissier_GetByChamps(String champs, String valeur) {
        PreparedStatement stmt = null;
        List<Caissier> listeCaissiers = new ArrayList<Caissier>();

        try {
            stmt = conn.prepareStatement(GET_BY_CHAMPS + champs + "=?");
            stmt.setString(1, valeur);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Caissier Caissier = new Caissier();
                Caissier.setIdCaissier(rs.getInt(1));
                Caissier.setNom(rs.getString(2));
                Caissier.setUserName(rs.getString(3));
                Caissier.setMotPasse(rs.getString(4));
                listeCaissiers.add(Caissier);
            }
        } catch (SQLException e) {
            // e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            Mdl_Fermer(stmt);
           // MdlCaissier_Fermer(conn);
        }

        return listeCaissiers;
}

    @Override
    public int MdlCaissier_Modifier(Caissier Caissier) {
        PreparedStatement stmt = null;
       
        try {
            stmt = conn.prepareStatement(MODIFIER);
            stmt.setString(1, Caissier.getNom());
            stmt.setString(2, Caissier.getUserName());
            stmt.setString(3, Caissier.getMotPasse());
            stmt.setInt(4, Caissier.getIdCaissier());
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
    public int MdlCaissier_Supprimer(int id) {
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
    public int MdlCaissier_EnregistrerParRequete(String strSql,String valeur) {
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
    public List<Caissier> MdlCaissier_GetParRequete(String strSql) {
        PreparedStatement stmt = null;
        List<Caissier> listeCaissiers = new ArrayList<Caissier>();

        try {
            stmt = conn.prepareStatement(strSql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Caissier Caissier = new Caissier();
                Caissier.setIdCaissier(rs.getInt(1));
                Caissier.setNom(rs.getString(2));
                Caissier.setUserName(rs.getString(3));
                Caissier.setMotPasse(rs.getString(4));

                listeCaissiers.add(Caissier);
            }
        } catch (SQLException e) {
            // e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            Mdl_Fermer(stmt);
           // MdlCaissier_Fermer(conn);
        }

        return listeCaissiers;
}

}
