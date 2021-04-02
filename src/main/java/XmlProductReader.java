import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class XmlProductReader extends DefaultHandler{

    private boolean productElement = false;
    private boolean description = false;
    private boolean price = false;
    private boolean gtin = false;
    private boolean supplier = false;
    private int orderId;
    private LocalDateTime creationDate;
    private List<Product> products = null;
    private Product product = null;


    public List getProducts() {
        return products;
    }


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if (qName.equalsIgnoreCase("order")) {
            orderId = Integer.parseInt(attributes.getValue("ID"));
            creationDate = LocalDateTime.parse(attributes.getValue("created"));
            if (products == null) {
                products = new ArrayList<>();
            }
        } else if (qName.equalsIgnoreCase("product")) {
            product = new Product();
            product.setOrderId(orderId);
            product.setCreationDate(creationDate);
            productElement = true;
        } else if (qName.equalsIgnoreCase("description")) {
            description = true;
        } else if (qName.equalsIgnoreCase("price")) {
            price = true;
        } else if (qName.equalsIgnoreCase("gtin")) {
            gtin = true;
        } else if (qName.equalsIgnoreCase("supplier")) {
            supplier = true;
        }

    }
    // add a product from XML file to a product list when "product" tag is closed
    @Override
    public void endElement(String uri, String localName, String qName) {
        if (qName.equalsIgnoreCase("product")) {
            products.add(product);
        }
    }


    // set the product fields to the values of XML elements
    @Override
    public void characters(char ch[], int start, int length) {
        if (description) {
            product.setDescriction(new String(ch, start, length));
            description = false;
        } else if (price) {
            product.setPrice(Double.parseDouble(new String(ch, start, length)));
            price = false;
        } else if (gtin) {
            product.setGtin(new String(ch, start, length));
            gtin = false;
        } else if (supplier) {
            product.setSupplier(new String(ch, start, length));
            supplier = false;
        }

    }
}
