import sys
import json
from kafka import KafkaProducer

producer = KafkaProducer(bootstrap_servers='localhost:19092')

message_dict = {
    "accountname": "UNKNOWN",
    "transactionstring1": "Checkpoint Next Generation Firewall Event",
    "ipaddress": "162.243.147.25",
    "deviceaction": "Accept",
    "deviceinboundinterface": "eth5",
    "sourceaddress": "162.243.147.25",
    "sourceport": "54903",
    "deviceaddress": "10.83.152.36",
    "devicehostname": "chkmgmt-hisysmc",
    "resourcegroupid": "4",
    "resourcegroupname": "HSI_Checkpont_firewall",
    "devicecustomstring1": "Standard",
    "devicedirection": "inbound",
    "devicefacility": "VPN-1 & FireWall-1",
    "resourcename": "HSI_Checkpont_firewall",
    "resourcetype": "Check Point Next Generation Firewall",
    "destinationaddress": "122.186.40.52",
    "destinationport": "465",
    "transportprotocol": "TCP",
    "customnumber1": "2",
    "customnumber10": "65536",
    "customnumber6": "6",
    "customstring55": "geo_protection",
    "customstring74": "HISYSMC-FW2",
    "invalid": "false",
    "categorybehavior": "Connection Request",
    "categoryobject": "Network",
    "categoryoutcome": "Success",
    "categoryseverity": "0",
    "customstring15": "",
    "customstring57": "",
    "customstring6": "",
    "customstring63": "",
    "customstring67": "",
    "customstring68": "",
    "customstring69": "",
    "freq": "",
    "transactionstring4": "",
    "eventcity": "San Francisco",
    "eventcountry": "United States",
    "eventlatitude": "37.7308",
    "eventlongitude": "-122.3838",
    "eventregion": "North America",
    "category": "ALERT",
    "policyid": "1755",
    "policyname": "Hitachi Malicious IP detection_HSI",
    "riskthreatname": "Activity from malicious address",
    "violator": "Activityip",
    "criticality": "Medium",
    "tenantname": "Hitachi Systems India Pvt Ltd",
    "SiemId": "{0x65e4c1fc,0x4,0x898530a,0x3fffcb4a}",
    "eventid": "15860fe9-1735-4d55-82c7-29bd2f59b493",
    "formatteddate": "",
    "generationtime": "2024-03-04 00:04:31",
    "publishedtime": "2024-03-04 00:01:00",
    "receivedtime": "2024-03-04 00:01:02",
    "risktypeid": "3588",
    "indexedat": "2024-03-04 00:06:01",
    "rawevent": "Mar  4 00:00:23 10.83.152.8 1 2024-03-03T18:29:35Z chkmgmt-hisysmc CheckPoint 52042 - [action:Accept; flags:393216; ifdir:inbound; ifname:eth5; logid:65536; loguid:{0x65e4c1fc,0x4,0x898530a,0x3fffcb4a}; origin:10.83.152.36; originsicname:CN=HISYSMC-FW2,O=chkmgmt-hisysmc.microclinic.in.ynni43; sequencenum:2; time:1709490575; version:5; __policy_id_tag:product=VPN-1 & FireWall-1[db_tag={1E53ABA2-E497-2049-8566-3F2EB71B4FD5};mgmt=chkmgmt-hisysmc;date=1709376413;policy_name=Standard\\]; dst:122.186.40.52; dst_country:IND; inspection_information:Geo-location inbound enforcement; inspection_profile:Geo_settings_upgraded_from_Recommended_Protection; product:VPN-1 & FireWall-1; protection_type:geo_protection; proto:6; s_port:54903; service:465; src:162.243.147.25; src_country:USA]",
    "week": "10",
    "month": "3",
    "hour": "23",
    "year": "2024",
    "dayofweek": "1",
    "categorizedtime": "Evening",
    "dayofyear": "63",
    "dayofmonth": "3",
    "eventtime": "2024-03-03 23:59:35",
}

