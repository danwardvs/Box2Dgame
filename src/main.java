import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
	  


public class main {
	
		int BOX_AMOUNT = 10;
		Box[] gameBoxes = new Box[BOX_AMOUNT];
		
		
		 public void update(){
			 if (Keyboard.isKeyDown(Keyboard.KEY_UP)){
				 gameBoxes[0].applyTorque(30f);
			 }
				 
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
		    
		    
		    
		 
		    // Setup world
		    float timeStep = 1.0f/60.0f;
		    int velocityIterations = 6;
		    int positionIterations = 2;

		    // Run loop
		    for (int i = 0; i <BOX_AMOUNT; ++i) {
		    	//if((int)(Math.random()*2)==1)
		    			gameBoxes[i] = new Box(world,BodyType.DYNAMIC,(3*i)-10,0,1,1,0,1,1f,0.5f,0);
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
	    	update();
	    	
	    	
	        // Clear the screen and depth buffer
	        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);  
	         
	        GL11.glClearColor(1, 1, 1, 0);
	        // set the color of the quad (R,G,B,A)
	       
	             
	        for (int i = 0; i < BOX_AMOUNT; ++i) {
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
