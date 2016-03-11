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
		
	  public static float normalRelativeAngle( float angle) {
		    return ((angle %= ((float)Math.PI*2)) >= 0 ? (angle < (float)Math.PI) ? angle : angle - ((float)Math.PI*2) : (angle >= -(float)Math.PI) ? angle : angle + ((float)Math.PI*2))* (180 / (float)Math.PI);
	  }


	    public void start() {
	    	
	    	
	    	
	    	 // Static Body
		    Vec2  gravity = new Vec2(0,-10);
		    World world = new World(gravity);
		    BodyDef groundBodyDef = new BodyDef();
		    groundBodyDef.position.set(0, -15);
		    Body groundBody = world.createBody(groundBodyDef);
		    PolygonShape groundBox = new PolygonShape();
		    groundBox.setAsBox(800, 0);
		    groundBody.createFixture(groundBox, 0);
		    
		    
		    Box[] gameBoxes = new Box[60];
		 
		    // Setup world
		    float timeStep = 1.0f/60.0f;
		    int velocityIterations = 6;
		    int positionIterations = 2;

		    // Run loop
		    for (int i = 0; i < 59; ++i) {
			    gameBoxes[i] = new Box(world,(float)Math.random()*20,(float)Math.random()*20);

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
	    
	    while (!Display.isCloseRequested()) {
	    	
	    	world.step(timeStep, velocityIterations, positionIterations);
	    	
	    	
	        // Clear the screen and depth buffer
	        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);  
	         
	        GL11.glClearColor(1, 1, 1, 0);
	        // set the color of the quad (R,G,B,A)
	        
	             
	        for (int i = 0; i < 59; ++i) {
			    gameBoxes[i].draw();

		    } 
	  
	        Display.update();
	        
	        Display.sync(60);
	    }
	  
	    Display.destroy();
	    }
	  
	
	public static void main(String[] args) {
		
		main quadExample = new main();
	        quadExample.start();
	}

}
