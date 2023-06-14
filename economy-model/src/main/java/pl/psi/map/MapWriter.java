package pl.psi.map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import pl.psi.Point;
import pl.psi.mapElements.MagicWell;
import pl.psi.mapElements.MapElement;
import pl.psi.mapElements.StaticElement;

import java.io.File;
import java.io.IOException;

public class MapWriter {
    public static void main(String[] args) {
        BiMap<Point, MapElement> mapElements = HashBiMap.create();
        mapElements.put(new Point(8, 2), new StaticElement());
        mapElements.put(new Point(8, 3), new StaticElement());
        mapElements.put(new Point(9, 3), new StaticElement());
        mapElements.put(new Point(9, 2), new StaticElement());
//        mapElements.put(new Point(12, 2), new MagicWell());

        ObjectMapper objectMapper = new ObjectMapper();

        try {
            objectMapper.writeValue(new File("economy-model/src/main/resources/maps/testMap.json"), mapElements);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
