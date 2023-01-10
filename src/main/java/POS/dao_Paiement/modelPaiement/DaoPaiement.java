package POS.dao_Paiement.modelPaiement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class DaoPaiement implements IPaiement {
    private static Connection conn = null;
    private static DaoPaiement instanceDao = null;

    // MySQL
    //private static final String PILOTE = "com.mysql.jdbc.Driver";
    private static final String NOM_BD = "bdcaisse";
    private static final String URL_BD = "jdbc:mysql://localhost/" +NOM_BD;     
    private static final String USAGER = "root";
    private static final String PASS = "";

    private static final String SUPPRIMER = "DELETE FROM Paiement WHERE ID_Paiement=?";
    private static final String GET_ALL = "SELECT * FROM Paiement ORDER BY ID_Paiement";
    private static final String GET_BY_ID = "SELECT * FROM Paiement WHERE ID_Paiement=?";
    private static final String GET_BY_CHAMPS = "SELECT * FROM Paiement WHERE ";
    private static final String ENREGISTRER = "INSERT INTO Paiement VALUES(0,?,?,?,?,?)";
    private static final String MODIFIER = "UPDATE Paiement SET ID_FACTURE=?,ID_CAISSIER=?,DATE_PAIEMENT=?,TYPE_PAIEMENT=?,MONTANT_PAIEMENT=? WHERE ID_Paiement=?";

    // Singleton de connexion à la BD
    // getConnexion() est devenu une zonne critique. 
    // Pour ne pas avoir deux processus légers (threads) qui
    // appellent au même temps getConnexion

    private DaoPaiement(){};

    public static synchronized DaoPaiement getPaiementDao () {
        try {
                if (instanceDao == null) {
                    instanceDao = new DaoPaiement();
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
    public String MdlPaiement_Enregistrer(Paiement Paiement) {
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(ENREGISTRER, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, Paiement.getIdFacture());
            stmt.setInt(2, Paiement.getIdSession());
            stmt.setString(3, Paiement.getDatePaiement());
            stmt.setString(4, Paiement.getTypePaiement());
            stmt.setDouble(5, Paiement.getMontantPaiement());
           
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                Paiement.setIdPaiement(rs.getInt(1));
            }
            return "Paiement est bien enregistré ";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Mdl_Fermer(stmt);
           // MdlLivre_Fermer(conn);
        }
    }

    @Override
    public List<Paiement> MdlPaiement_GetAll() {
            PreparedStatement stmt = null;
            List<Paiement> listePaiements = new ArrayList<Paiement>();
    
            try {
                stmt = conn.prepareStatement(GET_ALL);
                ResultSet rs = stmt.executeQuery();
    
                while (rs.next()) {
                    Paiement Paiement = new Paiement();
                    Paiement.setIdPaiement(rs.getInt(1));
                    Paiement.setIdFacture(rs.getInt(2));
                    Paiement.setIdSession(rs.getInt(3));
                    Paiement.setDatePaiement(rs.getString(4));
                    Paiement.setTypePaiement(rs.getString(5));
                    Paiement.setMontantPaiement(rs.getDouble(6));
        
                    listePaiements.add(Paiement);
                    }
            } catch (SQLException e) {
                // e.printStackTrace();
                throw new RuntimeException(e);
            } finally {
                Mdl_Fermer(stmt);
               // MdlPaiement_Fermer(conn);
            }
    
            return listePaiements;
        }
    
/*
    @Override
    public Paiement MdlPaiement_GetByID(int id) {
        PreparedStatement stmt = null;

        try {

            stmt = conn.prepareStatement(GET_BY_ID);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Paiement Paiement = new Paiement();
                Paiement.setId(rs.getInt(1));
                Paiement.setNom(rs.getString(2));
                Paiement.setPrenom(rs.getString(3));
                Paiement.setTelphone(rs.getString(4));
                Paiement.setEmail(rs.getString(5));
                Paiement.setUserName(rs.getString(6));
                Paiement.setMotPasse(rs.getString(7));
                Paiement.setNiveau(rs.getInt(8));

                return Paiement;
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
    public List<Paiement> MdlPaiement_GetByChamps(String champs, String valeur) {
        PreparedStatement stmt = null;
        List<Paiement> listePaiements = new ArrayList<Paiement>();

        try {
            stmt = conn.prepareStatement(GET_BY_CHAMPS + champs + "=?");
            stmt.setString(1, valeur);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Paiement Paiement = new Paiement();
                Paiement.setIdPaiement(rs.getInt(1));
                Paiement.setIdFacture(rs.getInt(2));
                Paiement.setIdSession(rs.getInt(3));
                Paiement.setDatePaiement(rs.getString(4));
                Paiement.setTypePaiement(rs.getString(5));
                Paiement.setMontantPaiement(rs.getDouble(6));
                listePaiements.add(Paiement);
            }
        } catch (SQLException e) {
            // e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            Mdl_Fermer(stmt);
           // MdlPaiement_Fermer(conn);
        }

        return listePaiements;
}

    @Override
    public int MdlPaiement_Modifier(Paiement Paiement) {
        PreparedStatement stmt = null;
       
        try {
            stmt = conn.prepareStatement(MODIFIER);
            stmt.setInt(1, Paiement.getIdFacture());
            stmt.setInt(2, Paiement.getIdSession());
            stmt.setString(3, Paiement.getDatePaiement());
            stmt.setString(4, Paiement.getTypePaiement());
            stmt.setDouble(5, Paiement.getMontantPaiement());
            stmt.setInt(6, Paiement.getIdPaiement());

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
    public int MdlPaiement_Supprimer(int id) {
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
    public int MdlPaiement_EnregistrerParRequete(String strSql,String valeur) {
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
    public List<Paiement> MdlPaiement_GetParRequete(String strSql) {
        PreparedStatement stmt = null;
        List<Paiement> listePaiements = new ArrayList<Paiement>();

        try {
            stmt = conn.prepareStatement(strSql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Paiement Paiement = new Paiement();
                Paiement.setIdPaiement(rs.getInt(1));
                Paiement.setIdFacture(rs.getInt(2));
                Paiement.setIdSession(rs.getInt(3));
                Paiement.setDatePaiement(rs.getString(4));
                Paiement.setTypePaiement(rs.getString(5));
                Paiement.setMontantPaiement(rs.getDouble(6));

                listePaiements.add(Paiement);
            }
        } catch (SQLException e) {
            // e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            Mdl_Fermer(stmt);
           // MdlPaiement_Fermer(conn);
        }

        return listePaiements;
}

}
