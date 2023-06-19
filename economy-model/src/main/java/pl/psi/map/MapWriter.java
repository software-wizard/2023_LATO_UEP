package pl.psi.map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import pl.psi.Point;
import pl.psi.mapElements.MapElement;
import pl.psi.mapElements.StaticElement;

import java.util.HashMap;
import java.util.Map;

import java.io.FileWriter;
import java.io.IOException;

public class MapWriter {
    public static void main(String[] args) {
//        BiMap<Point, MapElement> mapElements = HashBiMap.create();

//        Gson gson = new GsonBuilder().create();
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(MapElement.class, new MapElementAdapter())
                .create();

        Map<Point, MapElement> map = new HashMap<>();
        map.put(new Point(10, 20), new StaticElement());

        String json = gson.toJson(map);

        try (FileWriter writer = new FileWriter("economy-model/src/main/resources/maps/testMap.json")) {
            writer.write(json);
        } catch (IOException e) {

        }
    }
}
