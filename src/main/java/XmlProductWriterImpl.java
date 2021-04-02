import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.List;
import java.util.Map;

public class XmlProductWriterImpl implements XmlProductWriterI {

    private String outputDirPath="/home/alex/Desktop/pss_project/output_files/";


    DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
    DocumentBuilder dBuilder;

    // for each supplier this method will create a XML file with corresponding products
    @Override
    public void writeToXml(SupplierOrder supplierOrder) throws ParserConfigurationException, TransformerException {
        // iterate through suppliers
        for (Map.Entry<String, List<Product>> entry : supplierOrder.getSupplierOrder().entrySet()) {
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();
            // add elements to Document
            Element rootElement = doc.createElement("Products");
            // append root element to document
            doc.appendChild(rootElement);
            // iterate through products of supplier
            for (Product product : entry.getValue()) {
                // append child elements to root element
                ProductCreatorImpl productCreator=new ProductCreatorImpl();
                rootElement.appendChild(productCreator.createProductElement(doc, product.getDescriction(), String.valueOf(product.getPrice()), product.getGtin(), String.valueOf(product.getOrderId())));
            }
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            // for pretty print
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);

            // write to file
            StreamResult outputFile = new StreamResult(new File(outputDirPath + entry.getKey() + entry.getValue().get(0).getIdForOutputFile() + ".xml"));

            // write data
            transformer.transform(source, outputFile);
        }
    }
}