message_csv = '"UNKNOWN","Checkpoint Next Generation Firewall Event","162.243.147.25","Accept","eth5","162.243.147.25","54903","10.83.152.36","chkmgmt-hisysmc","4","HSI_Checkpont_firewall","Standard","inbound","VPN-1 & FireWall-1","HSI_Checkpont_firewall","Check Point Next Generation Firewall","122.186.40.52","465","TCP","2","65536","6","geo_protection","HISYSMC-FW2","false","Connection Request","Network","Success","0","","","","","","","","","","San Francisco","United States","37.7308","-122.3838","North America","ALERT","1755","Hitachi Malicious IP detection_HSI","Activity from malicious address","Activityip","Medium","Hitachi Systems India Pvt Ltd","{0x65e4c1fc,0x4,0x898530a,0x3fffcb4a}","15860fe9-1735-4d55-82c7-29bd2f59b493","","2024-03-04 00:04:31","2024-03-04 00:01:00","2024-03-04 00:01:02","3588","2024-03-04 00:06:01","Mar  4 00:00:23 10.83.152.8 1 2024-03-03T18:29:35Z chkmgmt-hisysmc CheckPoint 52042 - [action:""Accept""; flags:""393216""; ifdir:""inbound""; ifname:""eth5""; logid:""65536""; loguid:""{0x65e4c1fc,0x4,0x898530a,0x3fffcb4a}""; origin:""10.83.152.36""; originsicname:""CN=HISYSMC-FW2,O=chkmgmt-hisysmc.microclinic.in.ynni43""; sequencenum:""2""; time:""1709490575""; version:""5""; __policy_id_tag:""product=VPN-1 & FireWall-1[db_tag={1E53ABA2-E497-2049-8566-3F2EB71B4FD5};mgmt=chkmgmt-hisysmc;date=1709376413;policy_name=Standard\]""; dst:""122.186.40.52""; dst_country:""IND""; inspection_information:""Geo-location inbound enforcement""; inspection_profile:""Geo_settings_upgraded_from_Recommended_Protection""; product:""VPN-1 & FireWall-1""; protection_type:""geo_protection""; proto:""6""; s_port:""54903""; service:""465""; src:""162.243.147.25""; src_country:""USA""]","10","3","23","2024","1","Evening","63","3","2024-03-03 23:59:35"'
# headers = ["accountname","transactionstring1","ipaddress","deviceaction","deviceinboundinterface","sourceaddress","sourceport","deviceaddress","devicehostname","resourcegroupid","resourcegroupname","devicecustomstring1","devicedirection","devicefacility","resourcename","resourcetype","destinationaddress","destinationport","transportprotocol","customnumber1","customnumber10","customnumber6","customstring55","customstring74","invalid","categorybehavior","categoryobject","categoryoutcome","categoryseverity","customstring15","customstring57","customstring6","customstring63","customstring67","customstring68","customstring69","freq","transactionstring4","eventcity","eventcountry","eventlatitude","eventlongitude","eventregion","category","policyid","policyname","riskthreatname","violator","criticality","tenantname","SiemId","eventid","formatteddate","generationtime","publishedtime","receivedtime","risktypeid","indexedat","rawevent","week","month","hour","year","dayofweek","categorizedtime","dayofyear","dayofmonth","eventtime"]
headers = "accountname,transactionstring1,ipaddress,deviceaction,deviceinboundinterface,sourceaddress,sourceport,deviceaddress,devicehostname,resourcegroupid,resourcegroupname,devicecustomstring1,devicedirection,devicefacility,resourcename,resourcetype,destinationaddress,destinationport,transportprotocol,customnumber1,customnumber10,customnumber6,customstring55,customstring74,invalid,categorybehavior,categoryobject,categoryoutcome,categoryseverity,customstring15,customstring57,customstring6,customstring63,customstring67,customstring68,customstring69,freq,transactionstring4,eventcity,eventcountry,eventlatitude,eventlongitude,eventregion,category,policyid,policyname,riskthreatname,violator,criticality,tenantname,SiemId,eventid,formatteddate,generationtime,publishedtime,receivedtime,risktypeid,indexedat,rawevent,week,month,hour,year,dayofweek,categorizedtime,dayofyear,dayofmonth,eventtime"
# headers = '["accountname","transactionstring1","ipaddress","deviceaction","deviceinboundinterface","sourceaddress","sourceport","deviceaddress","devicehostname","resourcegroupid","resourcegroupname","devicecustomstring1","devicedirection","devicefacility","resourcename","resourcetype","destinationaddress","destinationport","transportprotocol","customnumber1","customnumber10","customnumber6","customstring55","customstring74","invalid","categorybehavior","categoryobject","categoryoutcome","categoryseverity","customstring15","customstring57","customstring6","customstring63","customstring67","customstring68","customstring69","freq","transactionstring4","eventcity","eventcountry","eventlatitude","eventlongitude","eventregion","category","policyid","policyname","riskthreatname","violator","criticality","tenantname","SiemId","eventid","formatteddate","generationtime","publishedtime","receivedtime","risktypeid","indexedat","rawevent","week","month","hour","year","dayofweek","categorizedtime","dayofyear","dayofmonth","eventtime"]'
# message_csv = '"UNKNOWN","Checkpoint Next Generation Firewall Event","10.83.151.50","Accept","eth4","10.83.151.50","59831","10.83.152.35","chkmgmt-hisysmc","4","HSI_Checkpont_firewall","Standard","inbound","VPN-1 & FireWall-1","HSI_Checkpont_firewall","Check Point Next Generation Firewall","35.186.224.25","443","TCP","22","65536","6","geo_protection","HISYSMC-FW1","false","Connection Request","Network","Success","0","","","","","","","","","","","","","","","Command And Control","1812","Traffic to known Malicious IP Address - TPI - NGFW-NGF","Application Layer Protocol","Activityip","Low","Hitachi Systems India Pvt Ltd","{0x65e49bbd,0x18,0x898530a,0x3fffcb4a}","95a94846-2b46-4028-b43d-5406d2914f18","","2024-03-03 21:30:01","2024-03-03 21:18:00","2024-03-03 21:18:02","3691","2024-03-03 21:33:03","Mar  3 21:17:13 10.83.152.8 1 2024-03-03T15:46:38Z chkmgmt-hisysmc CheckPoint 52042 - [action:""Accept""; flags:""393216""; ifdir:""inbound""; ifname:""eth4""; logid:""65536""; loguid:""{0x65e49bbd,0x18,0x898530a,0x3fffcb4a}""; origin:""10.83.152.35""; originsicname:""CN=HISYSMC-FW1,O=chkmgmt-hisysmc.microclinic.in.ynni43""; sequencenum:""22""; time:""1709480798""; version:""5""; __policy_id_tag:""product=VPN-1 & FireWall-1[db_tag={1E53ABA2-E497-2049-8566-3F2EB71B4FD5};mgmt=chkmgmt-hisysmc;date=1709376413;policy_name=Standard]""; dst:""35.186.224.25""; dst_country:""USA""; inspection_information:""Geo-location outbound enforcement""; inspection_profile:""Geo_settings_upgraded_from_Recommended_Protection""; product:""VPN-1 & FireWall-1""; protection_type:""geo_protection""; proto:""6""; s_port:""59831""; service:""443""; src:""10.83.151.50""; src_country:""Internal""]","10","3","21","2024","1","Afternoon","63","3","2024-03-03 21:16:38"'

diction = {
    'source': 'securonix_csv',
    'value': message_csv,
    'headers': headers
}
# breakpoint()
message = json.dumps(diction)
message = bytes(message, 'utf-8')

message = bytes(json.dumps({
    'source': 'securonix_csv',
    'value': message_csv,
    'headers': headers
}), 'utf-8')

# message1 = bytes(json.dumps({
#     'source': 'securonix',
#     'value': message_dict
# }), 'utf-8')

for _ in range(1):
    producer.send('publish-log', message)
    # producer.send('publish-log', message1)
    print (message)

producer.flush()
producer.close()
