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

import com.fasterxml.jackson.databind.ObjectMapper;
 

public class JsonMapProducer {
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

        Map<String, String> messageDict = new HashMap<>();
        messageDict.put("accountname", "UNKNOWN");
        messageDict.put("transactionstring1", "Checkpoint Next Generation Firewall Event");
        messageDict.put("ipaddress", "162.243.147.25");
        messageDict.put("deviceaction", "Accept");
        messageDict.put("deviceinboundinterface", "eth5");
        messageDict.put("sourceaddress", "162.243.147.25");
        messageDict.put("sourceport", "54903");
        messageDict.put("deviceaddress", "10.83.152.36");
        messageDict.put("devicehostname", "chkmgmt-hisysmc");
        messageDict.put("resourcegroupid", "4");
        messageDict.put("resourcegroupname", "HSI_Checkpont_firewall");
        messageDict.put("devicecustomstring1", "Standard");
        messageDict.put("devicedirection", "inbound");
        messageDict.put("devicefacility", "VPN-1 & FireWall-1");
        messageDict.put("resourcename", "HSI_Checkpont_firewall");
        messageDict.put("resourcetype", "Check Point Next Generation Firewall");
        messageDict.put("destinationaddress", "122.186.40.52");
        messageDict.put("destinationport", "465");
        messageDict.put("transportprotocol", "TCP");
        messageDict.put("customnumber1", "2");
        messageDict.put("customnumber10", "65536");
        messageDict.put("customnumber6", "6");
        messageDict.put("customstring55", "geo_protection");
        messageDict.put("customstring74", "HISYSMC-FW2");
        messageDict.put("invalid", "false");
        messageDict.put("categorybehavior", "Connection Request");
        messageDict.put("categoryobject", "Network");
        messageDict.put("categoryoutcome", "Success");
        messageDict.put("categoryseverity", "0");
        messageDict.put("customstring15", "");
        messageDict.put("customstring57", "");
        messageDict.put("customstring6", "");
        messageDict.put("customstring63", "");
        messageDict.put("customstring67", "");
        messageDict.put("customstring68", "");
        messageDict.put("customstring69", "");
        messageDict.put("freq", "");
        messageDict.put("transactionstring4", "");
        messageDict.put("eventcity", "San Francisco");
        messageDict.put("eventcountry", "United States");
        messageDict.put("eventlatitude", "37.7308");
        messageDict.put("eventlongitude", "-122.3838");
        messageDict.put("eventregion", "North America");
        messageDict.put("category", "ALERT");
        messageDict.put("policyid", "1755");
        messageDict.put("policyname", "Hitachi Malicious IP detection_HSI");
        messageDict.put("riskthreatname", "Activity from malicious address");
        messageDict.put("violator", "Activityip");
        messageDict.put("criticality", "Medium");
        messageDict.put("tenantname", "Hitachi Systems India Pvt Ltd");
        messageDict.put("SiemId", "{0x65e4c1fc,0x4,0x898530a,0x3fffcb4a}");
        messageDict.put("eventid", "15860fe9-1735-4d55-82c7-29bd2f59b493");
        messageDict.put("formatteddate", "");
        messageDict.put("generationtime", "2024-03-04 00:04:31");
        messageDict.put("publishedtime", "2024-03-04 00:01:00");
        messageDict.put("receivedtime", "2024-03-04 00:01:02");
        messageDict.put("risktypeid", "3588");
        messageDict.put("indexedat", "2024-03-04 00:06:01");
        messageDict.put("rawevent", "Mar  4 00:00:23 10.83.152.8 1 2024-03-03T18:29:35Z chkmgmt-hisysmc CheckPoint 52042 - [action:Accept; flags:393216; ifdir:inbound; ifname:eth5; logid:65536; loguid:{0x65e4c1fc,0x4,0x898530a,0x3fffcb4a}; origin:10.83.152.36; originsicname:CN=HISYSMC-FW2,O=chkmgmt-hisysmc.microclinic.in.ynni43; sequencenum:2; time:1709490575; version:5; __policy_id_tag:product=VPN-1 & FireWall-1[db_tag={1E53ABA2-E497-2049-8566-3F2EB71B4FD5};mgmt=chkmgmt-hisysmc;date=1709376413;policy_name=Standard\\]; dst:122.186.40.52; dst_country:IND; inspection_information:Geo-location inbound enforcement; inspection_profile:Geo_settings_upgraded_from_Recommended_Protection; product:VPN-1 & FireWall-1; protection_type:geo_protection; proto:6; s_port:54903; service:465; src:162.243.147.25; src_country:USA]");
        messageDict.put("week", "10");
        messageDict.put("month", "3");
        messageDict.put("hour", "23");
        messageDict.put("year", "2024");
        messageDict.put("dayofweek", "1");
        messageDict.put("categorizedtime", "Evening");
        messageDict.put("dayofyear", "63");
        messageDict.put("dayofmonth", "3");
        messageDict.put("eventtime", "2024-03-03 23:59:35"); 
        messageDict.put("rawevent", "Mar  4 00:00:23 10.83.152.8 1 2024-03-03T18:29:35Z chkmgmt-hisysmc CheckPoint 52042 - [action:Accept; flags:393216; ifdir:inbound; ifname:eth5; logid:65536; loguid:{0x65e4c1fc,0x4,0x898530a,0x3fffcb4a}; origin:10.83.152.36; originsicname:CN=HISYSMC-FW2,O=chkmgmt-hisysmc.microclinic.in.ynni43; sequencenum:2; time:1709490575; version:5; __policy_id_tag:product=VPN-1 & FireWall-1[db_tag={1E53ABA2-E497-2049-8566-3F2EB71B4FD5};mgmt=chkmgmt-hisysmc;date=1709376413;policy_name=Standard\\]; dst:122.186.40.52; dst_country:IND; inspection_information:Geo-location inbound enforcement; inspection_profile:Geo_settings_upgraded_from_Recommended_Protection; product:VPN-1 & FireWall-1; protection_type:geo_protection; proto:6; s_port:54903; service:465; src:162.243.147.25; src_country:USA]");

    ObjectMapper objectMapper = new ObjectMapper();
    String jacksonData = objectMapper.writeValueAsString(messageDict);

        String jsonPubRecord = "{";
        jsonPubRecord = jsonPubRecord + "\"source\":\"securonix\",";
        jsonPubRecord = jsonPubRecord + "\"value\":" +  jacksonData + "";
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