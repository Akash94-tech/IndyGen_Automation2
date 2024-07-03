package ai.indygen.utils;

import java.util.Map;

public class EnrichDBJSONMaps {
    
    public static Map<String, String> getIPMap() {
        return Map.ofEntries(
            Map.entry("subdivision_2_name","geo.subdivision_2_name"),
            Map.entry("registered_country_geoname_id","user.name.registered_country_geoname_id"),
            Map.entry("represented_country_geoname_id","user.name.represented_country_geoname_id"),
            Map.entry("continent_code","geo.continent_code"),
            Map.entry("subdivision_1_iso_code","geo.subdivision_1_iso_code"),
            Map.entry("accuracy_radius","geo.location.accuracy_radius"),
            Map.entry("geoname_id","user.name.registered_country_geoname_id"),
            Map.entry("subdivision_2_iso_code","geo.subdivision_2_iso_code"),
            Map.entry("locale_code","user.locale_code"),
            Map.entry("country_iso_code","geo.country_iso_code"),
            Map.entry("metro_code","geo.metro_code"),
            Map.entry("is_in_european_union","geo.is_in_european_union"),
            Map.entry("is_satellite_provider","geo.is_satellite_provider"),
            Map.entry("postal_code","geo.postal_code"),
            Map.entry("subdivision_1_name","geo.subdivision_1_name")            
        );
    }
}
