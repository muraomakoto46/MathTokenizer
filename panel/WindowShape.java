package panel;

import java.awt.Rectangle;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * xmlファイルに ウィンドウのｘ座標位置、ウィンドウのｙ座標位置、
 * ウィンドウの幅、ウィンドウの高さを保存してある。
 * それを読み書きするだけのためのものだ。
 * WindowShape ws = new WindowShape();*/
public class WindowShape{

	private DocumentBuilderFactory dbfactory;
	private DocumentBuilder builder;
	private Document doc;
	private Element rootNode;

	private File file = new File("configuration.xml");

	public WindowShape()throws Exception{
		make();
	}

	public WindowShape(File file)throws Exception{
		this.file=file;
		//↓バグを発生させるかもしれない。
		//if(!file.createNewFile()){
		//	throw new Exception("ファイルが作成できそうにありません。");
		//}
		make();
	}


	/**String urlで指定したファイルを読む。なければ作る。*/
	private void make()throws Exception{
		System.out.println(file.exists());
		if(!file.exists()){
//			throw new IOException();
			WindowConfigurationXMLCreator wcxc=new WindowConfigurationXMLCreator(this.file);
			wcxc.createXMLDefaultData();
		}
		this.dbfactory = DocumentBuilderFactory.newInstance();
		this.builder = dbfactory.newDocumentBuilder();
		this.doc=builder.parse(file);
		this.rootNode=doc.getDocumentElement();
	}


	/**ウィンドウを開くときに呼び出せばよい。*/
	public Rectangle getRectangle(){
		Rectangle rectangle=new Rectangle();

		rectangle.x = helpToGet("position-X");
		rectangle.y = helpToGet("position-Y");
		rectangle.width = helpToGet("width");
		rectangle.height = helpToGet("height");
		return rectangle;
	}

	private int helpToGet(String tagName){
		NodeList nodeList =rootNode.getElementsByTagName(tagName);
		return Integer.parseInt(nodeList.item(0).getFirstChild().getNodeValue());
	}

	/**ウィンドウを閉じるときに呼び出せばよい。
	 * あれ？ configuration.xmlには何にも影響を及ぼしていないよ。
	 * setPositionX()は、あくまでも主記憶装置上の値を変更しただけだ。
	 *そこで commit()を用意した。
	 * @throws TransformerException
	 **/
	public void setRectangle(Rectangle rectangle){
		NodeList nodeList = rootNode.getElementsByTagName("position-X");
		nodeList.item(0).getFirstChild().setNodeValue(String.valueOf(rectangle.x));

		nodeList = rootNode.getElementsByTagName("position-Y");
		nodeList.item(0).getFirstChild().setNodeValue(String.valueOf(rectangle.y));

		nodeList = rootNode.getElementsByTagName("width");
		nodeList.item(0).getFirstChild().setNodeValue(String.valueOf(rectangle.width));

		nodeList = rootNode.getElementsByTagName("height");
		nodeList.item(0).getFirstChild().setNodeValue(String.valueOf(rectangle.height));

		try {
			this.commit();
		} catch (TransformerException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}


	private void commit() throws TransformerException{
		TransformerFactory tff = TransformerFactory.newInstance();
		Transformer tf = tff.newTransformer();
		tf.setOutputProperty(OutputKeys.ENCODING,"UTF-8");
		tf.transform(new DOMSource(doc), new StreamResult("configuration.xml"));
	}
}
