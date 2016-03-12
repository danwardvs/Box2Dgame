import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

public class Box {
	
	Body body;
	float width;
	float height;
	
	public Box(World newWorld,BodyType newBodyType,  float newX, float newY,float newWidth, float newHeight, float newAngle){
		
		height = newHeight;
		width = newWidth;
		// Dynamic Body
	    BodyDef bodyDef = new BodyDef();
	    bodyDef.type = newBodyType;
	    bodyDef.position.set(newX, newY);
	    bodyDef.angle = newAngle;
	    body = newWorld.createBody(bodyDef);
	    PolygonShape dynamicBox = new PolygonShape();
	    dynamicBox.setAsBox(width, height);
	    FixtureDef fixtureDef = new FixtureDef();
	    fixtureDef.shape = dynamicBox;
	    fixtureDef.density = 1;
	    fixtureDef.friction = 0.3f;
	    body.createFixture(fixtureDef);
	    
	}
	public void setTransform(float newX,float newY,float newAngle){
		Vec2 location = new Vec2(newX,newY);
		body.setTransform(location, newAngle);
	}
	
	public void draw(){
        Vec2 position = body.getPosition();
        float angle = body.getAngle();
        drawRect(angle,position.x*20,position.y*20,width*40,height*40);
	}
	
	public void applyTorque(float newTorque){
		 body.applyTorque(newTorque);
	}
	
	public void drawRect(float angle,float x, float y, float width, float height){
			 // draw quad
			GL11.glLoadIdentity();
			GL11.glPushMatrix();
			GL11.glTranslatef(x, y, 0);
			GL11.glTranslatef(400, 300, 0);
			GL11.glRotatef((float)Math.toDegrees(angle), 0, 0, 1);
			GL11.glTranslatef(-x, -y, 0);
			GL11.glTranslatef(-400, -300, 0);
			GL11.glTranslatef(400, 300, 0);
			
			
			
				//
				GL11.glBegin(GL11.GL_QUADS);
					
					GL11.glColor3f(0,0,0);
					GL11.glVertex2f(x-(width/2),y-(height/2));
					GL11.glVertex2f(x-(width/2)+width,y-(height/2));
					GL11.glVertex2f(x-(width/2)+width,y+height-(height/2));
					GL11.glVertex2f(x-(width/2),y+height-(height/2));
				
					GL11.glColor3f(0.5f,0.5f,1.0f);
					GL11.glVertex2f(x-(width/2)+1,y-(height/2)+1);
	            	GL11.glVertex2f(x-(width/2)+width-1,y-(height/2)+1);
	            	GL11.glVertex2f(x-(width/2)+width-1,y+height-(height/2)-1);
	            	GL11.glVertex2f(x-(width/2)+1,y+height-(height/2)-1);
	            	
		           
	            GL11.glEnd();
	            
	            
	            
	        GL11.glPopMatrix();
		}
}
