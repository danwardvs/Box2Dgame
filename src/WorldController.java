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
import org.lwjgl.Sys;
	  


public class WorldController {
	
		int BOX_AMOUNT = 25;
		List<Box> gameBoxes = new ArrayList<Box>();
		List<Projectile> gameProjectiles = new ArrayList<Projectile>();
		Character gameCharacter;
		static WorldController gameController;
		
		  /** time at last frame */
	    long lastFrame;
	     
	    /** frames per second */
	    int fps;
	    /** last fps time */
	    long lastFPS;
	 
		
		 public void update(int delta){
			gameCharacter.update(delta);
			updateFPS();
			
			for(int j = 0; j < gameProjectiles.size(); j++)
			{
			    Projectile newProjectile = gameProjectiles.get(j);

			    if(newProjectile.update(delta)){
			       //found, delete.
			        gameProjectiles.remove(j);
			        break;
			    }

			}
			//System.out.println(gameProjectiles.size());

			
				 
		}
		public void createProjectile(Projectile newProjectile){
			
			gameProjectiles.add(newProjectile);
		
			
		}
		
	    /** 
	     * Calculate how many milliseconds have passed 
	     * since last frame.
	     * 
	     * @return milliseconds passed since last frame 
	     */
	    public int getDelta() {
	        long time = getTime();
	        int delta = (int) (time - lastFrame);
	        lastFrame = time;
	      
	        return delta;
	    }
	     
	    /**
	     * Get the accurate system time
	     * 
	     * @return The system time in milliseconds
	     */
	    public long getTime() {
	        return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	    }
	     
	    /**
	     * Calculate the FPS and set it in the title bar
	     */
	    public void updateFPS() {
	        if (getTime() - lastFPS > 1000) {
	            Display.setTitle("FPS: " + fps);
	            fps = 0;
	            lastFPS += 1000;
	        }
	        fps++;
	    }
		
	    public void start() {
	    	
	    	 getDelta(); // call once before loop to initialise lastFrame
	         lastFPS = getTime(); // call before loop to initialise fps timer
	    	
	    	
	    	
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
		    	
		    	gameBoxes.add(new Box(gameWorld,BodyType.DYNAMIC,(float)(Math.random()*30)-10,(float)(Math.random()*30)-15,(float)(Math.random()*2),(float)(Math.random()*2),(float)(Math.random()*2),1,1f,0.5f,0));
		    	//gameBoxes.add(new Box(gameWorld,BodyType.DYNAMIC,(i)-12,-13,0.2f,1,0,1,1f,0.5f,0));
		    	
		    } 
		    gameCharacter = new Character(gameController,gameWorld,-13,-2,0.5f,1.5f,0,0,1f,0,0);
		    
		    
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
	    	float delta = getDelta();
	    	//System.out.print(delta/1000);
	    	//System.out.println(" " + timeStep);
	    	update((int)delta);
	    	gameWorld.step(delta/1000, velocityIterations, positionIterations);
	    	
	    	
	    	
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
