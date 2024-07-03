package ai.indygen.learn_exp;
import java.util.Map;
import java.util.HashMap;

import ai.indygen.utils.*;

public class FilePathFinder {
    public static void main(String[] args) throws Exception {
        System.out.println(System.getProperty("user.dir"));
        String ecsEnrichedFilePath = "/Users/prakash/IndyGen/IndyGen_Automation_Dev/app/TestData/JSON/enriched_ecs.json";
        Map<String,Object> enrichmap = new HashMap<String,Object>();
        enrichmap = JSONUtils.jsonFileToMap(ecsEnrichedFilePath);
        System.out.println(enrichmap);
        
    }    
}
