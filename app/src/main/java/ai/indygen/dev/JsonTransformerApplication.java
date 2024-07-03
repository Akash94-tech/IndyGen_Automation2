package ai.indygen.dev;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.Produced;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;


import java.util.Properties;
import java.util.Collections;
import java.util.Map;

public class JsonTransformerApplication {
    // Objective of this app is to normaize the incoming data into ECS compatible json.
    // We might receive data in various formats based on vendors
    // which needs to be converted into a standard format to be processed further.
    public static void main(String[] args) {
        // Set connection parameters
        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "json-transformer");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:19092");
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        // Instantiate stream builder
        StreamsBuilder builder = new StreamsBuilder();

        // Consume topic -> Transform data -> Publish to new topic
        builder.stream("publish-log", Consumed.with(Serdes.String(), Serdes.String()))
                .mapValues(JsonTransformerApplication::transformInput)
                .to("publish-log-ecs", Produced.with(Serdes.String(), Serdes.String()));

        KafkaStreams streams = new KafkaStreams(builder.build(), props);
        System.out.println("Listening to streams...");
        streams.start();

        Runtime.getRuntime().addShutdownHook(new Thread(streams::close));
    }

    private static Map<String, String> getECSMap(String source) {
        // This function accepts the source string and returns corresponding map for ECS transformation.
        switch (source) {
            case "securonix":
                return ECSMaps.securonix();
            default:
                // Return empty map if source is not recognized
                return Collections.emptyMap();
        }
    }

    private static JsonNode csv2json(String csv, String headerString) {
        // Convert CSV to JSON

        // Split each row into an array
        // Input format is something like: "UNKNOWN","VALUE1","VALUE2"
        // To split this into array we remove trailing and leading double quotes
        // which results the value to change to UNKNOWN","VALUE1","VALUE2
        // We can now split the string by "," (quote comma quote) -> UNKNOWN VALUE1 VALUE2

        // Remove trailing and leading double quotes
        System.out.println("------------ csv ----------------");
        System.out.println(csv);
        System.out.println("------------ headerString ----------------");
        System.out.println(headerString);
        
        String trimmedCsv = csv.substring(1, csv.length() - 1);
        // Split by "," to extract values
        String[] values = trimmedCsv.split("\",\"");
        // Headers are simpler, there are no quotes - just split by commas
        String[] headers = headerString.split(",");
        System.out.println("headers.length=" + headers.length + "   values.lenth="+values.length);
        if (headers.length != values.length) {
            throw new IllegalArgumentException("Headers and values arrays must have the same length.");
        }

        // Create new JSON node and populate values using content of headers as keys
        ObjectMapper mapper = new ObjectMapper();
        JsonNode json = mapper.createObjectNode();
        for (int i = 0; i < headers.length; i++) {
            ((ObjectNode) json).put(headers[i], values[i]);
        }
        return json;
    }

    // Method to transform JSON
    private static String transformInput(String json) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(json);
            
            String source = rootNode.get("source").asText();
            if (source.contains("_csv")) {
                // If source contains "_csv" then convert it to json, and obtain source
                source = source.split("_")[0];
                // Pass the row and headers to the function
                ObjectMapper objectMapper = new ObjectMapper();
                String carAsString = objectMapper.writeValueAsString(rootNode);
                System.out.println("ROOT NODE");
                System.out.println(carAsString);
                rootNode = csv2json(
                    rootNode.get("value").asText(),
                    rootNode.get("headers").asText()
                );
            } else {
                // If not CSV, we expect the value to be a JSON
                rootNode = rootNode.get("value");
            }

            Map<String, String> fieldNameMap = getECSMap(source);
            
            // Create a new JSON with ECS fields to be published to new topic.
            JsonNode newNode = mapper.createObjectNode();
            for (Map.Entry<String, String> entry : fieldNameMap.entrySet()) {
                String newField = entry.getKey();
                String oldField = entry.getValue();
                ((ObjectNode) newNode).set(newField, rootNode.get(oldField));
            }

            // Convert back to JSON string before returning
            return mapper.writeValueAsString(newNode);
        } catch (Exception e){
            System.err.println(e);
            e.printStackTrace();
            return null;
        }
    }
}
