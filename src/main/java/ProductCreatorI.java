import org.w3c.dom.Document;
import org.w3c.dom.Node;

public interface ProductCreatorI {

    public Node createProductElement(Document doc, String element1, String element2, String element3,
                                     String element4);

    public Node createProductElements(Document doc, String name, String value, String attribute);
}
