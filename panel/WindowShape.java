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
 * xml�t�@�C���� �E�B���h�E�̂����W�ʒu�A�E�B���h�E�̂����W�ʒu�A
 * �E�B���h�E�̕��A�E�B���h�E�̍�����ۑ����Ă���B
 * �����ǂݏ������邾���̂��߂̂��̂��B
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
		//���o�O�𔭐������邩������Ȃ��B
		//if(!file.createNewFile()){
		//	throw new Exception("�t�@�C�����쐬�ł������ɂ���܂���B");
		//}
		make();
	}


	/**String url�Ŏw�肵���t�@�C����ǂށB�Ȃ���΍��B*/
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


	/**�E�B���h�E���J���Ƃ��ɌĂяo���΂悢�B*/
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

	/**�E�B���h�E�����Ƃ��ɌĂяo���΂悢�B
	 * ����H configuration.xml�ɂ͉��ɂ��e�����y�ڂ��Ă��Ȃ���B
	 * setPositionX()�́A�����܂ł���L�����u��̒l��ύX�����������B
	 *������ commit()��p�ӂ����B
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
			// TODO �����������ꂽ catch �u���b�N
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
