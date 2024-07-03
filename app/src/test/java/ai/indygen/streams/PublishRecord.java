package ai.indygen.streams;

import java.io.IOException;

import java.util.Properties;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;


public class PublishRecord {

    Properties streamProps;
    KafkaProducer<String, String> producer;


    public  PublishRecord (Properties props){
        this.streamProps = props;
        this.producer = new KafkaProducer<String, String>(props);;
    }

    public void createTopic (String sPubTopicName){
        CreateTopic.createTopic(sPubTopicName, this.streamProps);
    }

    public void publish(String sTopicName, String sTopicMsgKey, String sTopicMsgVal){

        ProducerRecord<String, String> pubRecord = new ProducerRecord<>(sTopicName, sTopicMsgKey, sTopicMsgVal);
        this.producer.send(pubRecord, new Callback() {
            @Override
            public void onCompletion(RecordMetadata metadata, Exception exception) {
                if (exception != null) {
                    exception.printStackTrace();

                } else {
                    System.out.println();
                    System.out.printf("Published message to Topic: %s, Partition: %d, Offset: %d%n", 
                        metadata.topic(), metadata.partition(), metadata.offset());
                    System.out.println();
                }
            }
        });
    }

    public void close(){
        this.producer.flush();
        this.producer.close();
    }

    public static void main(final String[] args) throws IOException {
        Properties props = StreamConfig.producerConfig();
        String sTopicName = "publish-log";

        PublishRecord publisher = new PublishRecord (props);

        publisher.createTopic(sTopicName);

        publisher.publish(sTopicName,"Key:", "Value");

        publisher.close();
    }
}