package cn.net.sinodata.cm.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.jdom2.xpath.XPath;
import org.xml.sax.InputSource;
import org.xml.sax.helpers.DefaultHandler;

import cn.net.sinodata.framework.log.SinoLogger;

public class XMLHandler {

	private static final SinoLogger logger = SinoLogger.getLogger(XMLHandler.class);
	protected Document doc;

	/**
	 * 
	 * @param doc
	 */
	public void setDoc(Document doc) {
		this.doc = doc;
	}
	
	/**
	 * 
	 * @return
	 */
	public Document getDoc() {
		return doc;
	}

	/**
	 * 
	 * @param xml
	 * @return
	 */
	public XMLHandler parseXML(String xml) {
		StringReader read = new StringReader(xml);
		InputSource source = new InputSource(read);
		SAXBuilder sb = new SAXBuilder();
		try {
			doc = sb.build(source);
		} catch (JDOMException ex) {
			logger.error(ex);
		} catch (Exception ex) {
			logger.error(ex);
		}
		return this;
	}

	/**
	 * 
	 * @param filePath
	 * @return
	 */
	public XMLHandler loadXML(String filePath){
		SAXBuilder builder = new SAXBuilder(false);
		builder.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
		builder.setEntityResolver(new DefaultHandler());
		try {
			doc = builder.build(new File(filePath));
		} catch (JDOMException ex) {
			logger.error(ex);
		} catch (IOException ex) {
			logger.error(ex);
		}
		return this;
	}

	/**
	 * 
	 */
	public String toString() {
//		Format format = Format.getPrettyFormat();
		Format format = Format.getRawFormat();
		format.setExpandEmptyElements(true);
		XMLOutputter outputter = null;
		try {
			outputter = new XMLOutputter(format);
		} catch (Exception ex) {
			logger.error(ex);
		}
		return outputter.outputString(doc);
	}

	/**
	 * 
	 * @param xPath
	 * @param key
	 * @param value
	 * @return
	 */
	public Element addNode(String xPath, String key, String value) {
		if(xPath.endsWith("/")){
			xPath = xPath.substring(0,xPath.length()-1);
		}
		Element parent;
		Element element = null;
		try {
			parent = (Element) XPath.selectSingleNode(doc, xPath);
			element = new Element(key).setText(value);
			parent.addContent(element);
		} catch (JDOMException ex) {
			logger.error(ex);
		}
		return element;
	}
	
	/**
	 * 
	 * @param parent
	 * @param key
	 * @param value
	 * @return
	 */
	public Element addNodeOnElement(Element parent, String key, String value) {
		Element element = new Element(key).setText(value);
		parent.addContent(element);
		return element;
	}

	/**
	 * 
	 * @param path
	 * @param map
	 */
	public void addElementsWithMap(String path, Map<String, String> map){
		try {
			Element element = (Element) XPath.selectSingleNode(doc, path);
			Set<String> nameSet = map.keySet();
			for (String name : nameSet) {
				String value = map.get(name);
				Element child = element.getChild(name);
				if(child!=null){
					child.setText(value);
				}else{
					element.addContent(new Element(name).setText(value));
					element.getChild(name);
				}
			}
		} catch (JDOMException ex) {
			logger.error(ex);
		}
		
	}
	
