package ai.indygen.tests;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

import ai.indygen.config.SysConfig;
import ai.indygen.db_conn.PostgresDataSource;
import ai.indygen.utils.EnrichDBJSONMaps;
import ai.indygen.utils.JSONUtils;

public class Test_StreamsPublishConsume {
    public static void main(String[] args) {   
        String newUserDir = System.getProperty("user.dir") + "/app";
        System.setProperty("BOOTSTRAP_SERVERS", "localhost");
        System.setProperty("BOOTSTRAP_PORT", "19092");

        System.setProperty("user.dir",newUserDir);

        System.out.println(System.getProperty("user.dir"));

        Test_StreamsPublishConsume appUT = new Test_StreamsPublishConsume();

        appUT.initTest();

        appUT.testcase01_CSV_Publish_Consume_Test();

        appUT.testcase02_JSON_Publish_Consume_Test();
        
        appUT.testcase03_Enrichment_ECS_DB_Test();
        
        System.out.println(); 
    }
//  @Test
    public static void debug_JavaVersion (){
        String sJVersion = System. getProperty("java.version");
        System. out. println("----------------------------------------------------------------");
        System. out. println("Current JVM version - " + sJVersion );
        Assert.assertEquals(sJVersion.length()>0, true);
        System. out. println("----------------------------------------------------------------");
    }

//  @Test
    public static void debug_StreamConfig (){
        String sBOOTSTRAP_SERVERS = System.getenv("BOOTSTRAP_SERVERS");
        String sBOOTSTRAP_PORT = System.getenv("BOOTSTRAP_PORT");
        System.out. println();
        System.out. println("----------------------------------------------------------------");
        System.out. println("Environment Variable :  BOOTSTRAP_SERVERS=" + sBOOTSTRAP_SERVERS );
        System.out. println("Environment Variable :  BOOTSTRAP_PORT=" + sBOOTSTRAP_PORT );
        System.out. println("----------------------------------------------------------------");

        if (sBOOTSTRAP_SERVERS== null || sBOOTSTRAP_SERVERS.isEmpty()){
            sBOOTSTRAP_SERVERS = System.getProperty("BOOTSTRAP_SERVERS");
            System.out. println("System Property :  BOOTSTRAP_SERVERS=" + sBOOTSTRAP_SERVERS );
            if (sBOOTSTRAP_SERVERS== null || sBOOTSTRAP_SERVERS.isEmpty()){
                sBOOTSTRAP_SERVERS = "localhost";
            }
        }

        if (sBOOTSTRAP_PORT== null || sBOOTSTRAP_PORT.isEmpty()){
            sBOOTSTRAP_PORT = System.getProperty("BOOTSTRAP_PORT");
            System.out. println("System Property :  BOOTSTRAP_PORT=" + sBOOTSTRAP_PORT );
            if (sBOOTSTRAP_PORT== null || sBOOTSTRAP_PORT.isEmpty()){
                sBOOTSTRAP_PORT = "19092";
            }
        }
        System.out. println();
        System.out. println("----------------------------------------------------------------");
        System.out.println();
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println("Stream Server (BOOTSTRAP_SERVERS) = "+sBOOTSTRAP_SERVERS);
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println("Stream Server (sBOOTSTRAP_PORT) ="+sBOOTSTRAP_PORT);
        System.out.println();
        System.out.println();
        String sBOOTSTRAP_SERVERS_CONFIG = sBOOTSTRAP_SERVERS + ":" + sBOOTSTRAP_PORT;
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println("Stream Server Config (BOOTSTRAP_SERVERS_CONFIG) ="+sBOOTSTRAP_SERVERS_CONFIG);
        System.out. println("----------------------------------------------------------------");
    }
    //  @Test
        public static void debug_SysConfig (){
            System.out. println();
            System.out. println("----------------------------------------------------------------");
            System.out.println();
            System.out.println("SysConfig : sAppBaseDir=" + SysConfig.sAppBaseDir);
            System.out.println("SysConfig : sAppCsvDataDir=" + SysConfig.sAppCsvDataDir);
            System.out.println("SysConfig : sAppDataDir=" + SysConfig.sAppDataDir);
            System.out.println("SysConfig : sAppJsonDataDir=" + SysConfig.sAppJsonDataDir);
            System.out.println("SysConfig : sEnrichDBName=" + SysConfig.sEnrichDBName);
            System.out.println("SysConfig : sEnrichDBPort=" + SysConfig.sEnrichDBPort);
            System.out.println("SysConfig : sEnrichDBPwd=" + SysConfig.sEnrichDBPwd);
            System.out.println("SysConfig : sEnrichDBServer=" + SysConfig.sEnrichDBServer);
            System.out.println("SysConfig : sEnrichDBUserName=" + SysConfig.sEnrichDBUserName);
            System.out.println("SysConfig : sEnrichDataSrcURL=" + SysConfig.sEnrichDataSrcURL);
            System.out. println();
            System.out. println("----------------------------------------------------------------");
            System.out.println();
        }
    //  @Test
    public static void debug_PostgresDBConn (){
        PostgresDataSource.testDBConn();
    }

