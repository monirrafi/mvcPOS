package POS.dao_Session.modelSession;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class DaoSession implements ISession {
    private static Connection conn = null;
    private static DaoSession instanceDao = null;

    // MySQL
    //private static final String PILOTE = "com.mysql.jdbc.Driver";
    private static final String NOM_BD = "bdcaisse";
    private static final String URL_BD = "jdbc:mysql://localhost/" +NOM_BD;     
    private static final String USAGER = "root";
    private static final String PASS = "";

    private static final String SUPPRIMER = "DELETE FROM Session WHERE ID_Session=?";
    private static final String GET_ALL = "SELECT * FROM Session ORDER BY ID_Session";
    private static final String GET_BY_ID = "SELECT * FROM Session WHERE ID_Session=?";
    private static final String GET_BY_CHAMPS = "SELECT * FROM Session WHERE ";
    private static final String ENREGISTRER = "INSERT INTO Session VALUES(0,?,?,?,?)";
    private static final String MODIFIER = "UPDATE Session SET ID_CAISSIER=?,DATE_SESSION=?,HEURE_OUVERTURE=?,HEURE_FERMETURE=? WHERE ID_Session=?";

    // Singleton de connexion à la BD
    // getConnexion() est devenu une zonne critique. 
    // Pour ne pas avoir deux processus légers (threads) qui
    // appellent au même temps getConnexion

    private DaoSession(){};

    public static synchronized DaoSession getSessionDao () {
        try {
                if (instanceDao == null) {
                    instanceDao = new DaoSession();
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
    public String MdlSession_Enregistrer(Session session) {
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(ENREGISTRER, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, session.getCaissier());
            stmt.setString(2, session.getDateSession());
            stmt.setString(3, session.getHeureOuverture());
            stmt.setString(4, session.getHeureFermeture());
           
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                session.setIdSession(rs.getInt(1));
            }
            return "Session est bien enregistré ";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            Mdl_Fermer(stmt);
           // MdlLivre_Fermer(conn);
        }
    }

    @Override
    public List<Session> MdlSession_GetAll() {
            PreparedStatement stmt = null;
            List<Session> listeSessions = new ArrayList<Session>();
    
            try {
                stmt = conn.prepareStatement(GET_ALL);
                ResultSet rs = stmt.executeQuery();
    
                while (rs.next()) {
                    Session session = new Session();
                    session.setIdSession(rs.getInt(1));
                    session.setCaissier(rs.getInt(2));
                    session.setDateSession(rs.getString(3));
                    session.setHeureOuverture(rs.getString(4));
                    session.setHeureFermeture(rs.getString(5));
    
                    listeSessions.add(session);
                    }
            } catch (SQLException e) {
                // e.printStackTrace();
                throw new RuntimeException(e);
            } finally {
                Mdl_Fermer(stmt);
               // MdlSession_Fermer(conn);
            }
    
            return listeSessions;
        }
    
/*
    @Override
    public Session MdlSession_GetByID(int id) {
        PreparedStatement stmt = null;

        try {

            stmt = conn.prepareStatement(GET_BY_ID);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Session Session = new Session();
                Session.setId(rs.getInt(1));
                Session.setNom(rs.getString(2));
                Session.setPrenom(rs.getString(3));
                Session.setTelphone(rs.getString(4));
                Session.setEmail(rs.getString(5));
                Session.setUserName(rs.getString(6));
                Session.setMotPasse(rs.getString(7));
                Session.setNiveau(rs.getInt(8));

                return Session;
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
    public List<Session> MdlSession_GetByChamps(String champs, String valeur) {
        PreparedStatement stmt = null;
        List<Session> listeSessions = new ArrayList<Session>();

        try {
            stmt = conn.prepareStatement(GET_BY_CHAMPS + champs + "=?");
            stmt.setString(1, valeur);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Session session = new Session();
                session.setIdSession(rs.getInt(1));
                session.setCaissier(rs.getInt(2));
                session.setDateSession(rs.getString(3));
                session.setHeureOuverture(rs.getString(4));
                session.setHeureFermeture(rs.getString(5));

                listeSessions.add(session);
            }
        } catch (SQLException e) {
            // e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            Mdl_Fermer(stmt);
           // MdlSession_Fermer(conn);
        }

        return listeSessions;
}

    @Override
    public int MdlSession_Modifier(Session session) {
        PreparedStatement stmt = null;
       
        try {
            stmt = conn.prepareStatement(MODIFIER);
            stmt.setInt(1, session.getCaissier());
            stmt.setString(2, session.getDateSession());
            stmt.setString(3, session.getHeureOuverture());
            stmt.setString(4, session.getHeureFermeture());
            stmt.setInt(5, session.getIdSession());

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
    public int MdlSession_Supprimer(int id) {
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
    public int MdlSession_EnregistrerParRequete(String strSql,String valeur) {
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
    public List<Session> MdlSession_GetParRequete(String strSql) {
        PreparedStatement stmt = null;
        List<Session> listeSessions = new ArrayList<Session>();

        try {
            stmt = conn.prepareStatement(strSql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Session session = new Session();
                session.setIdSession(rs.getInt(1));
                session.setCaissier(rs.getInt(2));
                session.setDateSession(rs.getString(3));
                session.setHeureOuverture(rs.getString(4));
                session.setHeureFermeture(rs.getString(5));

                listeSessions.add(session);
            }
        } catch (SQLException e) {
            // e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            Mdl_Fermer(stmt);
           // MdlSession_Fermer(conn);
        }

        return listeSessions;
}

}
