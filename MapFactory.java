import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.TreeMap;
import java.util.Map;

public class MapFactory<K, V> {
    
    public Map<K, V> newMap(int option) {

        Map<K, V> myMap;

        switch (option) {
            case 1:
                myMap = new HashMap<>();
                return myMap;
            case 2:
                myMap = new TreeMap<>();
                return myMap;
            case 3:
                myMap = new LinkedHashMap<>();
                return myMap;
            default:
                //Defensive programming in main shouldn't let the method get to this option
                return null;
        }
    }
}
