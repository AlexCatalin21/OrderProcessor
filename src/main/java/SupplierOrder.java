
import java.util.*;



public class SupplierOrder {


    private Map<String,List<Product>> supplierOrder = new HashMap<>();

    public Map<String, List<Product>> getSupplierOrder() {
        return supplierOrder;
    }

    public void setSupplierOrder(Map<String, List<Product>> order) {
        this.supplierOrder = order;
    }

}
