package ai.indygen.learn_exp;

public class GenSetEnv {
    public static void main(String[] args) 
    {   
        System.setProperty("BOOTSTRAP_SERVERS", "localhost");
        System.setProperty("BOOTSTRAP_PORT", "19092");

        String sBOOTSTRAP_SERVERS = System.getProperty("BOOTSTRAP_SERVERS");
        String sBOOTSTRAP_PORT = System.getProperty("BOOTSTRAP_PORT");

        String sBOOTSTRAP_SERVERS_CONFIG = sBOOTSTRAP_SERVERS + ":" + sBOOTSTRAP_PORT;

        System.out.println(sBOOTSTRAP_SERVERS); 
        System.out.println(sBOOTSTRAP_PORT); 
        System.out.println(sBOOTSTRAP_SERVERS_CONFIG); 
        
  
    }
}
