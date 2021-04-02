
import org.xml.sax.SAXException;
import javax.xml.parsers.*;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;


public class Main {

    public static void main(String[] args) {

        String inputDirPath = "/home/alex/Desktop/pss_project/input_files";


        try {
            WatchService watchService = FileSystems.getDefault().newWatchService();
            Path path = Paths.get(inputDirPath);

            path.register(
                    watchService,
                    StandardWatchEventKinds.ENTRY_CREATE,
                    StandardWatchEventKinds.ENTRY_MODIFY
                    );

            WatchKey key;
            while ((key = watchService.take()) != null) {
                SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
                for (WatchEvent<?> event : key.pollEvents()) {
                        if (event.context().toString().matches("orders\\d{2}[.]xml")) {
                            // reading process
                            SAXParser saxParser = saxParserFactory.newSAXParser();
                            XmlProductReader xmlProductReader = new XmlProductReader();
                            saxParser.parse(path + "/" + event.context().toString(), xmlProductReader);

                            // sorting process
                            ProductsSorter productsSorter = new ProductsSorter();
                            SupplierOrder supplierOrder = new SupplierOrder();
                            List<Product> productList = xmlProductReader.getProducts();
                            supplierOrder.setSupplierOrder(productsSorter.orderProductsByDateAndPrice(productsSorter.orderBySupplier(productList)));

                            // writing process
                            XmlProductWriterImpl xmlProductWriter = new XmlProductWriterImpl();
                            xmlProductWriter.writeToXml(supplierOrder);
                        }
                    key.reset();
                    }
                }
            } catch (IOException exception) {
            exception.printStackTrace();
        } catch (ParserConfigurationException configurationException) {
            configurationException.printStackTrace();
        } catch (InterruptedException interruptedException) {
            interruptedException.printStackTrace();
        } catch (SAXException exception) {
            exception.printStackTrace();
        } catch (TransformerException exception) {
            exception.printStackTrace();
        }
    }
    }