    public static void setIndyGenServerProps (){
        System.setProperty("BOOTSTRAP_SERVERS", "192.168.14.12");
        System.setProperty("BOOTSTRAP_PORT", "19092");
    }
 
@BeforeTest
    public void initTest(){
        //setIndyGenServerProps ();
        debug_JavaVersion();
        debug_StreamConfig();
        debug_SysConfig();
        debug_PostgresDBConn ();
    }
@Test
    public void testcase01_CSV_Publish_Consume_Test(){

        String inputCSVFile  = SysConfig.sAppCsvDataDir +  "/securonix_test.csv";
        String sKafkaTopic = "publish-log";
        String sKafkaECSTopic = "publish-log-ecs";
        String sExpJsonFile = "sample_ecs.json";

        JSONUtils jsonUtils = new JSONUtils();
        
        try {
            ObjectMapper objMapper = new ObjectMapper();
            ArrayNode listOfCsvJsonContents = objMapper.createArrayNode();
            ArrayNode listOfMappedJsonRecords = objMapper.createArrayNode();
    
            // Test Step : Publish the CSV file records 
            jsonUtils.publishCsvRecords (inputCSVFile,sKafkaTopic );

            // Test Step : Prepare the CSV test data for data comp 
            listOfCsvJsonContents = jsonUtils.getCsvJsonList (inputCSVFile);
            
            // Test Step : Map the CSV data with ECS headers
            listOfMappedJsonRecords = jsonUtils.getMappedJson2EcsRecords (listOfCsvJsonContents);

            // Test Step : Get the JSON records from topic
            ArrayList<String> consumedRecs = jsonUtils.consumeJsonFromTopic(sKafkaECSTopic);
            //ArrayList<String> consumedRecs = jsonUtils.getJsonFromDataDir(sExpJsonFile);

            // Test Step : Iterate through each record to verify
            for( JsonNode mappedJSONECSNode : listOfMappedJsonRecords) {
                int icount = 0;
                ObjectMapper inputNodemapper = new ObjectMapper();
                JsonNode topicJsonNode = inputNodemapper.readTree(consumedRecs.get(icount));
                // Test Step : Compare the CSV JSON mapped date with topic data
                jsonUtils.compareJSON (mappedJSONECSNode, topicJsonNode);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

@Test
    public void testcase02_JSON_Publish_Consume_Test(){

        String inputJsonFile  = SysConfig.sAppJsonDataDir +  "/Input_events_logs.json";
        String sKafkaTopic = "publish-log";
        String sKafkaECSTopic = "publish-log-ecs";
        String sExpJsonFile = "ecs_stream_output.json";

        JSONUtils jsonUtils = new JSONUtils();
        SoftAssert softAssert = new SoftAssert();

        try {
            ObjectMapper objMapper = new ObjectMapper();
            ArrayNode listOfInputJsonContents = objMapper.createArrayNode();
            ArrayNode listOfMappedJsonRecords = objMapper.createArrayNode();

            // Test Step : Publish the CSV file records 
            jsonUtils.publishJsonRecords (inputJsonFile,sKafkaTopic );

            // Test Step : Prepare the CSV test data for data comp 
            listOfInputJsonContents = jsonUtils.getInputJsonList (inputJsonFile);
            
            // Test Step : Map the Json Key data with ECS headers
            listOfMappedJsonRecords = jsonUtils.getMappedJson2EcsRecords (listOfInputJsonContents);

            // Test Step : Get the JSON records from topic
            ArrayList<String> consumedRecs = jsonUtils.consumeJsonFromTopic(sKafkaECSTopic);
            //ArrayList<String> consumedRecs = jsonUtils.getJsonFromDataDir(sExpJsonFile);

            // Test Step : Iterate through each record to verify
            for( JsonNode mappedJSONECSNode : listOfMappedJsonRecords) {
                int icount = 0;
                ObjectMapper inputNodemapper = new ObjectMapper();

                Assert.assertTrue(consumedRecs.size() > 0,"Events Log Records found in ECS topic - [" + sKafkaECSTopic+"]");
                        
                JsonNode topicJsonNode = inputNodemapper.readTree(consumedRecs.get(icount));
                // Test Step : Compare the CSV JSON mapped date with topic data
                jsonUtils.compareJSON (mappedJSONECSNode, topicJsonNode);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        softAssert.assertAll();
    }

@Test
    public void testcase03_Enrichment_ECS_DB_Test(){
        JSONUtils jsonUtils = new JSONUtils();
        String sKafkaEnrichedTopic = "enriched-log-ecs";
        String sTestIPStr = "10.83.152.35";
        String ecsEnrichedFilePath = SysConfig.sAppJsonDataDir + "/enriched_ecs.json";
        SoftAssert softAssert = new SoftAssert();

        try{ 
            jsonUtils.publishECSEnrichedRecords (ecsEnrichedFilePath,sKafkaEnrichedTopic );
            // Test Step: Get the JSON records from topic
            ArrayList<String> consumedRecs = jsonUtils.consumeJsonFromTopic(sKafkaEnrichedTopic); 
            String str = consumedRecs.get(0);
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(str);
            Map<String, Object> ecsJsonIPEnrichmentMap = 
                mapper.convertValue(jsonNode, 
                new TypeReference<Map<String, Object>>(){});
            
            System.out.println();
            sTestIPStr = ecsJsonIPEnrichmentMap.get("host.ip")+"";
            System.out.println("sTestIPStr (host.ip) = " + sTestIPStr);
            System.out.println();            
            Map<String, Object> dbIPEnrichmentMap = new HashMap <String,Object>();
            
            //dbIPEnrichmentMap = PostgresDataSource.getIPAddrEnrichmentData(sTestIPStr);
            dbIPEnrichmentMap = PostgresDataSource.getTestIPAddrEnrichmentData(sTestIPStr);

            Map<String,Object> fileJsonIPEnrichmentMap = new HashMap<String,Object>();
            fileJsonIPEnrichmentMap = JSONUtils.jsonFileToMap(ecsEnrichedFilePath);

            Map<String,Object> JsonECSIPEnrichmentMap = new HashMap<String,Object>();
            
            //JsonECSIPEnrichmentMap = fileJsonIPEnrichmentMap;
            JsonECSIPEnrichmentMap = ecsJsonIPEnrichmentMap;

            System.out.println();
            System.out.println(dbIPEnrichmentMap);
            System.out.println();
            System.out.println(JsonECSIPEnrichmentMap);   
            
            Map<String,String> mapECSJSON = EnrichDBJSONMaps.getIPMap();

            for (Map.Entry<String, String> entry : mapECSJSON.entrySet()) {
                String sMapKey = entry.getKey();

                String sDBKey   = sMapKey;
                String sDBVal   = dbIPEnrichmentMap.get(sDBKey)+"";
                String sECSKey  = entry.getValue();
                String sECSVal  = JsonECSIPEnrichmentMap.get(sECSKey)+"";
                System.out.println();
                System.out.println();
                System.out.println("Verifying: DB vs ECS Enrichment for Keys (" + sMapKey + ", " + sECSKey + ")");
                System.out.println("dbIPEnrichmentMap =>(" + sDBKey + "," + sDBVal + ")");
                System.out.println("fileJsonIPEnrichmentMap =>(" + sECSKey + "," + sECSVal + ")");
                softAssert.assertEquals(sDBVal, sECSVal, "Compare - DB and ECS Enrichment values for " + sMapKey + ": (Result) => ");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        softAssert.assertAll();
    }
}

