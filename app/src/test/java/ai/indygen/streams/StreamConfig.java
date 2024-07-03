package ai.indygen.streams;

import java.util.Properties;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.serialization.StringDeserializer;

public class StreamConfig {
    
    public static Properties producerConfig() {
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
        System.out.println("StreamConfig <> producerConfig | sBOOTSTRAP_SERVERS="+sBOOTSTRAP_SERVERS);
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println("StreamConfig <> producerConfig | sBOOTSTRAP_PORT="+sBOOTSTRAP_PORT);
        System.out.println();

        String sBOOTSTRAP_SERVERS_CONFIG = sBOOTSTRAP_SERVERS + ":" + sBOOTSTRAP_PORT;
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println("StreamConfig <> producerConfig | sBOOTSTRAP_SERVERS_CONFIG="+sBOOTSTRAP_SERVERS_CONFIG);
        System.out.println();
        System.out. println("----------------------------------------------------------------");
       Properties props = new Properties();

        props.put("key.serializer", StringSerializer.class.getName());
        props.put("value.serializer", StringSerializer.class.getName());
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "json-transformer");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, sBOOTSTRAP_SERVERS_CONFIG);
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        return props;
    }
    public static Properties producerConfig(String sHost, String sPort) {
        Properties props = new Properties();
        props.put("key.serializer", StringSerializer.class.getName());
        props.put("value.serializer", StringSerializer.class.getName());
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "json-transformer");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, sHost+":"+sPort);
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        return props;
    }

    public static Properties consumerConfig() {
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
        System.out.println("StreamConfig <> consumerConfig | sBOOTSTRAP_SERVERS="+sBOOTSTRAP_SERVERS);
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println("StreamConfig <> consumerConfig | sBOOTSTRAP_PORT="+sBOOTSTRAP_PORT);
        System.out.println();
        System.out.println();
        String sBOOTSTRAP_SERVERS_CONFIG = sBOOTSTRAP_SERVERS + ":" + sBOOTSTRAP_PORT;
        System.out.println("StreamConfig <> consumerConfig | sBOOTSTRAP_SERVERS_CONFIG="+sBOOTSTRAP_SERVERS_CONFIG);
        System.out.println();
        System.out. println("----------------------------------------------------------------");
        Properties props = new Properties();
        props.put("key.deserializer", StringDeserializer.class.getName());
        props.put("value.deserializer", StringDeserializer.class.getName());
        props.put("group.id", "firefox");
        props.put("auto.offset.reset", "earliest");
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "json-transformer");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, sBOOTSTRAP_SERVERS_CONFIG);
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        return props;
    }
    public static Properties consumerConfig(String sHost, String sPort) {
        Properties props = new Properties();
        props.put("key.deserializer", StringDeserializer.class.getName());
        props.put("value.deserializer", StringDeserializer.class.getName());
        props.put("group.id", "firefox");
        props.put("auto.offset.reset", "earliest");
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "json-transformer");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, sHost+":"+sPort);
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        return props;
    }
}