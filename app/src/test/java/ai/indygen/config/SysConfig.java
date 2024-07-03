package ai.indygen.config;
public class SysConfig {
    public static final String sAppBaseDir = System.getProperty("user.dir");
    public static final String sAppDataDir = sAppBaseDir + "/TestData";
    public static final String sAppCsvDataDir = sAppDataDir + "/CSV";
    public static final String sAppJsonDataDir = sAppDataDir + "/JSON";
    //public static final String sEnrichDBServer = "localhost";
    //public static final String sEnrichDBPort = "5432";
    //public static final String sEnrichDBName = "postgres";
    //public static final String sEnrichDBUserName = "postgres";
    //public static final String sEnrichDBPwd = "postgres";


    public static final String sEnrichDBServer = "192.168.14.12";
    public static final String sEnrichDBPort = "5432";
    public static final String sEnrichDBName = "postgres";
    public static final String sEnrichDBUserName = "postgres";
    public static final String sEnrichDBPwd = "pass1324";

    public static final String sEnrichDataSrcURL =  
            "jdbc:postgresql://"    +
            sEnrichDBServer         + ":" +
            sEnrichDBPort           + "/" +
            sEnrichDBName           + "?" + 
            "user="                 + 
            sEnrichDBUserName       + "&" +
            "password="             +
            sEnrichDBPwd            ;
    
     

}
