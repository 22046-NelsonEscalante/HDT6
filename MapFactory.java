import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.TreeMap;
import java.util.Map;


/**
 * The factory for the maps we are going to use.
 * 
 * @author 22046-NelsonEscalante
 */
public class MapFactory<K, V> {
    
    /**
     * This method creates a map with a specific implementation chosen by the user.
     * @param option the number for the option the user wants to use.
     * @return a map with the implementation the user chooses.
     */
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
