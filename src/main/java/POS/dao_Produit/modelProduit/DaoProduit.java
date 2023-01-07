package POS.dao_Produit.modelProduit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class DaoProduit implements IProduit {
    private static Connection conn = null;
    private static DaoProduit instanceDao = null;

    // MySQL
    //private static final String PILOTE = "com.mysql.jdbc.Driver";
    private static final String NOM_BD = "bdcaisse";
    private static final String URL_BD = "jdbc:mysql://localhost/" +NOM_BD;     
    private static final String USAGER = "root";
    private static final String PASS = "";

    private static final String SUPPRIMER = "DELETE FROM Produit WHERE ID_Produit=?";
    private static final String GET_ALL = "SELECT * FROM Produit ORDER BY ID_Produit";
    private static final String GET_BY_ID = "SELECT * FROM Produit WHERE ID_Produit=?";
    private static final String GET_BY_CHAMPS = "SELECT * FROM Produit WHERE ";
    private static final String ENREGISTRER = "INSERT INTO Produit VALUES(0,?,?,?)";
    private static final String MODIFIER = "UPDATE Produit SET NOM_Produit=?,PRIX_VENTE=?,CODE_PRODUIT=? WHERE ID_Produit=?";

    // Singleton de connexion à la BD
    // getConnexion() est devenu une zonne critique. 
    // Pour ne pas avoir deux processus légers (threads) qui
    // appellent au même temps getConnexion

    private DaoProduit(){};

    public static synchronized DaoProduit getProduitDao () {
        try {
                if (instanceDao == null) {
                    instanceDao = new DaoProduit();
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
    public String MdlProduit_Enregistrer(Produit Produit) {
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(ENREGISTRER, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, Produit.getNom());
            stmt.setDouble(2, Produit.getPrixVente());
            stmt.setString(3, Produit.getCodeProduit());
           
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                Produit.setIdProduit(rs.getInt(1));
            }
            return "Produit est bien enregistré ";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Mdl_Fermer(stmt);
           // MdlLivre_Fermer(conn);
        }
    }

    @Override
    public List<Produit> MdlProduit_GetAll() {
            PreparedStatement stmt = null;
            List<Produit> listeProduits = new ArrayList<Produit>();
    
            try {
                stmt = conn.prepareStatement(GET_ALL);
                ResultSet rs = stmt.executeQuery();
    
                while (rs.next()) {
                    Produit Produit = new Produit();
                    Produit.setIdProduit(rs.getInt(1));
                    Produit.setNom(rs.getString(2));
                    Produit.setPrixVente(rs.getDouble(3));
                    Produit.setCodeProduit(rs.getString(4));

                    listeProduits.add(Produit);
                }
            } catch (SQLException e) {
                // e.printStackTrace();
                throw new RuntimeException(e);
            } finally {
                Mdl_Fermer(stmt);
               // MdlProduit_Fermer(conn);
            }
    
            return listeProduits;
        }
    
/*
    @Override
    public Produit MdlProduit_GetByID(int id) {
        PreparedStatement stmt = null;

        try {

            stmt = conn.prepareStatement(GET_BY_ID);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Produit Produit = new Produit();
                Produit.setId(rs.getInt(1));
                Produit.setNom(rs.getString(2));
                Produit.setPrenom(rs.getString(3));
                Produit.setTelphone(rs.getString(4));
                Produit.setEmail(rs.getString(5));
                Produit.setUserName(rs.getString(6));
                Produit.setMotPasse(rs.getString(7));
                Produit.setNiveau(rs.getInt(8));

                return Produit;
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
    public List<Produit> MdlProduit_GetByChamps(String champs, String valeur) {
        PreparedStatement stmt = null;
        List<Produit> listeProduits = new ArrayList<Produit>();

        try {
            stmt = conn.prepareStatement(GET_BY_CHAMPS + champs + "=?");
            stmt.setString(1, valeur);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Produit Produit = new Produit();
                Produit.setIdProduit(rs.getInt(1));
                Produit.setNom(rs.getString(2));
                Produit.setPrixVente(rs.getDouble(3));
                Produit.setCodeProduit(rs.getString(4));
                listeProduits.add(Produit);
            }
        } catch (SQLException e) {
            // e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            Mdl_Fermer(stmt);
           // MdlProduit_Fermer(conn);
        }

        return listeProduits;
}

    @Override
    public int MdlProduit_Modifier(Produit Produit) {
        PreparedStatement stmt = null;
       
        try {
            stmt = conn.prepareStatement(MODIFIER);
            stmt.setString(1, Produit.getNom());
            stmt.setDouble(2, Produit.getPrixVente());
            stmt.setString(3, Produit.getCodeProduit());
            stmt.setInt(4, Produit.getIdProduit());

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
    public int MdlProduit_Supprimer(int id) {
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
    public int MdlProduit_EnregistrerParRequete(String strSql,String valeur) {
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
    public List<Produit> MdlProduit_GetParRequete(String strSql) {
        PreparedStatement stmt = null;
        List<Produit> listeProduits = new ArrayList<Produit>();

        try {
            stmt = conn.prepareStatement(strSql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Produit Produit = new Produit();
                Produit.setIdProduit(rs.getInt(1));
                Produit.setNom(rs.getString(2));
                Produit.setPrixVente(rs.getDouble(3));
                Produit.setCodeProduit(rs.getString(4));

                listeProduits.add(Produit);
            }
        } catch (SQLException e) {
            // e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            Mdl_Fermer(stmt);
           // MdlProduit_Fermer(conn);
        }

        return listeProduits;
}

}
