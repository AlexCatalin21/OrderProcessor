
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {


        try {
            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            File dir = new File("input_files");
            for (File file : dir.listFiles()) {
                // check for file name format if it is 'ordersXX.xml'
                if (file.getName().matches("orders\\d{2}[.]xml")) {

                    // reading process
                    SAXParser saxParser = saxParserFactory.newSAXParser();
                    XmlProductReader xmlProductReader = new XmlProductReader();
                    saxParser.parse(file, xmlProductReader);

                    // sorting process
                    ProductsSorter productsSorter = new ProductsSorter();
                    SupplierOrder supplierOrder = new SupplierOrder();
                    List<Product> productList = xmlProductReader.getProducts();
                    supplierOrder.setSupplierOrder(productsSorter.orderProductsByDateAndPrice(productsSorter.orderBySupplier(productList)));

                    // writing process
                    XmlProductWriterImpl xmlProductWriter = new XmlProductWriterImpl();
                    xmlProductWriter.writeToXml(supplierOrder);
                    }
                }
            } catch (ParserConfigurationException parserConfigurationException) {
            parserConfigurationException.printStackTrace();
        } catch (TransformerException transformerException) {
            transformerException.printStackTrace();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        } catch (SAXException saxException) {
            saxException.printStackTrace();
        }
    }
    }

