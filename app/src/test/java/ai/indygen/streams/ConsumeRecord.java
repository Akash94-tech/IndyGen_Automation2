package ai.indygen.streams;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import java.util.ArrayList;

import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

public class ConsumeRecord {

    Properties streamProps;
    KafkaConsumer<Object, Object> consumer;

    public  ConsumeRecord (Properties props){
        this.streamProps = props;
        this.consumer = new KafkaConsumer<>(props);
        this.consumer.subscribe(Collections.singletonList("publish-log"));
        this.consumer.seekToBeginning(consumer.assignment());
    }

    public  ConsumeRecord (Properties props,String sTopicName){
        this.streamProps = props;
        this.consumer = new KafkaConsumer<>(props);
        this.consumer.subscribe(Collections.singletonList(sTopicName));
        this.consumer.seekToBeginning(consumer.assignment());
    }

    public ConsumerRecords<Object, Object> consume() {
        ConsumerRecords<Object, Object> records = null;
        System.out.println();
        System.out.println();
        try {
            records = this.consumer.poll(Duration.ofSeconds(10));
            if (records.count() == 0) {
                System.out.println("No more records");
            } 
            else {
                records.forEach(record -> {
                    int icount = 0;
                    icount++;
                    System.out.println("Comsumed record: #" + icount);
                    System.out.printf("Consumed record: \n%s\n", record.value());
                });
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return records;
    }

    public ArrayList<String> consumeAll() {
        ArrayList<String> listOfRecords = new ArrayList<String>();

        ConsumerRecords<Object, Object> records = null;

        try {
            while (true) {
                records = consume();
                if (records.count() == 0) {
                    break;
                }
                else {
                    records.forEach(record -> {
                        listOfRecords.add(record.value().toString());
                    });  
                }
            }
        } catch(Exception e) {
            System.out.println(e);
        }

        //System.out.println(listOfRecords);
        return listOfRecords;
    }

    public void close(){
        this.consumer.close();
    }

    public static void main(String[] args) {
        
        Properties props = StreamConfig.consumerConfig();
        ConsumeRecord receiver = new ConsumeRecord(props);
        receiver.consumeAll();

   /* 
        try {
            while (true) {
                ConsumerRecords<Object, Object> records = receiver.consume();
                if (records.count() == 0) {
                    break;
                }
            }
        } catch(Exception e) {
            System.out.println(e);
        }
*/
        receiver.close();
    }
}