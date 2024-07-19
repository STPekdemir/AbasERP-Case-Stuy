public class App {
    public static void main(String[] args) throws Exception {
        ItemsLogic itemsLogic = new ItemsLogic();

        System.out.println("Üç siparişteki malların toplam tutarının çıktısı");
        System.out.println(itemsLogic.totalPriceOfAllItems());
        System.out.println("Üç siparişteki bütün malların ortalama fiyatı");
        System.out.println(itemsLogic.averagePriceOfAllItems());
        System.out.println("Üç siparişteki bütün malların tek tek mal bazlı ortalama fiyatı");
        System.out.println(itemsLogic.averagePricePerItem());
        System.out.println("Tek tek mal bazlı, malların hangi siparişlerde kaç adet olduğunun çıktısı");
        System.out.println(itemsLogic.quantityPerItemPerOrder());

        ApiRequest apiRequest = new ApiRequest();
        apiRequest.sendGetRequest("https://api.restful-api.dev/objects");
        apiRequest.sendPostRequest("https://api.restful-api.dev/objects");


    }
}
