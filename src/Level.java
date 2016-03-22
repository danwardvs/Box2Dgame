import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;



public class Level {
	
	String level_path;
	
	public Level(String newLevelPath){
		level_path = newLevelPath;
	}
	
	public void load_xml(){
		try {

			File fXmlFile = new File(level_path);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
					
			//optional, but recommended
			//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
			doc.getDocumentElement().normalize();

			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
					
			NodeList nList = doc.getElementsByTagName("object");
					
			System.out.println("----------------------------");

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);
						
				System.out.println("\nCurrent Element :" + nNode.getNodeName());
						
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;

					System.out.println("Type: " + eElement.getAttribute("type"));
					System.out.println("X: " + eElement.getElementsByTagName("x").item(0).getTextContent());
					System.out.println("Y: " + eElement.getElementsByTagName("y").item(0).getTextContent());


				}
			}
		    } catch (Exception e) {
			e.printStackTrace();
		    }
	}

}
