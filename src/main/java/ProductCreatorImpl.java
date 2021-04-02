import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class ProductCreatorImpl implements ProductCreatorI {


    @Override
    public Node createProductElement(Document doc, String description, String price, String gtin,
                                     String orderId) {
        Element product = doc.createElement("Product");

        // create description element
        product.appendChild(createProductElements(doc, "description", description, null));

        // create gtin element
        product.appendChild(createProductElements(doc, "gtin", gtin,null));

        // create price element
        product.appendChild(createProductElements(doc, "price", price,"USD"));

        // create orderId element
        product.appendChild(createProductElements(doc, "orderId", orderId,null));

        return product;
    }

    // utility method to create text node
    @Override
    public Node createProductElements(Document doc, String name, String value, String currency) {
        Element node = doc.createElement(name);
        if(name.equalsIgnoreCase("price")){
            node.setAttribute("currency", currency);
        }
        node.appendChild(doc.createTextNode(value));
        return node;
    }
}
