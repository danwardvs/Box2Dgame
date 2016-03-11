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
	  


public class main {
		
	public void drawBody(Body newBody){
        Vec2 position = newBody.getPosition();
        float angle = newBody.getAngle();
        drawRect(position.x*20,position.y*20,40,40);
	}
	
		public void drawRect(float x, float y, float width, float height){
			 // draw quad
	        GL11.glBegin(GL11.GL_QUADS);
	            GL11.glVertex2f(x,y);
	            GL11.glVertex2f(x+width,y);
	            GL11.glVertex2f(x+width,y+height);
	            GL11.glVertex2f(x,y+height);
	        GL11.glEnd();
		}
	  
	    public void start() {
	    	 // Static Body
		    Vec2  gravity = new Vec2(0,-10);
		    World world = new World(gravity);
		    BodyDef groundBodyDef = new BodyDef();
		    groundBodyDef.position.set(-100, -26);
		    Body groundBody = world.createBody(groundBodyDef);
		    PolygonShape groundBox = new PolygonShape();
		    groundBox.setAsBox(1000, 10);
		    groundBody.createFixture(groundBox, 0);

		    // Dynamic Body
		    BodyDef bodyDef = new BodyDef();
		    bodyDef.type = BodyType.DYNAMIC;
		    bodyDef.position.set(0, 4);
		    Body body = world.createBody(bodyDef);
		    PolygonShape dynamicBox = new PolygonShape();
		    dynamicBox.setAsBox(1, 1);
		    FixtureDef fixtureDef = new FixtureDef();
		    fixtureDef.shape = dynamicBox;
		    fixtureDef.density = 1;
		    fixtureDef.friction = 0.3f;
		    body.createFixture(fixtureDef);
		    
		    // Dynamic Body
		    BodyDef bodyDef2 = new BodyDef();
		    bodyDef2.type = BodyType.DYNAMIC;
		    bodyDef2.position.set(0.5f, 6f);
		    Body body2 = world.createBody(bodyDef2);
		    PolygonShape dynamicBox2 = new PolygonShape();
		    dynamicBox2.setAsBox(1, 1);
		    FixtureDef fixtureDef2 = new FixtureDef();
		    fixtureDef2.shape = dynamicBox2;
		    fixtureDef2.density = 1;
		    fixtureDef2.friction = 0.3f;
		    body2.createFixture(fixtureDef2);
		    
		    // Setup world
		    float timeStep = 1.0f/60.0f;
		    int velocityIterations = 6;
		    int positionIterations = 2;

		    // Run loop
		    for (int i = 0; i < 60; ++i) {
		        
		    } 
			
	        try {
	        Display.setDisplayMode(new DisplayMode(800,600));
	        Display.create();
	    } catch (LWJGLException e) {
	        e.printStackTrace();
	        System.exit(0);
	    }
	  
	    // init OpenGL
	    GL11.glMatrixMode(GL11.GL_PROJECTION);
	    GL11.glLoadIdentity();
	    GL11.glOrtho(0,800, 0, 600, 1, -1);
	    GL11.glMatrixMode(GL11.GL_MODELVIEW);
	    GL11.glTranslatef(400, 300, 0);
	    while (!Display.isCloseRequested()) {
	    	
	    	world.step(timeStep, velocityIterations, positionIterations);
	       
	    	
	        // Clear the screen and depth buffer
	        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);  
	         
	        // set the color of the quad (R,G,B,A)
	        GL11.glColor3f(0.5f,0.5f,1.0f);
	             
	        drawBody(body);
	        drawBody(body2);
	  
	        Display.update();
	    }
	  
	    Display.destroy();
	    }
	  
	
	public static void main(String[] args) {
		
		main quadExample = new main();
	        quadExample.start();
	}

}
