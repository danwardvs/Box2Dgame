import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;

public class Level {
	
	String level_path;
	
	World gameWorld;
	WorldController gameController;
	String type;
	float x;
	float y;
	float width;
	float height;
	float angle;
	float r;
	float g;
	float b;
	String bodytype;
	
	
	
	
	public Level(WorldController newWorldController, World newWorld, String newLevelPath){
		level_path = newLevelPath;
		gameWorld = newWorld;
		gameController = newWorldController;
	}
	
	public void load_level(){
		try {

			File fXmlFile = new File(level_path);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);
					
			//optional, but recommended
			//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
			doc.getDocumentElement().normalize();
					
			NodeList nList = doc.getElementsByTagName("object");
					
			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node nNode = nList.item(temp);
						
						
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {

					Element eElement = (Element) nNode;

					type = ("Type: " + eElement.getAttribute("type"));
					x = Float.valueOf(eElement.getElementsByTagName("x").item(0).getTextContent());
					y = Float.valueOf(eElement.getElementsByTagName("y").item(0).getTextContent());
					width = Float.valueOf(eElement.getElementsByTagName("width").item(0).getTextContent());
					height = Float.valueOf(eElement.getElementsByTagName("height").item(0).getTextContent());
					angle = Float.valueOf(eElement.getElementsByTagName("angle").item(0).getTextContent());
					r = Float.valueOf(eElement.getElementsByTagName("r").item(0).getTextContent());
					g = Float.valueOf(eElement.getElementsByTagName("g").item(0).getTextContent());
					b = Float.valueOf(eElement.getElementsByTagName("b").item(0).getTextContent());
					bodytype = (eElement.getElementsByTagName("bodytype").item(0).getTextContent());
					if(bodytype.equals("KINEMATIC")){
						gameController.createBox(new Box(gameWorld,BodyType.KINEMATIC,x,y,width,height,angle,r,g,b,0));
					}
					if(bodytype.equals("DYNAMIC")){
						gameController.createBox(new Box(gameWorld,BodyType.DYNAMIC,x,y,width,height,angle,r,g,b,0));
					}


				}
			}
		    } catch (Exception e) {
		    	e.printStackTrace();
		    }
	}

}