	private boolean hasElement(Element baseElement, String name){
		Element element = baseElement.getChild(name);
		if(element != null){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 
	 * @param path
	 * @param name
	 * @param value
	 */
	public void addAttribute(String path, String name, String value){
		Element element;
		try {
			element = (Element) XPath.selectSingleNode(doc, path);
			element.setAttribute(name, value);
		} catch (JDOMException ex) {
			logger.error(ex);
		}
	}
	
	/**
	 * 
	 * @param element
	 * @param name
	 * @param value
	 */
	public void addAttributeOnElement(Element element, String name, String value){
		element.setAttribute(name, value);
	}

	/**
	 * 
	 * @param xPath
	 * @param key
	 * @param value
	 * @return
	 */
	public Element addSimpleElement(String xPath, String key, String value) {
		Element element = new Element(key).setText(value);
		try {
			Element parent = (Element) XPath.selectSingleNode(doc, xPath);
			parent.addContent(element);
		} catch (JDOMException ex) {
			logger.error(ex);
		}
		return element;
	}

	/**
	 * 
	 * @param xPath
	 * @return
	 */
	public Element getElement(String xPath) {
		Element elt = null;
		try {
			elt = (Element) XPath.selectSingleNode(doc, xPath);
		} catch (JDOMException ex) {
			logger.error(ex);
		}
		return elt;
	}
	
	/**
	 * 
	 * @param xPath
	 * @param name
	 * @param value
	 * @return
	 */
	public Element getElementByAttribute(String xPath, String name, String value){
		Element elt = null;
		try {
			xPath = xPath + "[@" + name + "=" + "'" + value + "']";
			elt = (Element) XPath.selectSingleNode(doc, xPath);
		} catch (JDOMException ex) {
			logger.error(ex);
		}
		return elt;
	}

	/**
	 * 
	 * @param path
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List getList(String path) {
		List list = null;
		try {
			list = XPath.selectNodes(doc, path);
		} catch (JDOMException ex) {
			logger.error(ex);
		}
		return list;
	}

	/**
	 * 
	 * @param xPath
	 * @param key
	 * @param value
	 * @return
	 */
	public Element putTextValue(String xPath, String key, String value) {
		Element elt = getElement(handlePath(xPath, key));
		if (null != elt)
			elt.setText(value);
		else {
			addSimpleElement(xPath, key, value);
		}
		return elt;
	}
	
	/**
	 * 
	 * @param xPath
	 * @param value
	 */
	public void setTextValue(String xPath, String value) {
		Element elt = getElement(xPath);
		if (null != elt)
			elt.setText(value);
	}

	/**
	 * 
	 * @param xPath
	 * @param nodeName
	 * @return
	 */
	public String getSingleNodeValue(String xPath, String nodeName) {
		String path = handlePath(xPath, nodeName);
		return getSinglePathValue(path);
	}

	/**
	 * 
	 * @param path
	 * @return
	 */
	public String getSinglePathValue(String path) {
		Element element = null;
		try {
			element = (Element) XPath.selectSingleNode(doc, path);
		} catch (JDOMException ex) {
			logger.error(ex);
		}
//		return element == null ? "":element.getTextTrim();
		return element == null ? "":element.getText();
	}
	
	/**
	 * 
	 * @param path
	 * @param name
	 * @return
	 */
	public String getSinglePathAttribute(String path, String name) {
		Element element = null;
		try {
			element = (Element) XPath.selectSingleNode(doc, path);
		} catch (JDOMException ex) {
			logger.error(ex);
		}
		return element == null ? "":element.getAttribute(name).toString();
	}

	/**
	 * 
	 * @param path
	 * @param nodeName
	 * @return
	 */
	private String handlePath(String path, String nodeName){
		if (path.endsWith("/")) {
			return path + nodeName;
		} else {
			return path + "/" + nodeName;
		}
	}
	
    /**
	 * 格式化XML并指定字符集
	 * @param suppressDeclaration 是否包含XML头信息，true不包含，false包含
	 * @author manan
	 */
    private boolean format(String path, boolean suppressDeclaration){
    	XMLOutputter outputter = null;
    	FileOutputStream fos = null;
    	try{
           Format format = Format.getPrettyFormat();
           format.setOmitDeclaration(suppressDeclaration);
           /** 指定XML编码 */
           format.setEncoding("GBK");
           System.out.println(path);
           File file = new File(path);
           if(file.exists()){
        	   file.delete();
           } 
           file.createNewFile();
           outputter = new XMLOutputter(format);
           fos = new FileOutputStream(file);
           outputter.output(doc, fos);
	       return true;
       }catch(Exception ex){
    	   logger.error(ex);
           return false;
       }finally{
    	   if(fos != null)
			try {
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
       }
    }
	
    /**
     * 
     * @param path
     * @return
     */
	public boolean writeToFile(String path){
		return format(path, false);
	}
	public static void main(String[] args) {
		String str = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" + 
				"<sinobpc>\r\n" + 
				"  <header>\r\n" + 
				"    <msg>\r\n" +
				"    	<abc></abc>\r\n" +
				"    </msg>\r\n" +
				"  </header>\r\n" +
				"</sinobpc>";
		XMLHandler xml = new XMLHandler();
		xml.parseXML(str);
		Map map = new HashMap();
		map.put("test", "2222");
		map.put("abc", "3333");
		xml.addElementsWithMap("/sinobpc/header/msg", map);
		System.out.println(xml.toString());
	}
}
