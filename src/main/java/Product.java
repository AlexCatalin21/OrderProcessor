
import java.time.LocalDateTime;


public class Product {


    private String description;
    private Double price;
    private String gtin;
    private String supplier;
    private int orderId;
    private LocalDateTime creationDate;

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public String getDescriction() {
        return description;
    }

    public void setDescriction(String descriction) {
        this.description = descriction;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getGtin() {
        return gtin;
    }

    public void setGtin(String gtin) {
        this.gtin = gtin;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getIdForOutputFile(){
        return String.valueOf(orderId).substring(0,2);
    }

    @Override
    public String toString(){
        return "Product = description :" + description + ", price :"+ price +", gtin:" + gtin + ", orderId:" + orderId
                + ", supplier:" + supplier + ", creation date:" + creationDate;
    }




}
