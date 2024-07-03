package ai.indygen.produce_consume_demo;

import java.io.IOException;
import java.util.Collections;
import java.util.Optional;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.Map;
import java.util.HashMap;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.errors.TopicExistsException;
import java.io.File;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Charsets;
 

public class JsonFileProducer {
    /**
     * Creates a new topic in Redpanda
     * @param topic
     * @param config
     */
    public static void createTopic(final String topic, final Properties config) {
        final NewTopic newTopic = new NewTopic(topic, Optional.empty(), Optional.empty());

        try (final AdminClient adminClient = AdminClient.create(config)) {
            adminClient.createTopics(Collections.singletonList(newTopic)).all().get();
        } catch (final InterruptedException | ExecutionException e) {
            System.out.println(e.getMessage());
            if (!(e.getCause() instanceof TopicExistsException)) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void main(final String[] args) throws IOException {
        Properties props = ClientConfig.producerConfig();

        createTopic("publish-log", props);
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(props);

        File file = new File("/Users/prakash/IndyGen/IndyGen_Automation/app/TestData/JSON/Input_events_logs.json");
    String jsonInputData = com.google.common.io.Files.asCharSource(file, Charsets.UTF_8).read();

        String jsonPubRecord = "{";
        jsonPubRecord = jsonPubRecord + "\"source\":\"securonix\",";
        jsonPubRecord = jsonPubRecord + "\"value\":" +  jsonInputData + "";
        jsonPubRecord = jsonPubRecord + "}";


        ProducerRecord<String, String> record = new ProducerRecord<>(
            "publish-log", "",jsonPubRecord);
                
            producer.send(record, new Callback() {
                @Override
                public void onCompletion(RecordMetadata metadata, Exception exception) {
                    if (exception != null) {
                        exception.printStackTrace();
                    } else {
                        System.out.printf("Sent message to topic: %s, partition: %d, offset: %d%n", 
                            metadata.topic(), metadata.partition(), metadata.offset());
                    }
                }
            });

        producer.flush();
        producer.close();
    }
}