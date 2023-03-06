import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class DriverProgram {
    
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        ArrayList<String> shopCart = new ArrayList<>();

        Map<String, ArrayList<String>> myMap = makeMap(in);


        try {
            myMap = readFile("ListadoProducto (2).txt", myMap);
        } catch (Exception e) {
            System.out.println("Error fatal: Archivo no encontrado! Asegurese que el archivo esté en la misma carpeta que este programa.");
            System.exit(0);
        }

        System.out.println("\n********** Bienvenido a la tienda virtual. **********");

        while (true) {
            printMenu();
            int option = Integer.parseInt(in.nextLine());

            switch (option) {
                case 1:
                    shopCart.add(addProduct(in, myMap));
                    break;
                case 2:
                    productCat(in, myMap);
                    break;
                case 3:
                    listProds(shopCart);
                    break;
                case 4:
                    listInventory(myMap);
                    break;
                case 5:
                    in.close();
                    System.exit(0);
                default:
                    System.out.println("Por favor, ingrese una opción válida.");
                    break;
            }
        }
    }


    public static String addProduct(Scanner in, Map<String, ArrayList<String>> myMap) {
        System.out.println("Seleccione la categoría de productos que desea comprar.");
        String cat = "";
        String prod = "";

        while (true) {
            System.out.println(myMap.keySet());
            cat = in.nextLine();

            if (!(myMap.keySet().contains(cat))) {
                System.out.println("Seleccione una opción válida.");
            } else {
                break;
            }
        }

        System.out.println("Seleccione un producto.");

        while (true) {
            System.out.println(myMap.get(cat));
            prod = in.nextLine();

            if (!(myMap.get(cat).contains(prod))) {
                System.out.println("Seleccione una opción válida.");
            } else {
                break;
            }
        }
        System.out.println("Producto añadido!");
        return prod;
    }

    public static void productCat(Scanner in, Map<String, ArrayList<String>> myMap) {
        System.out.println("Ingrese el producto del que quiere hallar la categoría.");
        String prod = in.nextLine();

        for (String cat : myMap.keySet()) {
            if (myMap.get(cat).contains(prod)) {
                System.out.println("El producto que busco pertenece a la categoría: " + cat);
                return;
            }
        }

        System.out.println("El producto que buscó no existe.");
    }

    public static void listProds(ArrayList<String> cart) {
        System.out.println(cart);
    }

    public static void listInventory(Map<String, ArrayList<String>> myMap) {
        for (String k : myMap.keySet()) {
            System.out.println(k + ":");
            for (String v : myMap.get(k)) {
                System.out.println("\t" + v);
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n======= OPCIONES =======");
        System.out.println("1. Añadir un producto al carrito.");
        System.out.println("2. Ver la categoría de un producto.");
        System.out.println("3. Enlistar el carrito.");
        System.out.println("4. Enlistar el inventario.");
        System.out.println("5. Salir del programa.");
        System.out.println("\nSeleccione la opción que desea realizar: ");
    }

    private static Map<String, ArrayList<String>> makeMap(Scanner in) {
        MapFactory<String, ArrayList<String>> mapMaker = new MapFactory<>();

        System.out.println("Seleccione una opción para generar el mapa a utilizar en el programa.");
        System.out.println("1. HashMap");
        System.out.println("2. TreeMap");
        System.out.println("3. LinkedHashMap");
        System.out.println("\nEscriba el número de su selección.");
        int map = Integer.parseInt(in.nextLine());
        Map<String, ArrayList<String>> myMap = mapMaker.newMap(map);
        return myMap;
    }

    private static Map<String, ArrayList<String>> readFile(String path, Map<String, ArrayList<String>> myMap) throws Exception {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF8"));
            String line = null;
            ArrayList<String> products = new ArrayList<>();
            String category = "";
            String product = "";

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                
                if (!(category.equals(parts[0].trim()))) {
                    products = new ArrayList<>();
                }
                
                category = parts[0].trim();
                product = parts[1].trim();
                products.add(product);

                myMap.put(category, products);
            }
            reader.close();
            return myMap;
        } catch (Exception e) {
            throw new Exception();
        }
    }
}
