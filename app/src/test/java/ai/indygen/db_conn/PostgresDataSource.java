package ai.indygen.db_conn;

import javax.sql.DataSource;
import org.postgresql.ds.PGSimpleDataSource;
import java.sql.*;
import java.util.Map;
import java.util.HashMap;

public class PostgresDataSource {
    public static void main(String[] args) {
        //String sQry = "SELECT city, temp_lo, temp_hi, prcp, date, location FROM weather JOIN cities ON city = name;";
        //System.out.println(execDBQuery(sQry));

        Map<String,Object> ipEnrichment = new HashMap<String,Object>();
        ipEnrichment = getIPAddrEnrichmentData("162.243.147.25");

    }

    public static Map <String,Object> getIPAddrEnrichmentData(String ipAddr) {
        Map <String,Object> resultIPEnrichMap = new HashMap <String,Object>();
        /*
        * SELECT
        cb.geoname_id,
        cb.registered_country_geoname_id,
        cb.represented_country_geoname_id,
        cb.is_anonymous_proxy,
        cb.is_satellite_provider,
        cb.postal_code,
        cb.accuracy_radius,
        cl.locale_code,
        cl.continent_code,
        cl.country_iso_code,
        cl.subdivision_1_iso_code,
        cl.subdivision_1_name,
        cl.subdivision_2_iso_code,
        cl.subdivision_2_name,
        cl.metro_code,
        cl.time_zone,
        cl.is_in_european_union
        FROM
        geo_lite2_city_blocks cb
        JOIN
        geo_lite2_city_locations cl
        ON
        cb.geoname_id = cl.geoname_id
        WHERE
        cb.network >> inet '123.123.123.123';
        */

        String ipEnrichmentQry = " SELECT cb.geoname_id," +
        " cb.registered_country_geoname_id," +
        " cb.represented_country_geoname_id," +
        " cb.is_anonymous_proxy," +
        " cb.is_satellite_provider," +
        " cb.postal_code," +
        " cb.accuracy_radius," +
        " cl.locale_code," +
        " cl.continent_code," +
        " cl.country_iso_code," +
        " cl.subdivision_1_iso_code," +
        " cl.subdivision_1_name," +
        " cl.subdivision_2_iso_code," +
        " cl.subdivision_2_name," +
        " cl.metro_code," +
        " cl.time_zone," +
        " cl.is_in_european_union " +
        " FROM " +
        "  geo_lite2_city_blocks cb " +
        " JOIN " +
        "  geo_lite2_city_locations cl " +
        " ON  " +
        "  cb.geoname_id = cl.geoname_id  " +
        " WHERE  " +
        "  cb.network >> inet '"   +
        ipAddr                  + "';";

        System.out.println();
        System.out.println("SQL Query : "); 
        System.out.println(ipEnrichmentQry);

        resultIPEnrichMap = execDBQuery (ipEnrichmentQry);
        return resultIPEnrichMap;
    }
    
    public static Map <String,Object> getTestIPAddrEnrichmentData(String ipAddr) {
        Map<String,Object> ipEnrichmentMapStatic = new HashMap<String,Object>();
        ipEnrichmentMapStatic.put("subdivision_2_name","California");
        ipEnrichmentMapStatic.put("registered_country_geoname_id","6252001");
        ipEnrichmentMapStatic.put("represented_country_geoname_id","0");
        ipEnrichmentMapStatic.put("continent_code","NA");
        ipEnrichmentMapStatic.put("subdivision_1_iso_code","CA");
        ipEnrichmentMapStatic.put("accuracy_radius","20.0");
        ipEnrichmentMapStatic.put("time_zone","America/Los_Angeles");
        ipEnrichmentMapStatic.put("geoname_id","5391959");
        ipEnrichmentMapStatic.put("is_anonymous_proxy","true");
        ipEnrichmentMapStatic.put("subdivision_2_iso_code",null);
        ipEnrichmentMapStatic.put("locale_code","en");
        ipEnrichmentMapStatic.put("country_iso_code","US");
        ipEnrichmentMapStatic.put("metro_code","807");
        ipEnrichmentMapStatic.put("is_in_european_union","true");
        ipEnrichmentMapStatic.put("is_satellite_provider","true");
        ipEnrichmentMapStatic.put("postal_code","94124");
        ipEnrichmentMapStatic.put("subdivision_1_name","California");

        return ipEnrichmentMapStatic;
    }
    public static boolean testDBConn(){
        boolean res = false;
        try{
            Map <String,Object> resultMap = new HashMap <String,Object>();
            resultMap = execDBQuery("Select now()");

            if (resultMap != null){
                System.out.println("DB Test Conn Result : " + resultMap.values().toString());
                res = true;
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
        return res;
    }
    private static Map <String,Object> execDBQuery(String sEnrichQuery) {
        DataSource dataSource = createDataSource();
        Connection conn = getDSConnection(dataSource);
        Map <String,Object> resultMap = new HashMap <String,Object>();
        try {
            PreparedStatement stmt = conn.prepareStatement(sEnrichQuery);
            ResultSet rs = stmt.executeQuery();
            ResultSetMetaData md = rs.getMetaData();
            int columns = md.getColumnCount();

            if (rs.next()){
                //sQueryRes = sQueryRes + rs.getString("city");
                for (int i = 1; i <= columns; i++) {
                    resultMap.put(md.getColumnLabel(i), rs.getObject(i));
                }
                // Print the HashMap
                System.out.println();
                System.out.println("Query Result Map : "); 
                for (Map.Entry<String, Object> entry : resultMap.entrySet()) {
                    System.out.println(entry.getKey() + " = " + entry.getValue());
                }
                System.out.println();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return resultMap;
    }

    private static Connection getDSConnection (DataSource dataSource) {
        Connection conn = null;
        try {
            conn = dataSource.getConnection();    
        }
        catch (Exception e) {
            e.printStackTrace();
        }
         return conn;
    }

    private static DataSource createDataSource() {
        final String dbURL = ai.indygen.config.SysConfig.sEnrichDataSrcURL;
        System.out.println();
        System.out.println("JDBC Connection URL =" + dbURL);
        final PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setUrl(dbURL);
        return dataSource;
    }
}
