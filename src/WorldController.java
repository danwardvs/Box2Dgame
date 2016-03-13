import java.util.List;
import java.util.ArrayList;

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
	  


public class WorldController {
	
		int BOX_AMOUNT = 5;
		List<Box> gameBoxes = new ArrayList<Box>();
		List<Box> gameProjectiles = new ArrayList<Box>();
		Character gameCharacter;
		static WorldController gameController;
		
		 public void update(){
			gameCharacter.update();
				 
		}
		public void createProjectile(Box newProjectile){
			
			gameProjectiles.add(newProjectile);
		
			
		}
		
	    public void start() {
	    	
	    	
	    	
	    	 // Static Body
		    Vec2  gravity = new Vec2(0,-10);
		    World gameWorld = new World(gravity);
		    BodyDef groundBodyDef = new BodyDef();
		    groundBodyDef.position.set(0, -15);
		    Body groundBody = gameWorld.createBody(groundBodyDef);
		    PolygonShape groundBox = new PolygonShape();
		    groundBox.setAsBox(800, 0);
		    groundBody.createFixture(groundBox, 0);
		    
		    
		    
		 
		    // Setup world
		    float timeStep = 1.0f/60.0f;
		    int velocityIterations = 6;
		    int positionIterations = 2;

		    // Run loop
		    for (int i = 0; i <BOX_AMOUNT; ++i) {
		    	for( int j = 0; j<BOX_AMOUNT; j++)
		    	gameBoxes.add(new Box(gameWorld,BodyType.DYNAMIC,(2*i)-10,(2*j)-15,1,1,0,1,1f,0.5f,0));
		    } 
		    gameCharacter = new Character(gameController,gameWorld,BodyType.DYNAMIC,-13,-2,1,1,0,0,1f,0,0);
		    
		    
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
	    	
	    	gameWorld.step(timeStep, velocityIterations, positionIterations);
	    	update();
	    	
	    	
	        // Clear the screen and depth buffer
	        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);  
	         
	        GL11.glClearColor(1, 1, 1, 0);
	        // set the color of the quad (R,G,B,A)
	       
	             
	        for(Box box: gameBoxes){
	        	box.draw();

		    } 
	        
	        for(Box projectile: gameProjectiles){
	        	projectile.draw();
	        }
	        
	        gameCharacter.draw();
	  
	        Display.update();
	        
	        Display.sync(60);
	    }
	  
	    Display.destroy();
	    }
	    
	   
	  
	
	public static void main(String[] args) {
		
		gameController = new WorldController();
	       gameController.start();
	}

}
