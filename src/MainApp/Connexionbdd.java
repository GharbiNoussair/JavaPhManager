package MainApp;
import java.sql.* ;
import com.mysql.jdbc.Driver;
public class Connexionbdd {

    public static Connection Connect() {
        try {
            @SuppressWarnings("rawtypes")
            Class c = Class.forName("com.mysql.jdbc.Driver") ;
            @SuppressWarnings("deprecation")
            Driver pilote = (Driver)c.newInstance() ;
            DriverManager.registerDriver(pilote);
             String protocole =  "jdbc:mysql:" ;
             String ip =  "localhost" ;
             String port =  "3306" ;
             String nomBase =  "test_db" ;
             String conString = protocole +  "//" + ip +  ":" + port +  "/" + nomBase+"?autoReconnect=true&useSSL=false" ;
             String nomConnexion =  "root" ; 
             String motDePasse =  "" ;
             Connection con = DriverManager.getConnection(conString, nomConnexion, motDePasse) ;
             return con;

        }catch(Exception e) {
        System.out.println(e);
        return null;
        }
    }
/*    public void requette() {
        try {
            String sql =  "select * from Marins" ;
            Statement smt = con.createStatement() ;
            ResultSet rs = smt.executeQuery(sql) ;
             while (rs.next()) {
               System.out.println(rs.getString("nom")) ;
            }
        }catch(Exception e) {
            System.out.println(e);
        }
    }*/
}