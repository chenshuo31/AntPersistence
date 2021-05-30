package org.antpersistence;

import org.antpersistence.api.AntPersistence;
import org.antpersistence.db.DataStorage;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        DataStorage dataStorage = AntPersistence.basicConfig("./data/test", "field1", "field2", "field3");
        Map<String, String> maps = new HashMap<>();
        maps.put("field1", "1");
        maps.put("field2", "2");
        maps.put("field3", "3");
        AntPersistence.insert(maps, dataStorage);
    }
}
