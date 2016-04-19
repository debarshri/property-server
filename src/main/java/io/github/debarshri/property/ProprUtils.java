package io.github.debarshri.property;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ProprUtils {
    public static Map<String,String> parse(List<String> strings) {
        Map<String, String> keyValue = new HashMap<>();

        for(String line : strings)
        {
            if(line.charAt(0) != '#')
             keyValue.put(line.split("=")[0], line.split("=")[1]);
        }

        return keyValue;
    }

    public static Properties toProperties(Map<String, String> parse) {

        Properties properties = new Properties();
        properties.putAll(parse);

        return properties;
    }


    public static Map<String, String> search(List<String> properties, String term) {
       Map<String,String> collect = properties.stream()
                .filter(line -> line.split("=")[0].contains(term))
                .map( line -> {
                    Map<String, String> stringStringHashMap = new HashMap<>();
                    stringStringHashMap.put(line.split("=")[0],line.split("=")[1]);
                    return stringStringHashMap;
                })
                .reduce((stringStringMap, stringStringMap2) -> {
                    stringStringMap.putAll(stringStringMap2);
                    return stringStringMap;
                }).get();


        return collect;
    }
}
