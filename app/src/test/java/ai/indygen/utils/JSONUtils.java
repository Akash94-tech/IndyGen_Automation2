package ai.indygen.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import ai.indygen.config.SysConfig;
import ai.indygen.dev.ECSMaps;

import org.testng.Assert;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.common.base.Charsets;
import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import ai.indygen.streams.*;
import java.util.Properties;
 
public class JSONUtils {
    
    public void publishCsvRecords(String sCsvFilePath, String sTopicName) {        
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode cSVJsonList = mapper.createArrayNode();

        String sHeaderLine = "";
        JsonNode aCSV2JsonNode;
        //Read the header Line from Input CSV
        // Read the Content (Values) from the Input CSV and store into a List

		try {
			Scanner scanner = new Scanner(new File(sCsvFilePath));

            if (scanner.hasNextLine()) {
				sHeaderLine = scanner.nextLine();
			}

            Properties props = StreamConfig.producerConfig();

            PublishRecord publisher = new PublishRecord (props);

            publisher.createTopic(sTopicName);

			while (scanner.hasNextLine()) {
                
                String sDataValueLine;
                String csvPubRecord = "";
                Map<String, Object> map = new HashMap<>();
                
                map.put("source", "securonix_csv");
                map.put("headers", sHeaderLine);

				sDataValueLine= scanner.nextLine();

                map.put("value", sDataValueLine);
                ObjectMapper objectMapper = new ObjectMapper();
                try {
                    csvPubRecord = objectMapper.writeValueAsString(map);
                    System.out.println(csvPubRecord);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }

                publisher.publish(sTopicName, "Message", csvPubRecord);
                System.out.println();
                System.out.println("End - Publishing record .... Topic Name:" + sTopicName);
                System.out.println();
 			}
			scanner.close();
		} catch (FileNotFoundException e) {
            System.err.println("Error reading the file: " + sCsvFilePath +"\n\n" + e.getMessage()); 
        }
    }

    public ArrayNode getCsvJsonList(String sCsvFilePath) {        
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode cSVJsonList = mapper.createArrayNode();

        String sHeaderLine = "";
        String sDataValueLine;
        JsonNode aCSV2JsonNode;
        //Read the header Line from Input CSV
        // Read the Content (Values) from the Input CSV and store into a List

		try {
			Scanner scanner = new Scanner(new File(sCsvFilePath));

            if (scanner.hasNextLine()) {
				sHeaderLine = scanner.nextLine();
			}

			while (scanner.hasNextLine()) {
				sDataValueLine= scanner.nextLine();
                aCSV2JsonNode = convertCsv2Json(sDataValueLine, sHeaderLine);
                cSVJsonList.add(aCSV2JsonNode);
			}
			scanner.close();
		} catch (FileNotFoundException e) {
            System.err.println("Error reading the file: " + sCsvFilePath +"\n\n" + e.getMessage()); 
        }
        //System.out.println("Entries - From Input CSV : ---------------------- Start --------------------------");
        for(JsonNode jsonNode1 : cSVJsonList) {
            //System.out.println(jsonNode1);
            //System.out.println("---------------------");
        }
        //System.out.println("Entries - From Input CSV : ---------------------- End --------------------------");
        return cSVJsonList;
    }

