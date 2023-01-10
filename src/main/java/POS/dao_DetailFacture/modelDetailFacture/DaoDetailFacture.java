package POS.dao_DetailFacture.modelDetailFacture;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class DaoDetailFacture implements IDetailFacture {
    private static Connection conn = null;
    private static DaoDetailFacture instanceDao = null;

    // MySQL
    //private static final String PILOTE = "com.mysql.jdbc.Driver";
    private static final String NOM_BD = "bdcaisse";
    private static final String URL_BD = "jdbc:mysql://localhost/" +NOM_BD;     
    private static final String USAGER = "root";
    private static final String PASS = "";

    private static final String SUPPRIMER = "DELETE FROM Detail_Facture WHERE ID_Detail_Facture=?";
    private static final String GET_ALL = "SELECT * FROM Detail_Facture ORDER BY ID_Detail_Facture";
    private static final String GET_BY_ID = "SELECT * FROM Detail_Facture WHERE ID_Detail_Facture=?";
    private static final String GET_BY_CHAMPS = "SELECT * FROM Detail_Facture WHERE ";
    private static final String ENREGISTRER = "INSERT INTO Detail_Facture VALUES(0,?,?,?)";
    private static final String MODIFIER = "UPDATE Detail_Facture SET NOM_DetailFacture=?,USERNAME=?,MOTDEPASSE=? WHERE ID_Detail_Facture=?";

    // Singleton de connexion à la BD
    // getConnexion() est devenu une zonne critique. 
    // Pour ne pas avoir deux processus légers (threads) qui
    // appellent au même temps getConnexion

    private DaoDetailFacture(){};

    public static synchronized DaoDetailFacture getDetailFactureDao () {
        try {
                if (instanceDao == null) {
                    instanceDao = new DaoDetailFacture();
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
    public String MdlDetailFacture_Enregistrer(DetailFacture DetailFacture) {
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(ENREGISTRER, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, DetailFacture.getIdFacture());
            stmt.setInt(2, DetailFacture.getIdProduit());
            stmt.setDouble(3, DetailFacture.getQty());
           
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                DetailFacture.setIdDetailFacture(rs.getInt(1));
            }
            return "DetailFacture est bien enregistré ";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Mdl_Fermer(stmt);
           // MdlLivre_Fermer(conn);
        }
    }

    @Override
    public List<DetailFacture> MdlDetailFacture_GetAll() {
            PreparedStatement stmt = null;
            List<DetailFacture> listeDetailFactures = new ArrayList<DetailFacture>();
    
            try {
                stmt = conn.prepareStatement(GET_ALL);
                ResultSet rs = stmt.executeQuery();
    
                while (rs.next()) {
                    DetailFacture DetailFacture = new DetailFacture();
                    DetailFacture.setIdDetailFacture(rs.getInt(1));
                    DetailFacture.setIdFacture(rs.getInt(2));
                    DetailFacture.setIdProduit(rs.getInt(3));
                    DetailFacture.setQty(rs.getDouble(4));
    
                    listeDetailFactures.add(DetailFacture);
                }
            } catch (SQLException e) {
                // e.printStackTrace();
                throw new RuntimeException(e);
            } finally {
                Mdl_Fermer(stmt);
               // MdlDetailFacture_Fermer(conn);
            }
    
            return listeDetailFactures;
        }
    
/*
    @Override
    public DetailFacture MdlDetailFacture_GetByID(int id) {
        PreparedStatement stmt = null;

        try {

            stmt = conn.prepareStatement(GET_BY_ID);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                DetailFacture DetailFacture = new DetailFacture();
                DetailFacture.setId(rs.getInt(1));
                DetailFacture.setNom(rs.getString(2));
                DetailFacture.setPrenom(rs.getString(3));
                DetailFacture.setTelphone(rs.getString(4));
                DetailFacture.setEmail(rs.getString(5));
                DetailFacture.setUserName(rs.getString(6));
                DetailFacture.setMotPasse(rs.getString(7));
                DetailFacture.setNiveau(rs.getInt(8));

                return DetailFacture;
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
    public List<DetailFacture> MdlDetailFacture_GetByChamps(String champs, String valeur) {
        PreparedStatement stmt = null;
        List<DetailFacture> listeDetailFactures = new ArrayList<DetailFacture>();

        try {
            stmt = conn.prepareStatement(GET_BY_CHAMPS + champs + "=?");
            stmt.setString(1, valeur);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                DetailFacture DetailFacture = new DetailFacture();
                DetailFacture.setIdDetailFacture(rs.getInt(1));
                DetailFacture.setIdFacture(rs.getInt(2));
                DetailFacture.setIdProduit(rs.getInt(3));
                DetailFacture.setQty(rs.getDouble(4));
                listeDetailFactures.add(DetailFacture);
            }
        } catch (SQLException e) {
            // e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            Mdl_Fermer(stmt);
           // MdlDetailFacture_Fermer(conn);
        }

        return listeDetailFactures;
}

    @Override
    public int MdlDetailFacture_Modifier(DetailFacture DetailFacture) {
        PreparedStatement stmt = null;
       
        try {
            stmt = conn.prepareStatement(MODIFIER);
            stmt.setInt(1, DetailFacture.getIdFacture());
            stmt.setInt(2, DetailFacture.getIdProduit());
            stmt.setDouble(3, DetailFacture.getQty());
            stmt.setInt(4, DetailFacture.getIdDetailFacture());
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
    public int MdlDetailFacture_Supprimer(int id) {
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
    public int MdlDetailFacture_EnregistrerParRequete(String strSql,String valeur) {
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
    public List<DetailFacture> MdlDetailFacture_GetParRequete(String strSql) {
        PreparedStatement stmt = null;
        List<DetailFacture> listeDetailFactures = new ArrayList<DetailFacture>();

        try {
            stmt = conn.prepareStatement(strSql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                DetailFacture DetailFacture = new DetailFacture();
                DetailFacture.setIdDetailFacture(rs.getInt(1));
                DetailFacture.setIdFacture(rs.getInt(2));
                DetailFacture.setIdProduit(rs.getInt(3));
                DetailFacture.setQty(rs.getDouble(4));

                listeDetailFactures.add(DetailFacture);
            }
        } catch (SQLException e) {
            // e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            Mdl_Fermer(stmt);
           // MdlDetailFacture_Fermer(conn);
        }

        return listeDetailFactures;
}

}
