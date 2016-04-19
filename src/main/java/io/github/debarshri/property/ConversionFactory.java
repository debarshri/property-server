package io.github.debarshri.property;

import io.github.debarshri.Extensions;
import org.json.JSONObject;
import org.yaml.snakeyaml.Yaml;

import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class ConversionFactory {
    public static <T> T fromTo(String configuration, String extension, String as) {

        if(as.equalsIgnoreCase(extension))
        {
            return (T)configuration;
        }
        else if(as.equalsIgnoreCase(Extensions.JSON) && extension.equalsIgnoreCase(Extensions.YAML))
        {
            Yaml yaml = new Yaml();
            Map<String,Object> map= (Map<String, Object>) yaml.load(configuration);

            JSONObject jsonObject=new JSONObject(map);
            return (T)jsonObject.toString();
        }
        else if(as.equalsIgnoreCase(Extensions.JSON) && extension.equalsIgnoreCase(Extensions.PROPERTIES))
        {
            Yaml yaml = new Yaml();
            Map<String,Object> map= (Map<String, Object>) yaml.load(configuration);
            JSONObject jsonObject=new JSONObject(map);
            return (T)jsonObject.toString();
        }
        else if(as.toUpperCase().equals("java"))
        {
            Yaml yaml = new Yaml();
            Map<String,Object> map= (Map<String, Object>) yaml.load(configuration);
            return (T)mapToProperties(map);
        }

        return (T)"Property mapping does not exist";
    }

    public static Properties mapToProperties(Map<String, Object> map) {
        Properties p = new Properties();
        Set<Map.Entry<String,Object>> set = map.entrySet();
        for (Map.Entry<String,Object> entry : set) {
            p.put(entry.getKey(), entry.getValue());
        }
        return p;
    }
}