    public JsonNode convertCsv2Json(String csvRecord, String headerRecord) {
        // Convert CSV to JSON
        String trimmedCsv = csvRecord.substring(1, csvRecord.length() - 1);

        // Split by "," to extract values
        String[] values = trimmedCsv.split("\",\"");

        // Headers are simpler, there are no quotes - just split by commas
        String[] headers = headerRecord.split(",");

        if (headers.length != values.length) {
            throw new IllegalArgumentException("Headers and values arrays must have the same length.");
        }

        // Create new JSON node and populate values using content of headers as keys
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.createObjectNode();
        for (int i = 0; i < headers.length; i++) {
            ((ObjectNode) jsonNode).put(headers[i], values[i]);
        }
        return jsonNode;
    }
    public void publishJsonRecords (String inputJsonFile, String sTopicName){
        Properties props = StreamConfig.producerConfig();

        PublishRecord publisher = new PublishRecord (props);

        publisher.createTopic(sTopicName);
        File file = new File(inputJsonFile);
        String jsonInputData = "";
        try{
            jsonInputData = com.google.common.io.Files.asCharSource(file, Charsets.UTF_8).read();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        String jsonPubRecord = "{";
        jsonPubRecord = jsonPubRecord + "\"source\":\"securonix\",";
        jsonPubRecord = jsonPubRecord + "\"value\":" +  jsonInputData + "";
        jsonPubRecord = jsonPubRecord + "}";

        System.out.println(jsonPubRecord);
        System.out.println();
        System.out.println("Start - Publishing JSON record .... Topic Name:" + sTopicName);
        System.out.println();

        publisher.publish(sTopicName,"Message",jsonPubRecord );
        
        System.out.println();
        System.out.println("End - Publishing JSON record .... Topic Name:" + sTopicName);
        System.out.println();

    }
    public void publishECSEnrichedRecords (String inputJsonFile, String sTopicName){
        Properties props = StreamConfig.producerConfig();

        PublishRecord publisher = new PublishRecord (props);

        publisher.createTopic(sTopicName);
        File file = new File(inputJsonFile);
        String jsonInputData = "";
        try{
            jsonInputData = com.google.common.io.Files.asCharSource(file, Charsets.UTF_8).read();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println();
        System.out.println("Start - Publishing JSON record .... Topic Name:" + sTopicName);
        System.out.println();

        publisher.publish(sTopicName,"Message",jsonInputData );
        
        System.out.println();
        System.out.println("End - Publishing JSON record .... Topic Name:" + sTopicName);
        System.out.println();

    }
    public ArrayNode getInputJsonList (String inputJsonFile){
        ObjectMapper objMapper = new ObjectMapper();
        ArrayNode inputJsonRecords = objMapper.createArrayNode();
        try{

            File file = new File(inputJsonFile);
            String jsonInputData = "";

            jsonInputData = com.google.common.io.Files.asCharSource(file, Charsets.UTF_8).read();

            // Jackson main object
            ObjectMapper mapper = new ObjectMapper();

            // read the json strings and convert it into JsonNode
            JsonNode node = mapper.readTree(jsonInputData);
            inputJsonRecords.add(node);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return inputJsonRecords;
    }
    public ArrayNode getMappedJson2EcsRecords(ArrayNode csvJsonNodeList) {        
        ObjectMapper mapper = new ObjectMapper();
        ArrayNode mapedJson2EcsRecords = mapper.createArrayNode();

        for(JsonNode jsonNode1 : csvJsonNodeList) {
            mapedJson2EcsRecords.add(mapJson2Ecs(jsonNode1));
        }

        return mapedJson2EcsRecords;
    }
    public JsonNode mapJson2Ecs(JsonNode csvJsonNode) {
        Map<String, String> fieldNameMap = ECSMaps.securonix();
        ObjectMapper mapper = new ObjectMapper();

        JsonNode newNode = mapper.createObjectNode();

        try {
            for (Map.Entry<String, String> entry : fieldNameMap.entrySet()) {
                String newField = entry.getKey();
                String oldField = entry.getValue();
                ((ObjectNode) newNode).set(newField, csvJsonNode.get(oldField));
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println();
        System.out.println("Mapped JSON Output ---> Start");
        System.out.println();
        System.out.println(newNode);
        System.out.println();
        System.out.println("Mapped JSON Output ---> End");
        System.out.println();       
        return newNode; 
    }

    public ArrayList<String> consumeJsonFromTopic(String sTopicName){
        ArrayList<String> consumedRecs = new ArrayList<String>();
        Properties props = StreamConfig.consumerConfig();
        ConsumeRecord receiver = new ConsumeRecord(props,sTopicName);
        consumedRecs = receiver.consumeAll();   
        receiver.close();
        return consumedRecs;
    }

    public ArrayList<String> getJsonFromDataDir(String sJsonFileName) {
        String jsonStrFromFile = "";
        ArrayList<String> consumedFileRecs = new ArrayList<String>();

        String currentDir = System.getProperty("user.dir");
        //System.out.println("Current dir using System:" + currentDir);
        String inputJSONFile = SysConfig.sAppJsonDataDir + "/" + sJsonFileName;

        
        try {
			Scanner scanner = new Scanner(new File(inputJSONFile));

			while (scanner.hasNextLine()) {
				jsonStrFromFile += scanner.nextLine();
			}
            consumedFileRecs.add(jsonStrFromFile);
			scanner.close();
		} catch (Exception e) {
            System.err.println("Error reading the file: " + inputJSONFile +"\n\n" + e.getMessage()); 
        }

        return consumedFileRecs;
    }

    public void compareJSON (JsonNode mappedJSONECSNode, JsonNode topicJsonNode){
        try {
            ObjectMapper mapper1 = new ObjectMapper();
            ObjectMapper mapper2 = new ObjectMapper();
            String mappedJsonText = mapper1.writeValueAsString(mappedJSONECSNode);
            String sTopicJsonText = mapper2.writeValueAsString(topicJsonNode);
            
            // System.out.println();
            // System.out.println("--------------- INPUT CSV DATA -----------------------------");
            // System.out.println(mappedJsonText);
            // System.out.println();
            // System.out.println("--------------- JSON DATA FROM STREAM -----------------------------");
            // System.out.println(sTopicJsonText);
            System.out.println();
            System.out.println("====================== TEST RESULT ================================");
    
            boolean bJsonNodesMatching = mappedJSONECSNode.equals(topicJsonNode);
             
            if (bJsonNodesMatching) {
                System.out.println("************** PASSS *********");
            } else {
                System.err.println("@@@@@@@@@ FAIL @@@@@@@@@@@");
                diffJsonDisplay (mappedJsonText, sTopicJsonText);
            }

            System.out.println();
            System.out.println();

            Assert.assertTrue(bJsonNodesMatching,"Input Data and Topic output JSON data are mismatching. Please check the Test Report for more details ... ");
    
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void diffJsonDisplay (String leftJson, String rightJson){
        ObjectMapper mapper = new ObjectMapper();
        Type type = new TypeToken<Map<String, Object>>(){}.getType();
                       
        Gson gson = new Gson();

        try {
            Map<String, Object> leftMap = gson.fromJson(leftJson, type);
            Map<String, Object> rightMap = gson.fromJson(rightJson, type);

            MapDifference<String, Object> difference = Maps.difference(leftMap, rightMap);
            System.out.println();
            //System.err.println(leftJson);
            //System.err.println(rightJson);
            System.out.println();
            System.out.println();

            System.out.println("Entries - Missing in Stream output\n--------------------------");
            difference.entriesOnlyOnLeft().forEach((key, value) -> System.out.println(key + ": " + value));
            
            System.out.println("\n\nEntries - Found only in Stream output   \n--------------------------");
            difference.entriesOnlyOnRight().forEach((key, value) -> System.out.println(key + ": " + value));
            
            System.out.println("\n\nEntries - Differing between CSV and Stream output\n--------------------------");
            difference.entriesDiffering().forEach((key, value) -> System.out.println(key + ": " + value));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Map<String, Object> jsonFileToMap(String path) throws Exception {
    ObjectMapper mapper = new ObjectMapper();
    return mapper.readValue(new File(path), new TypeReference<Map<String, Object>>() {});
    }
}
