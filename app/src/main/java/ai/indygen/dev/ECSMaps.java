// This file contains mapping between ECS fields and log fields from each vendor
// Ex: accountname field in securonix file maps to user.name of ECSchema
// The number of entries and keys with each vendor is expected to be the same with different values
package ai.indygen.dev;

import java.util.Map;

public class ECSMaps {
    
    public static Map<String, String> securonix() {
        return Map.ofEntries(
            Map.entry("user.name", "accountname"),
            Map.entry("source.ip", "ipaddress"),
            Map.entry("event.action", "deviceaction"),
            Map.entry("network.iana_number", "deviceinboundinterface"),
            Map.entry("source.port", "sourceport"),
            Map.entry("host.ip", "deviceaddress"),
            Map.entry("host.name", "devicehostname"),
            Map.entry("cloud.resource.id", "resourcegroupid"),
            Map.entry("network.direction", "devicedirection"),
            Map.entry("cloud.resource.name", "resourcename"),
            Map.entry("cloud.resource.type", "resourcetype"),
            Map.entry("server.address", "destinationaddress"),
            Map.entry("server.port", "destinationport"),
            Map.entry("network.transport", "transportprotocol"),
            Map.entry("event.outcome", "categoryoutcome"),
            Map.entry("event.severity", "categoryseverity"),
            Map.entry("geo.city_name", "eventcity"),
            Map.entry("geo.country_name", "eventcountry"),
            Map.entry("geo.location.lat", "eventlatitude"),
            Map.entry("geo.location.lon", "eventlongitude"),
            Map.entry("geo.region_name", "eventregion"),
            Map.entry("event.category", "category"),
            Map.entry("rule.id", "policyid"),
            Map.entry("rule.name", "policyname"),
            Map.entry("threat.name", "riskthreatname"),
            Map.entry("cloud.account.name", "tenantname"),
            Map.entry("label.siem_id", "SiemId"),
            Map.entry("event.id", "eventid"),
            Map.entry("event.created", "generationtime"),
            Map.entry("event.received", "receivedtime"),
            Map.entry("threat.type", "risktypeid"),
            Map.entry("event.ingested", "indexedat"),
            Map.entry("event.original", "rawevent"),
            Map.entry("event.start", "eventtime"),
            Map.entry("event.start.week", "week"),
            Map.entry("event.start.month", "month"),
            Map.entry("event.start.hour", "hour"),
            Map.entry("event.start.year", "year"),
            Map.entry("event.start.day_of_week", "dayofweek"),
            Map.entry("event.start.day_of_year", "dayofyear"),
            Map.entry("event.start.day_of_month", "dayofmonth")
        );
    }
}
