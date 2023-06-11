package pl.psi.map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import pl.psi.mapElements.MapElement;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Map;

public class MapReader {
    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();
        BiMap<Point, MapElement> mapElements = HashBiMap.create();

        try {
            File file = new File("economy-model/src/main/resources/maps/testMap.json");
            Map<String, MapElement> jsonMap = objectMapper.readValue(file, new TypeReference<Map<String, MapElement>>() {});

//            for (Map.Entry<String, StaticElement> entry : jsonMap.entrySet()) {
//                Point key = parsePoint(entry.getKey());
//                StaticElement value = entry.getValue();
//                mapElements.put(key, value);
//            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
