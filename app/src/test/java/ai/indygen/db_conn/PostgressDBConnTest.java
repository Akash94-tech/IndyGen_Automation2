package ai.indygen.db_conn;

import java.sql.Connection;
import java.sql.DriverManager;


public class PostgressDBConnTest {
    public static void main(String[] args) {
        localDBConnTest();
        indyGenDBConnTest();
    
    }
    
    public static void localDBConnTest(){
        Connection c = null;
        try {
           Class.forName("org.postgresql.Driver");
           c = DriverManager
              .getConnection("jdbc:postgresql://localhost/postgres",
              "postgres", "postgres");
        } catch (Exception e) {
           e.printStackTrace();
           System.err.println(e.getClass().getName()+": "+e.getMessage());
           System.exit(0);
        }
        System.out.println();
        System.out.println("-----------------------------------------------");
        System.out.println("Connected to Local Database successfully");
        System.out.println("-----------------------------------------------");
        System.out.println();
    }

    public static void indyGenDBConnTest(){
        Connection c = null;
        try {
           Class.forName("org.postgresql.Driver");
           c = DriverManager
              .getConnection("jdbc:postgresql://192.168.14.12/postgres",
              "postgres", "pass1324");
        } catch (Exception e) {
           e.printStackTrace();
           System.err.println(e.getClass().getName()+": "+e.getMessage());
           System.exit(0);
        }
        System.out.println();
        System.out.println("-----------------------------------------------");
        System.out.println("Connected to IndyGen Database successfully");
        System.out.println("-----------------------------------------------");
        System.out.println();    }
}
