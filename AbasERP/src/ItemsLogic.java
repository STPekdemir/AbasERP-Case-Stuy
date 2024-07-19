import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ItemsLogic {
    private List<Item> items = new ArrayList<>();

    public ItemsLogic() {
        readItemsFromFile("data.csv");
    }

    //Tüm ürünlerin toplam fiyatı
    public double totalPriceOfAllItems() {
        double total = 0;
        for (Item item : items) {
            total += item.getQuantity() * item.getUnitPrice();
        }

        writeResultToFile(String.valueOf(total), "totalPriceOfAllItems.txt");
        return total;
    }

    //Tüm ürünlerin ortalama fiyatı
    public double averagePriceOfAllItems() {
        double total = 0;
        int totalCount = 0;
        for (Item item : items) {
            total += item.getQuantity() * item.getUnitPrice();
            totalCount += item.getQuantity();
        }
        double avarege = total / totalCount;
        writeResultToFile(String.valueOf(avarege), "averagePriceOfAllItems.txt");
        return avarege;
    }

    //Bir ürünün ortalama fiyatı
    public Map<Integer, Double> averagePricePerItem() {
        Map<Integer, Double> totalPricePerItem = new HashMap<>();
        Map<Integer, Integer> totalCountPerItem = new HashMap<>();

        for (Item item : items) {
            totalPricePerItem.merge(item.getItemNumber(), item.getQuantity() * item.getUnitPrice(), (a, b) -> a + b);
            totalCountPerItem.merge(item.getItemNumber(), item.getQuantity(), (a, b) -> a + b);
        }

        Map<Integer, Double> averagePricePerItem = new HashMap<>();
        totalPricePerItem.forEach((itemNumber, totalPrice) -> {
            double averagePrice = totalPrice / totalCountPerItem.get(itemNumber);
            averagePricePerItem.put(itemNumber, averagePrice);
        });

        writeResultToFile(averagePricePerItem.toString(), "averagePricePerItem.txt");
        return averagePricePerItem;
    }

    //Siparişe göre ürün sayısı
    public Map<Integer, Map<Integer, Integer>> quantityPerItemPerOrder() {
        Map<Integer, Map<Integer, Integer>> quantityPerItemPerOrder = new HashMap<>();

        for (Item item : items) {
            int itemNumber = item.getItemNumber();
            int orderNumber = item.getOrderNumber();
            int quantity = item.getQuantity();


            if (!quantityPerItemPerOrder.containsKey(itemNumber)) {
                quantityPerItemPerOrder.put(itemNumber, new HashMap<>());
            }

            Map<Integer, Integer> orderToQuantityMap = quantityPerItemPerOrder.get(itemNumber);


            if (!orderToQuantityMap.containsKey(orderNumber)) {
                orderToQuantityMap.put(orderNumber, quantity);
            } else {

                int currentQuantity = orderToQuantityMap.get(orderNumber);
                orderToQuantityMap.put(orderNumber, currentQuantity + quantity);
            }
        }
        writeResultToFile(quantityPerItemPerOrder.toString(), "quantityPerItemPerOrder.txt");
        return quantityPerItemPerOrder;
    }

    // data.csv dosyasından dataları okuyup Item yaratır ve bu ıtemı ıtems arrayıne ekler
    private void readItemsFromFile(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length == 4) {
                    int orderNumber = Integer.parseInt(values[0]);
                    int itemNumber = Integer.parseInt(values[1]);
                    int quantity = Integer.parseInt(values[2]);
                    double unitPrice = Double.parseDouble(values[3]);
                    items.add(new Item(orderNumber, itemNumber, quantity, unitPrice));
                }
            }
        } catch (IOException e) {

        }
    }

    // Methodlardan dönen sonuçları dosyaya yazar.
    private void writeResultToFile(String result, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(result);
            writer.newLine();
        } catch (IOException e) {

        }
    }
}