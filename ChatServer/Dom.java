import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.*;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class Dom
{
    private DocumentBuilderFactory dbf;
    private DocumentBuilder db;
    private Document doc;
    InputSource is;
    public Dom()
    {
        dbf = DocumentBuilderFactory.newInstance();
        try
        {
            db = dbf.newDocumentBuilder();
        }
        catch(ParserConfigurationException e){}
    }
    public Document prepareDoc(String raw)
    {
        is = new InputSource();
        is.setCharacterStream(new StringReader(raw));
        try
        {
            doc = db.parse(is);
        }
        catch(SAXException e)
        {
        }
        catch(IOException e)
        {
        }
        return doc;
    }
    public NodeList byTag(String tagName)
    {
        return doc.getElementsByTagName(tagName);
    }
    public Element byId(String id)
    {
        return (Element)doc.getElementById(id);
    }
    public ArrayList<String> attributeValue(String node, String attName)
    {
        int i;
        ArrayList<String> values = new ArrayList<String>();
        NodeList nodes = this.byTag(node);
        for(i = 0; i < nodes.getLength(); i++)
        {
            Element el = (Element)nodes.item(i);
            values.add(el.getAttribute(attName));
        }
        return values;
    }
    public void addNode(Document doc, String tagName, String id, String nodeValue)
    {
        Element el = doc.createElement(tagName);
        el.setAttribute("id", id);
        el.setNodeValue(nodeValue);
        doc.appendChild(el);
    }
    public void setAttribute(String id, String attName, String attValue)
    {
        ((Element)this.byId(id)).setAttribute(attName, attValue);
    }
}
