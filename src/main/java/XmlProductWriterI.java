import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import java.util.List;
import java.util.Map;

public interface XmlProductWriterI {

    public void writeToXml(SupplierOrder supplierOrder) throws ParserConfigurationException, TransformerException;

}
