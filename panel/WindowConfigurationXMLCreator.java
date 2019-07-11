package panel;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

/**
 * JFrameのウィンドウのサイズと位置が書かれたxmlファイルを作成する。
 * USAGE 使用法
 * File file=new File("configuration.xml");
 * WindowConfigurationXMLCreator wcxc = WindowConfigurationXMLCreator(File file);
 * */
public class WindowConfigurationXMLCreator {
	private DocumentBuilderFactory dbfactory;
	private DocumentBuilder builder;
	private Document doc;
	private File file;
	public WindowConfigurationXMLCreator(File file) throws ParserConfigurationException{
		dbfactory = DocumentBuilderFactory.newInstance();
		builder = dbfactory.newDocumentBuilder();
		doc = builder.newDocument();
		this.file=file;
	}


//	public static void main(String[] args) throws ParserConfigurationException, TransformerException{
//		WindowConfigurationXMLCreator wcxc = new WindowConfigurationXMLCreator();
//		wcxc.createXMLDefaultData();
//	}

	public void createXMLDefaultData() throws ParserConfigurationException, TransformerException{
		Element windowElement = doc.createElement("window");
		doc.appendChild(windowElement);
		windowElement.appendChild(this.putNameAndValue("position-X","100"));
		windowElement.appendChild(this.putNameAndValue("position-Y", "100"));
		windowElement.appendChild(this.putNameAndValue("width","300"));
		windowElement.appendChild(this.putNameAndValue("height","400"));
		this.createXMLFile();
	}

	private Element putNameAndValue(String name,String value){
		Element element = doc.createElement(name);
		Text text = doc.createTextNode(value);
		element.appendChild(text);
		return element;
	}

	private void createXMLFile() throws TransformerException{
		//オブジェクトのdocを 書き出します。
		TransformerFactory transFactory = TransformerFactory.newInstance();
		Transformer transformer = transFactory.newTransformer();
		transformer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty(OutputKeys.METHOD, "xml");

		//変換元
		Source src=new DOMSource(doc);

		//変換先
		Result result = new StreamResult(this.file);

		//いよいよ変換と出力するよ。
		transformer.transform(src, result);
	}
}
