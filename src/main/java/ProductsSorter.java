import java.util.*;

public class ProductsSorter {

    private Map<String, List<Product>> orderedProducts = new HashMap<>();

    // create a hashmap with suppliers as keys and list of products as value for each corresponding supplier
    public Map<String, List<Product>> orderBySupplier(List<Product> productList){

        for (Product product:productList) {
            String key = product.getSupplier();
            orderedProducts.computeIfAbsent( key, k -> new ArrayList<>()).add(product);
        }
        return orderedProducts;
    }

    // sort the list of products of each supplier by date and price
    public Map<String, List<Product>> orderProductsByDateAndPrice(Map<String, List<Product>> orderedProducts){
        for (Map.Entry<String, List<Product>> entry : orderedProducts.entrySet()){
            entry.getValue().sort(Comparator.comparing(Product::getCreationDate).reversed());
            entry.getValue().sort(Comparator.comparing(Product::getPrice).reversed());
        }
        return orderedProducts;
    }

}
